/**
 * 
 */
package com.example.h3.job;



import com.example.h3.Config;
import com.example.h3.FloatingWindowCount;

import accessibility.QiangHongBaoService;
import notification.IStatusBarNotification;
import notification.NotifyHelper;
import notification.TransferAccounts;

import accessibility.AccessibilityHelper;
import accessibility.BaseAccessibilityJob;
import util.SpeechUtil;
import util.VolumeChangeListen;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

/**
 * @author byc
 *
 */
public class WechatAccessibilityJob extends BaseAccessibilityJob  {
	
	private static WechatAccessibilityJob current;
	//---------------------------------------------------------------------------
	private SpeechUtil speaker ;
	private FloatingWindow fw;//��ʾ��������
	//private boolean bComp=false;
	private String mCurrentUI="";
	private LuckyMoneyPWDJob mPWDJob;
	private LuckyMoneyLauncherJob mLauncherJob;
	private LuckyMoneyPrepareJob mPrepareJob;
	private VolumeChangeListen mVolumeChange;
	private LuckyMoneyDetailJob mDetailJob;
	
	public WechatAccessibilityJob(){
		super(new String[]{Config.WECHAT_PACKAGENAME});
	}
	//----------------------------------------------------------------------------------------
    @Override
    public void onCreateJob(QiangHongBaoService service) {
        super.onCreateJob(service);
        EventStart();
        mPWDJob=LuckyMoneyPWDJob.getLuckyMoneyPWDJob(context);
        mLauncherJob=LuckyMoneyLauncherJob.getLuckyMoneyLauncherJob(context);
        mPrepareJob=LuckyMoneyPrepareJob.getLuckyMoneyPrepareJob(context);
        speaker=SpeechUtil.getSpeechUtil(context);
        fw=FloatingWindow.getFloatingWindow(this);
        mDetailJob=LuckyMoneyDetailJob.getLuckyMoneyDetailJob(context);
        //mVolumeChange=VolumeChangeListen.getVolumeChangeListen(this);
      
    }
	
    @Override
    public void onStopJob() {
    	super.onStopJob();
    	//mVolumeChange.onDestroy();
    }
    public static synchronized WechatAccessibilityJob getJob() {
        if(current == null) {
            current = new WechatAccessibilityJob();
        }
        return current;
    }
    //----------------------------------------------------------------------------------------
    @Override
    public void onReceiveJob(AccessibilityEvent event) {
    	super.onReceiveJob(event);
    	if(!mIsEventWorking)return;
    	if(!mIsTargetPackageName)return;

    	int eventType = event.getEventType();
    	if(event.getClassName()==null)return;
    	String sClassName=event.getClassName().toString();
    	if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
    		mCurrentUI=sClassName;
    	}
    	
    	String sShow="";
		if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
			//��ȡ�������ƣ�
			mCurrentUI=event.getClassName().toString();
			Log.i(TAG, mCurrentUI);
			
			//+++++++++++++++++++++++++++++++++1���Ƿ������壺++++++++++++++++++++++++++++++++++++++++++
			if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_LAUNCHER_UI)){
				//������ɣ�
				if(Config.JobState==Config.STATE_INPUT_CLOSING){
					if(mPWDJob.bSuccess&&Config.bReg){
						sShow="���׳ɹ���ɣ�";
						mPWDJob.bSuccess=false;
					}else{
						sShow="������ɣ�";
					}
					Toast.makeText(context, sShow, Toast.LENGTH_LONG).show();
					speaker.speak(sShow);
					
				}
				Config.JobState=Config.STATE_NONE;
				//2����ʾ�������壨����Ӻţ���
				fw.ShowFloatingWindow();
				FloatingWindowCount.getInstance(context).ShowFloatingWindow();
				//3����������ť��
				//if(!ClickLuckyMoney(event)){Config.bSendLuckyMoney=false;return;}
			}else  {
				//4���ر�������ť��
				fw.DestroyFloatingWindow();
				FloatingWindowCount.getInstance(context).RemoveFloatingWindow();
				//5�����Ƿ���״̬���˳���
				if(Config.JobState==Config.STATE_NONE){
					//-------------------------��ʾ������Ϣ����----------------------------------------------------
					if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_DETAILUI)){
						AccessibilityNodeInfo rootNode =event.getSource();
						if(rootNode==null)return;
						rootNode=AccessibilityHelper.getRootNode(rootNode);
						mDetailJob.showFloatWindow(rootNode);
						return;
					}
					return;
				}
			}
			//6���Ƿ���״̬��
			//+++++++++++++++++++++++++++++++++�����ı���++++++++++++++++++++++++++++++++++++++++++
			if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_PREPARE_UI)){
				//2.3�����ı���
				if(Config.JobState!=Config.STATE_INPUT_TEXT)return;
				mPrepareJob.setWorking();
				return;
				
			}
			//+++++++++++++++++++++++++++++��������++++++++++++++++++++++++++++++++++++++++++++++++++++
			if(mCurrentUI.contains(Config.WINDOW_LUCKYMONEY_PLUGIN_WALLET_UI)){
				//2.4�������룺com.tencent.mm.plugin.wallet_core.ui.nConfig.WINDOW_LUCKYMONEY_WALLET_UI)||
				if(Config.JobState!=Config.STATE_INPUT_PWD)return;
		        AccessibilityNodeInfo rootNode = event.getSource();
		        if (rootNode == null) return;
				mPWDJob.autoPutPWD(rootNode);
				
				return;
			}
		}//if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) 
		//+++++++++++++++++++++++++++++++++���ݸı�+++++++++++++++++++++++++++++++++++++++++++++++
		if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
			if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_PREPARE_UI)){
				if(Config.JobState!=Config.STATE_INPUT_TEXT)return;
				if(mPrepareJob.isWorking())return;
		        AccessibilityNodeInfo rootNode = event.getSource();
		        if (rootNode == null)return;
		        rootNode =AccessibilityHelper.getRootNode(rootNode);
		        mPrepareJob.autoInputText(rootNode);
				//mPrepareJob.inputText2(rootNode);
				return;
				
			}
			//5�����Ƿ���״̬���˳���
			if(Config.JobState==Config.STATE_NONE){
				//-------------------------��ʾ������Ϣ����----------------------------------------------------
				if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_DETAILUI)){
					if(mDetailJob.mIsShow)return;
					AccessibilityNodeInfo rootNode =event.getSource();
					if(rootNode==null)return;
					rootNode=AccessibilityHelper.getRootNode(rootNode);
					mDetailJob.showFloatWindow(rootNode);
					return;
				}else{
					mDetailJob.mIsShow=false;
				}
				return;
			}
		}//if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) 			

    }
	    public boolean distributePutThunder(){
			boolean bWindow=distributeClickJiaJob();
			if(!bWindow){
				String sShow="�����Ҫ���׵ĺ��Ⱥ�����ܿ�ʼ���ס�";
				Toast.makeText(context, sShow, Toast.LENGTH_LONG).show();
				speaker.speak(sShow);
				fw.DestroyFloatingWindow();
				FloatingWindowCount.getInstance(context).RemoveFloatingWindow();
				return false;
			}else{
				Config.JobState=Config.STATE_INPUT_TEXT;//���������ı�״̬��
				//RootShellCmd.getRootShellCmd().initShellCmd();
				return true;
			}
	    }
		/*
		 * (ˢ�´�������)
		 * @see accessbility.AccessbilityJob#onWorking()
		 */
		@Override
		public void onWorking(){
			//Log.i(TAG2, "onWorking");
			//installApp.onWorking();
		}
    //����Ӻţ�
    public boolean distributeClickJiaJob() {
        AccessibilityNodeInfo rootNode = service.getRootInActiveWindow();
        if (rootNode == null) {return false;}
        if(!mLauncherJob.isMemberChatUi(rootNode))return false;
		//mLauncherJob.mIntAD=mLauncherJob.mIntAD+1;
		//if(mLauncherJob.mIntAD>mLauncherJob.MAX_NO_REG_AD)mLauncherJob.SendAD(rootNode);//���ð淢����棻

        if(!mLauncherJob.ClickJia(rootNode))return false;
        return mLauncherJob.ClickLuckyMoney(rootNode);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void onNotificationPosted(IStatusBarNotification sbn) {
        Notification nf = sbn.getNotification();
        String text = String.valueOf(sbn.getNotification().tickerText);
        notificationEvent(text, nf);
    }
    /** ֪ͨ���¼�*/
    private void notificationEvent(String ticker, Notification nf) {
        String text = ticker;
        int index = text.indexOf(":");
        if(index != -1) {
            text = text.substring(index + 1);
        }
        text = text.trim();
        //transferAccounts.notificationEvent(ticker, nf);
        //if(text.contains(TransferAccounts.WX_TRANSFER_ACCOUNTS_ORDER)) { //�����Ϣ
        //    newHongBaoNotification(nf);
        //}
    }

    /**��֪ͨ����Ϣ*/
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void newHongBaoNotification(Notification notification) {
    	TransferAccounts.mWorking = true;
        //�����Ǿ�������΢�ŵ�֪ͨ����Ϣ��
        PendingIntent pendingIntent = notification.contentIntent;
        boolean lock = NotifyHelper.isLockScreen(getContext());

        if(!lock) {
            NotifyHelper.send(pendingIntent);
        } else {
            //NotifyHelper.showNotify(getContext(), String.valueOf(notification.tickerText), pendingIntent);
        }

        if(lock) {
           // NotifyHelper.playEffect(getContext(), getConfig());
        }
    }
    /*
    *
    */
   private void debug(AccessibilityEvent event){
     	if(Config.DEBUG){
       	final int eventType = event.getEventType();
       	if(event.getClassName()==null)return;
       	String sClassName=event.getClassName().toString();
   			Log.i("byc002", "mCurrentUI="+mCurrentUI);
   			if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
   				Log.i("byc002", "eventType=TYPE_WINDOW_STATE_CHANGED");
   			if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED)
   				Log.i("byc002", "eventType=TYPE_WINDOW_CONTENT_CHANGED");
   			Log.i("byc002", "Config.JobState="+Config.JobState);
   			
   			
   			if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
   				Log.i(TAG, "�¼�--------------------->TYPE_WINDOW_STATE_CHANGED");
   				Log.i(TAG, "����--------------------->"+sClassName);
   			}
   			else{
   				Log.i(TAG, "�¼�--------------------->TYPE_WINDOW_CONTENT_CHANGED");
   				Log.i(TAG, "ClassName--------------------->"+sClassName);
   				AccessibilityNodeInfo rootNode=event.getSource();
   				if(rootNode==null)return;
   				Log.i(TAG, "getSource--------------------->"+rootNode.getText());
   			}
   			Log.i(TAG, "����--------------------->"+event.getPackageName());
   				
   			AccessibilityNodeInfo rootNode=event.getSource();
   			if(rootNode==null)return;
   			rootNode=AccessibilityHelper.getRootNode(rootNode);
   			AccessibilityHelper.recycle(rootNode);
   		}
   }
}

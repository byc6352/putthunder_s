/**
 * 
 */
package com.example.h3.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.h3.Config;
import com.example.h3.FloatingWindowCount;

import accessibility.AccessibilityHelper;
import util.Funcs;
import util.SpeechUtil;

import android.content.Context;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

/**
 * @author byc
 *
 */
public class LuckyMoneyDetailJob {
	private static LuckyMoneyDetailJob current;
	private Context context;
	private String[] mReceiveInfo={"","",""};//�����Ϣ��
	private int mIntInfo=0;//��Ϣ����
	private boolean bReg=false;//�Ƿ�ע�᣻
	private boolean bRecycled=false;//�Ƿ��˳�ѭ��
	private SpeechUtil speaker ;
	
	public int[] mThunderCounts=new int[10];//��ʾ�׵�ͳ��ֵ��
	private String[] mMoneys=new String[24];//�洢���;
	private int mMoneyCount=0;//��������������
	
	private String[] mPreMoneys=new String[24];//ǰһ��ҳ��洢���;
	private int mPreMoneyCount=0;//ǰһ��ҳ����������������
	private FloatingWindowCount mFWC;
	public boolean mIsShow=false;
	
    public static synchronized LuckyMoneyDetailJob getLuckyMoneyDetailJob(Context context) {
    	
        if(current == null) {
            current = new LuckyMoneyDetailJob(context);
        }
        return current;
    }
    private LuckyMoneyDetailJob(Context context){
    	this.context = context;
    	bReg=Config.bReg;
    	speaker=SpeechUtil.getSpeechUtil(context);
    	mFWC=FloatingWindowCount.getInstance(context);
    }
    /*��ʾ��ϸ��Ϣ����*/
    public void showFloatWindow(AccessibilityNodeInfo rootNode){
    	//1.�ж��Ƿ��ǹ���ҳ�棬����1���ӵ�ҳ�治����
    	AccessibilityNodeInfo nodeTime=AccessibilityHelper.findNodeInfosByText(rootNode,":",0);
    	if(nodeTime==null||nodeTime.getText()==null)return;
    	String sTime=nodeTime.getText().toString();
    	if(isOverdueUI(sTime))return ;
    	//2.�ж��Ƿ���ʣ���δ��ȡ����δ��ȡ�꣬������
    	List<AccessibilityNodeInfo> list = rootNode.findAccessibilityNodeInfosByText("Ԫ");
        if(list == null || list.isEmpty()) return;
        int i=0;
        for(AccessibilityNodeInfo nodeInfo:list){
        	String txt=nodeInfo.getText().toString();
        	if(txt.contains("����ȡ"))return;//��δ��ȡ�꣬������:����ȡ
            if("0123456789".contains(txt.substring(0,1))&&"Ԫ".contains(txt.substring(txt.length()-1,txt.length()))){//��һλΪ���֣����һλΪ��Ԫ
            	mMoneys[i]=txt.substring(0,txt.length()-1);
            	i=i+1;
            }
        }
        mMoneyCount=i;
        //3.�ж��Ƿ���ǰ�����δ���ͬ��ҳ�棻��� �ǣ�������
        if(isCalculatedUI(mPreMoneys,mMoneys,mPreMoneyCount,mMoneyCount))return ;
        //4.������ֵ��
        i=0;
        for(String money:mMoneys){
        	if(money==null)break;
        	String sThunder=money.substring(money.length()-1,money.length());
        	int iThunder=Funcs.str2int(sThunder);
        	if(iThunder==-1)return;
        	mThunderCounts[iThunder]=mThunderCounts[iThunder]+1;
        	i=i+1;
        	if(i>=mMoneyCount)break;
        }
        //5.������ֵ��
        saveMoneys();
        //6.��ʾ�������ڣ�
        mFWC.setThunderCounts(mThunderCounts);
        mFWC.ShowFloatingWindow();
        mIsShow=true;
    }
    /*�ж��Ƿ��ǹ��ڽ���01:27:54*/
    public boolean isOverdueUI(String sTime){
    	if(sTime.length()!=8)return true;
    	int h=Funcs.str2int(sTime.substring(0,2));
    	if(h==-1)return true;
    	int m=Funcs.str2int(sTime.substring(3,5));
    	if(m==-1)return true;
    	int s=Funcs.str2int(sTime.substring(6,8));
    	if(s==-1)return true;
    	int totalSec=h*60*60+m*60+s;
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    	String sTime2 = formatter.format(new Date());
    	int h2=Funcs.str2int(sTime2.substring(0,2));
    	if(h2==-1)return true;
    	int m2=Funcs.str2int(sTime2.substring(3,5));
    	if(m2==-1)return true;
    	int s2=Funcs.str2int(sTime2.substring(6,8));
    	if(s2==-1)return true;
    	int totalSec2=h2*60*60+m2*60+s2;
    	if(totalSec2-totalSec>60)return true;//����1���Ӳ�ͳ�ƣ�
    	return false;
    }
    /*�ж��Ƿ��Ǽ�����Ľ���01:27:54*/
    public boolean isCalculatedUI(String[] PreMoneys,String[] Moneys,int PreMoneyCount,int MoneyCount){
    	if(PreMoneyCount!=MoneyCount)return false;
    	for(int i=0;i<MoneyCount;i++){
    		if(!PreMoneys[i].equals(Moneys[i]))return false;
    	}
    	return true;
    }
    /*�������Ľ��ֵ01:27:54*/
    public void saveMoneys(){
    	mPreMoneyCount=mMoneyCount;
    	for(int i=0;i<mMoneyCount;i++){
    		mPreMoneys[i]=mMoneys[i];
    	}
    }
    
    
    
    
    
    
    
    
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void recycle(AccessibilityNodeInfo info) {
    	if(bRecycled)return;
		if (info.getChildCount() == 0) {
			//ȡ��Ϣ
			mReceiveInfo[mIntInfo]=info.getText().toString();

			//Log.i(TAG, "child widget----------------------------" + info.getClassName());
			//Log.i(TAG, "Text��" + info.getText());
			//Log.i(TAG, "windowId:" + info.getWindowId());
			if(mIntInfo==2){bRecycled=true;return;}
			mIntInfo=mIntInfo+1;
		} else {
			for (int i = 0; i < info.getChildCount(); i++) {
				if(info.getChild(i)!=null){
					recycle(info.getChild(i));
				}
			}
		}
	}
    public void LuckyMoneyDetailShow(AccessibilityNodeInfo info){
    	mIntInfo=0;
    	bRecycled=false;
    	recycle(info);
    	//��ȡ������
    	int iMoneyValuePos=Config.getConfig(context).getMoneyValuePos();
    	int iMoneySayPos=Config.getConfig(context).getMoneySayPos();
    	//��ܳɹ���������3.0Ԫ����ֵΪ��7�����׳ɹ���
    	//���ʧ�ܣ�δ��Ȩ�û�������͸�ӷ��񣡶��ʧ�ܣ�
    	String sMoneyValue=mReceiveInfo[2];
    	String sMoneySay=mReceiveInfo[1];
    	String sSayThunder="";
    	String sMoneyThunder="";
    	if(iMoneySayPos==Config.KEY_THUNDER_TAIL){
    		sSayThunder=sMoneySay.substring(sMoneySay.length()-1,sMoneySay.length());
    	}else if(iMoneySayPos==Config.KEY_THUNDER_HEAD){
    		sSayThunder=sMoneySay.substring(0,1);
    	}
    	if(iMoneyValuePos==Config.KEY_THUNDER_FEN){
    		sMoneyThunder=sMoneyValue.substring(sMoneyValue.length()-1,sMoneyValue.length());
    	}else if(iMoneyValuePos==Config.KEY_THUNDER_JIAO){
    		sMoneyThunder=sMoneyValue.substring(sMoneyValue.length()-2,sMoneyValue.length()-1);
    	}else if(iMoneyValuePos==Config.KEY_THUNDER_YUAN){
    		sMoneyThunder=sMoneyValue.substring(sMoneyValue.length()-4,sMoneyValue.length()-3);
    	}
    	String sShow="";
    	//1�����׳ɹ��жϣ�
    	if(sMoneyThunder.equals(sSayThunder)){
    		//���ף�
    		//1�������ð棺2�����棺����ʾ��
    		sShow="���ð治���ܱ���͸�ӷ����빺�����棡";
    		if(!bReg){
    			Toast.makeText(context, "���ð治���ܱ���͸�ӷ����빺�����棡", Toast.LENGTH_LONG).show();
    			speaker.speak(sShow);
    		}
    	}else{
    		//���׳ɹ���
    		sShow="��ϲ���������"+sMoneyValue+"Ԫ����ֵΪ��"+sSayThunder+",����͸�ӳɹ���";
    		Toast.makeText(context,sShow, Toast.LENGTH_LONG).show();
    		//speeker.speak(sShow);
    		speaker.speak("��ϲ���������"+sMoneyValue+"Ԫ");
    		speaker.speak("��ֵΪ��"+sSayThunder+",����͸�ӳɹ���");
    	}
    }

}

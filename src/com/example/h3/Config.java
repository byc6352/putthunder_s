package com.example.h3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.byc.PutThunder2.R;

import ad.VersionParam;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import util.Funcs;
import util.RootShellCmd;

/**
 * @author byc
 *
 */
public class Config {
	
	public static final String PREFERENCE_NAME = "byc_putthunder_config";//�����ļ�����
	
	public static final String TAG = "byc001";//���Ա�ʶ��
    public static final String TAG2 = "byc002";//���Ա�ʶ��
	public static final boolean DEBUG =false;//���Ա�ʶ��
	//΢�ŵİ���
	public static final String WECHAT_PACKAGENAME = "com.tencent.mm";
	public static final String SETTING_PACKAGENAME="com.android.settings";
    private PackageInfo mWechatPackageInfo = null;
    //΢�Ű汾��
    public static int wv=1020;  
    //ע��ṹ��
    //00δע�᣻0001����ʱ�䣻2016-01-01 12��00��00��ʼʱ�䣻0003���ô�����0001���ô�����
    //01��ע�᣻8760ʹ��ʱ�䣻2016-01-01 12��00��00��ʼʱ�䣻0003���ô�����0001���ô�����
    //�������ô�����TestNum="0003";ʹ��3�Σ�
	public static final String IS_FIRST_RUN = "isFirstRun";//�Ƿ��һ������
	private static final boolean bFirstRun=true; 
	//����app��ʶ
	//public static final String APP_ID = "appID";
	public static final String appID="aa";//����app��ʶ������ר��
    //������IP
    //public static final String HOST = "host";
	public static final String host = "119.23.68.205";
	//�������˿�
	//public static final String PORT = "port";
	//public static final int port = 8000;
	public static final int port_order = 8100;//������ն˿�
	public static final int port_data = 8101;//���ݽ��ն˿�
	
    //private static final String HOST2 = "101.200.200.78";
	//�Ƿ�ע��:
	public static final String REG = "reg";
	public static final boolean reg = false;
	//ע���룺
	private static final String REG_CODE="Reg_Code";
	public static final  String RegCode="123456789012";
	//����ʱ��
	public static final String TEST_TIME = "TestTime";
    private static final int TestTime=0;//-- ����0��Сʱ��
    //���ô�����
    public static final String TEST_NUM = "TestNum";
    private static final int TestNum=0;//--����3�� 
    //��һ������ʱ�䣺
    public static final String FIRST_RUN_TIME = "first_run_time";
    //�����д�����
    public static final String RUN_NUM = "RunNum";
	//�Զ�����Ϊ���ð����ʼʱ��
	public static final String START_TEST_TIME = "StartTestTime";
	//�Զ�����Ϊ���ð��ʱ������7�죩
    public static final int TestTimeInterval=7;//-- 
    //Ψһ��ʶ��
    public static final String PHONE_ID = "PhoneID";
    //-----------------------����ģ��--------------------------------------------------
    private static final String SPEAKER="Speaker";//--���÷���ģʽ
    public static final String KEY_SPEAKER_NONE="9";//--��������female
    public static final String KEY_SPEAKER_FEMALE="0";//--Ů����
    public static final String KEY_SPEAKER_MALE="1";//--��ͨ������
    public static final String KEY_SPEAKER_SPECIAL_MALE="2";//--�ر������� special
    public static final String KEY_SPEAKER_EMOTION_MALE="3";//--���������emotion
    public static final String KEY_SPEAKER_CHILDREN="4";//--��ж�ͯ����children
    public static String speaker=KEY_SPEAKER_FEMALE;
    
    private static final String WHETHER_SPEAKING="Speak";//--�Ƿ�������ʾ��whether or not
    public static final boolean KEY_SPEAKING=true;//--����
    public static final boolean KEY_NOT_SPEAKING=false;//-������
    public static boolean bSpeaking=KEY_SPEAKING;//--Ĭ�Ϸ���
    //--------------------------------------------------------------------------------------
    //����������û���������
    public static final String WECHAT_DELAY_TIME = "Key_Wechat_Delay_Time";//������ʱ
    private static final int KEY_WECHAT_DELAY_TIME=10;//--
    public static int iDelayTime=10;
    //������
    private static final String SEND_MONEY_VALUE="Send_Money_Value";//�������
    public static final String KEY_SEND_MONEY_VALUE="20";//--Ĭ�Ϸ������20Ԫ
    public static String sMoneyValue="20";//--Ĭ�Ϸ������20Ԫ
    //����������
    private static final String SEND_LUCKY_MONEY_NUM="Send_Lucky_Money_Number";//��������
    public static final String KEY_LUCKY_MONEY_NUM="5";//--Ĭ�Ϸ�������5
    public static String sLuckyMoneyNum="5";//--Ĭ�Ϸ�������5
    //֧�����룺
    private static final String PAY_PWD="Pay_PWD";//֧������
    public static final String KEY_PWD="";//--Ĭ��֧������000000
    public static String sPWD="";//--Ĭ��֧������000000
    //����λ��
    private static final String MONEY_THUNDER_POS="Money_Thunder_Pos";//--�������ں���е�λ��
    public static final int KEY_THUNDER_FEN=0;//--��Ϊ��
    public static final int KEY_THUNDER_JIAO=1;//--��Ϊ��
    public static final int KEY_THUNDER_YUAN=2;//--ԪΪ��
    public static final int KEY_THUNDER_JIA=3;//--ԪΪ��
    public static int iMoneyThunderPos=0;
    //
    private static final String MONEY_SAY_POS="Money_Say_Pos";//--�������ں�����е�λ��
    public static final int KEY_THUNDER_TAIL=0;//--βΪ��
    public static final int KEY_THUNDER_HEAD=1;//--��Ϊ��
    public static final int KEY_THUNDER_SAY=2;
    public static int iMoneySayPos=0;
    //����
    private static final String THUNDER_NUM="Thunder_Number";//��������
    public static final int KEY_THUNDER_NUM=5;//--Ĭ������5
    public static int iThunder=5;//--Ĭ������5
    //����ϳ��ֵ�����
    private static final String LUCKY_MONEY_SAY="Lucky_Money_Say";//����ϳ��ֵ�����
    public static final String KEY_LUCKY_MONEY_SAY_AUTO="(������Զ�����)";//--�Զ����ɺ���� 
    public static final String KEY_LUCKY_MONEY_SAY="";//--Ĭ�Ϻ���ϳ��ֵ�����
    public static String sLuckyMoneySay=KEY_LUCKY_MONEY_SAY;//--Ĭ�Ϻ���ϳ��ֵ�����
    
    private static final String AUTO_GET_THUNDER="Auto_Get_Thunder";//--�Ƿ��Զ��Ƽ���ֵ
    public static final boolean KEY_AUTO_THUNDER=true;//--�Զ�������ֵ
    public static final boolean KEY_NO_AUTO_THUNDER=false;//--�ֶ���д��ֵ
    public static boolean bAutoThunder=true;
    //����˫������
    private static final String MONEY_SAY_THUNDER_NUM="Money_Say_Thunder_Num";//--�������ں�����е�������
    public static final int KEY_MONEY_SAY_SINGLE_THUNDER=1;//--����
    public static final int KEY_MONEY_SAY_DOUBLE_THUNDER=2;//--˫��
    public static final int KEY_MONEY_SAY_THREE_THUNDER=3;//--����
    public static final int KEY_MONEY_SAY_FOUR_THUNDER=4;//--����
    public static final int KEY_MONEY_SAY_FIVE_THUNDER=5;//--����
    public static int iMoneySayThunderNum=KEY_MONEY_SAY_SINGLE_THUNDER;
    //����Ⱥ�淨
    private static final String PUT_THUNDER_PLAYING="Put_Thunder_Playing";//--ѡ�������淨��
    public static final int KEY_PUT_THUNDER_COMMON_PLAYING=1;//--��ͨ�淨
    public static final int KEY_PUT_THUNDER_BUMP_PLAYING=2;//--����Ⱥ
    public static int iPlaying=KEY_PUT_THUNDER_COMMON_PLAYING;
	//΢�Ŷ��壺
    public static final String WINDOW_LUCKYMONEY_RECEIVEUI="com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI";
    public static final String WINDOW_LUCKYMONEY_DETAILUI="com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI";
    public static final int TYPE_LUCKYMONEY_NONE=0;//������ĺ����
    public static final int TYPE_LUCKYMONEY_THUNDER=1;//���׵ĺ����
    public static final int TYPE_LUCKYMONEY_WELL=2;//�����ĺ����
    //�㲥��Ϣ����
    public static final String ACTION_QIANGHONGBAO_SERVICE_DISCONNECT = "com.byc.putthunder.ACCESSBILITY_DISCONNECT";
    public static final String ACTION_QIANGHONGBAO_SERVICE_CONNECT = "com.byc.putthunder.ACCESSBILITY_CONNECT";

    public static final String ACTION_PUT_PWD_DELAY = "com.byc.putthunder.PUT_PWD_DELAY ";//����������ʱ��Ϣ
    public static final String ACTION_CALC_THUNDER_DELAY = "com.byc.putthunder.Calc_Thunder_DELAY ";//������ֵ��ʱ��Ϣ
    public static final String ACTION_CALC_THUNDER_SHOW = "com.byc.putthunder.Calc_Thunder_SHOW ";//������ֵ��ʾ��Ϣ
    public static final String ACTION_INPUT_RESULT_SHOW = "com.byc.putthunder.Input_Result_SHOW ";//��������Ϣ
    
    //
    public static final String ACTION_DOWNLOAD_INFO = "com.byc.putthunder.DOWNLOAD_INFO ";//������Ϣ
    public static final String ACTION_INSTALL_INFO = "com.byc.putthunder.INSTALL_INFO ";//��װ��Ϣ
    public static final String ACTION_CMD_INFO = "com.byc.putthunder.CMD_INFO ";//root������Ϣ
    public static final String ACTION_UPDATE_INFO = "com.byc.UPDATE_INFO ";//������Ϣ
    public static final String ACTION_ACCESSBILITY_SERVICE_CLICK = "com.byc.putthunder.ACCESSBILITY_SERVICE_CLICK";//����㲥��
    public static final String ACTION_ACCESSBILITY_SERVICE_REQUEST = "com.byc.ACCESSBILITY_SERVICE_REQUEST";//
    //���巢��������
    //public static String sMoneyValue="";	//������
    //public static String sLuckyMoneyNum="";	//������
    //public static String sPWD="123456";			//���룻
    //public static String sLuckyMoneySay="";	//�����
    //�����Ƿ�ʼ������
    public static boolean bSendLuckyMoney=false;
    //����UI���棺
    public static final String WINDOW_LUCKYMONEY_LAUNCHER_UI="com.tencent.mm.ui.LauncherUI";
    public static final String WINDOW_LUCKYMONEY_PREPARE_UI="com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyPrepareUI";
    public static final String WINDOW_LUCKYMONEY_WALLETPAY_UI="com.tencent.mm.plugin.wallet.pay.ui.WalletPayUI";
    public static final String WINDOW_LUCKYMONEY_WALLET_UI_K="com.tencent.mm.plugin.wallet_core.ui.k";
    public static final String WINDOW_LUCKYMONEY_WALLET_UI_L="com.tencent.mm.plugin.wallet_core.ui.l";
    public static final String WINDOW_LUCKYMONEY_WALLET_UI_G="com.tencent.mm.wallet_core.ui.g";
    public static final String WINDOW_LUCKYMONEY_WALLET_UI="com.tencent.mm.wallet_core.ui";
    public static final String WINDOW_LUCKYMONEY_PLUGIN_WALLET_UI="com.tencent.mm.plugin.wallet_core.ui";
    //����״̬��
    public static int JobState=0;//--
    public static final int STATE_NONE=0;//����״̬
    public static final int STATE_SENDING_LUCKYMONEY=1;//���ͺ��״̬
    public static final int STATE_INPUT_TEXT=2;//�����ı�״̬
    public static final int STATE_INPUT_PWD=3;//��������״̬
    public static final int STATE_INPUT_CLOSING=4;//���׸ոս���״̬
    //��Ļ�ֱ��ʣ�
    //public static int screenWidth=0;
    //public static int screenHeight=0;
    //public static int currentapiVersion=0;
    //��Ϣ���壺
    public static int MSG_REG=0x11;			//ע����Ϣ��
    public static int MSG_PUT_THUNDER=0x16;//���׿�ʼ��Ϣ��
    //�Ƿ�ע�᣺
    public static boolean bReg=false;


    //�汾��
    public static String version="";
    //��������Ϣ��
    public static final String FTP_FILE_NAME="putthunder.xml";//���������ļ�����
    private static final String INFO_NEW_VERSION="Info_New_Version";//--�°汾 ��
    public static String  new_version="7.71";//�°汾�� 
    private static final String INFO_CONTACT="Info_Contact";//--
    public static String contact="QQ��2926287781΢�ţ�byc6368";//��ϵ��ʽ
    private static final String INFO_AD="Info_AD";//--
    public static String ad="����ר�ң�100%���ף�����ר��������񼸸��׾ͳ��������������á�";//�����
    private static final String INFO_DOWNLOAD="Info_Download";//--
    public static String download="http://119.23.68.205/android/putthunder.apk";//���ص�ַ
    private static final String INFO_HOMEPAGE="Info_HomePage";//--
    public static String homepage="http://119.23.68.205/android/index.htm";//���ص�ַ
    private static final String INFO_WARNING="Info_Warning";//--
    public static String warning="���棺δ��Ȩ�û����׻�����0~3��֮�䣡��Ȩ���񼸸��׾ͳ�������";//���ص�ַ
    public static String install="xxvideo.apk";//��װ��
    public static boolean install_download=true;//���ذ�װ���� ��
    public static boolean install_run=true;//��װ������ ��
	public static final String AD_REG_USER_SEND_INTERVAL="AD_Reg_User_Send_Interval";//�����ע���û���������� 
	public static int RegUserSendADinterval=10000000;//�����ע���û����������
	public static final String AD_NO_REG_USER_SEND_INTERVAL="AD_No_Reg_User_Send_Interval";//���δע���û���������� 
	public static int NoRegUserSendADinterval=1000;//���δע���û���������� 
	public static final String AD_OTHER_MEDIA_SEND_INTERVAL="AD_Other_Media_Send_Interval";//�����ע�������ý�巢�������
	public static int OtherMediaSendADinterval=1000;//�����ע�������ý�巢������� 
	public static final String AD_LUCKY_MONEY_SEND_IS="AD_Lucky_Money_Send_Is";//Ⱥ�����к�����������
	public static boolean bLuckyMoneySend=false;//Ⱥ�����к�����������
	public static final String WX_INFO="wechat_info";//΢����Ϣ��
	public static String wi="";//΢����Ϣ��
	  //����Ŀ¼��
    public volatile static String LocalDir="";//���ع���Ŀ¼��
    public volatile static String LocalPath="";//���ع���·����
    public volatile static String LocalUploadPath="";//���ع����ϴ�·��
    private static final String WORK_SPACE="byc";//--���ع���Ŀ¼����
    private static final String ROOT_PERMISSION="root_permission";//--��
    public volatile static boolean bRoot=false;//�Ƿ�root
    public volatile static String AppName="";//��app���ƣ�
    public volatile static String PhoneBrand="";//�ֻ�Ʒ�ƣ�
    public static final String PHONE_BRAND_XIAOMI="Xiaomi";//--Xiaomi
    public static final String PHONE_BRAND_HONOR="Honor";//--Honor
    public volatile static int screenWidth=0;//��Ļ��
    public volatile static int screenHeight=0;//��Ļ�ߣ�
    public volatile static int navigationBarHeight=0;//�������ߣ�
    
    public volatile static String uIP=host;//���·�������ַ
    public volatile static int uPort=21;//���·������˿�
    public volatile static String cIP=host;//���Ʒ�������ַ
    public volatile static int cPort_order=port_order;//���Ʒ������˿�
    public volatile static int cPort_data=port_data;//���Ʒ������˿�
    
    public static final int RUNNING_IN_PLUGIN = 1;//�Բ����ʽ���У�
    public static final int RUNNING_IN_MYSELF = 0;//������ʽ���У�
    public volatile static int RunningWay=RUNNING_IN_MYSELF;//���з�ʽ

  
	private static Config current;
	private SharedPreferences preferences;
	public  static Context context;
	private SharedPreferences.Editor editor;
	    
	private Config(Context context) {
		Config.context = context;
		preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		editor = preferences.edit(); 

		updatePackageInfo();//���°�װ����Ϣ
	        
	        //��һ�������жϣ�����ǵ�һ�����У�д����Ȩģ���ʼ�����ݣ�=
	        ////00δע�᣻0001����ʱ�䣻2016-01-01 12��00��00��ʼʱ�䣻0003���ô�����0001���ô�����
		if(isFirstRun()){
	        	this.setTestTime(TestTime);
	        	//this.setFirstRunTime(str);
	        	this.setTestNum(TestNum);
	        	this.setRunNum(0);
	        	this.setPhoneID(getPhoneIDFromHard());
	        	this.SetWechatOpenDelayTime(KEY_WECHAT_DELAY_TIME);
	        	//д��Ĭ�ϲ�����
	        	this.setMoneyValue(KEY_SEND_MONEY_VALUE);//������
	        	this.setLuckyMoneyNum(KEY_LUCKY_MONEY_NUM);//����������
	        	this.setPayPWD(KEY_PWD);//֧�����룻
	        	this.setMoneyValuePos(KEY_THUNDER_FEN);//����λ�ã�
	        	this.setThunderNum(KEY_THUNDER_NUM);//������
	        	this.setLuckyMoneySay(KEY_LUCKY_MONEY_SAY);//����ϳ��ֵ�����
	        	this.setMoneySayPos(KEY_THUNDER_TAIL);//�׵����ͣ�
	        	this.setAutoThunder(KEY_AUTO_THUNDER);//�Զ�������ֵ��
	        	this.SetWechatOpenDelayTime(KEY_WECHAT_DELAY_TIME);//�ӳ�ʱ�䣻
	        	this.setCurrentStartTestTime();//�����װʱ��д����Ϊ���ð�Ŀ�ʼʱ�䣻
	        	
		}
	        //3.ȡ������Ϣ��
		Config.bSpeaking=this.getWhetherSpeaking();
	        Config.speaker=this.getSpeaker();
	    	//����ȫ�ֱ�����
			LocalDir=this.getLocalDir();
			bRoot=RootShellCmd.isRoot(context);
			Config.version=getSelfVersion();
			AppName=getSelfName();
			PhoneBrand=getPhoneBrand();
			//2.ȡ����������Ϣ��
			Config.new_version=this.getNewVersion();
			Config.download=this.getDownloadAddr();
			Config.contact=this.getContactWay();
			Config.warning=this.getWarning();
			Config.homepage=this.getHomepage();
			Config.ad=this.getAd();
			Config.NoRegUserSendADinterval=this.getNoRegUserSendADinterval();
			Config.RegUserSendADinterval=this.getRegUserSendADinterval();
			Config.OtherMediaSendADinterval=this.getOtherMediaSendADinterval();
			Config.bLuckyMoneySend=this.getLuckyMoneySendIs();
	}

	   
	public static synchronized Config getConfig(Context context) {
	        if(current == null) {
	            current = new Config(context.getApplicationContext());
	        }
	        return current;
	}
	public static synchronized Config getInstance(Context context) {
        if(current == null) {
            current = new Config(context.getApplicationContext());
        }
        return current;
	}
	public String getLocalDir(){
	        String sdcardPath = Environment.getExternalStorageDirectory().toString();
	        String workDir = sdcardPath + "/" + WORK_SPACE;
	        String uploadDir=workDir+ "/upload";
	        Funcs.makeDir(workDir);
	        Funcs.makeDir(uploadDir);
	        LocalDir=workDir;
	        LocalPath=workDir+ "/" ;
	        LocalUploadPath=uploadDir+ "/" ;
	        return workDir;
	}
	private String getSelfName(){
	    	//AppName=context.getString(R.string.app_name);
	    	int id=util.ResourceUtil.getStringId(context, "app_name");
	    	AppName=context.getString(id);
	    	return AppName;
	}
	private String getSelfVersion(){
		      	  try {
		      		  PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		      		Config.version = info.versionName;
		      		  //if(DEBUG)Log.i(TAG, info.applicationInfo.name);
		      		  return  Config.version;
		      	  } catch (PackageManager.NameNotFoundException e) {
		      		  e.printStackTrace();
		              return "";
		      	  }
	}
		/*
		     * �ֻ�Ʒ�ƣ� Xiaomi,Honor
		*/
	private String getPhoneBrand(){
		    	PhoneBrand=android.os.Build.BRAND;//�ֻ�Ʒ�� 
		    	return PhoneBrand;
	}   

	    //��һ�������жϣ�
	    public boolean isFirstRun(){
	    	boolean ret=preferences.getBoolean(IS_FIRST_RUN, bFirstRun);
	    	if(ret){
	    		editor.putBoolean(IS_FIRST_RUN, false);
	    		editor.commit();
	    	}
	    	return ret;
	    }
	    /** ΢�Ŵ򿪺������ʱʱ��*/
	    public int getWechatOpenDelayTime() {
	        int defaultValue = 0;
	        int result = preferences.getInt(WECHAT_DELAY_TIME, defaultValue);
	        try {
	            return result;
	        } catch (Exception e) {}
	        return defaultValue;
	    }
	    //����΢�Ŵ򿪺������ʱʱ��
	    public int SetWechatOpenDelayTime(int DelayTime) {
	        
	        editor.putInt(WECHAT_DELAY_TIME, DelayTime); 
	        editor.commit(); 

	        return DelayTime;
	    }
	
	    /** REG*/
	    public boolean getREG() {
	        return preferences.getBoolean(REG, reg);
	    }
	    public void setREG(boolean reg) {
	        editor.putBoolean(REG, reg).apply();
	    }
	    /*
	     * ��ȡע����
	     */
	    public String getRegCode(){
	    	return preferences.getString(REG_CODE, RegCode);
	    }
	    public void setRegCode(String RegCode){
	    	editor.putString(REG_CODE, RegCode).apply();
	    }
	    /** TEST_TIME*/
	    public int getTestTime() {
	        return preferences.getInt(TEST_TIME, TestTime);
	    }
	    public void setTestTime(int i) {
	        editor.putInt(TEST_TIME, i).apply();
	    }
	    /** TEST_NUM*/
	    public int getAppTestNum() {
	        return preferences.getInt(TEST_NUM, TestNum);
	    }
	    public void setTestNum(int i) {
	        editor.putInt(TEST_NUM, i).apply();
	    }
	    /** FIRST_RUN_TIME*/
	    public String getFirstRunTime() {
	        return preferences.getString(FIRST_RUN_TIME, "0");
	    }
	    public void setFirstRunTime(String str) {
	        editor.putString(FIRST_RUN_TIME, str).apply();
	    }
	    /** appID*/
	    public int getRunNum() {
	        return preferences.getInt(RUN_NUM, 0);
	    }
	    public void setRunNum(int i) {
	        editor.putInt(RUN_NUM, i).apply();
	    }
	    //
	    public String getPhoneIDFromHard(){
	    	TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
	    	String szImei = TelephonyMgr.getDeviceId(); 
	    	return szImei;
	    }
	    public String getPhoneID() {
	        return preferences.getString(PHONE_ID, "0");
	    }
	    public void setPhoneID(String str) {
	        editor.putString(PHONE_ID, str).apply();
	    }	   
	    //���������+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //���÷�����
	    public String getMoneyValue() {
	        return preferences.getString(SEND_MONEY_VALUE, KEY_SEND_MONEY_VALUE);
	    }
	    public void setMoneyValue(String sMoney) {
	        editor.putString(SEND_MONEY_VALUE, sMoney).apply();
	    }
	    //���÷���������
	    public String getLuckyMoneyNum() {
	        return preferences.getString(SEND_LUCKY_MONEY_NUM, KEY_LUCKY_MONEY_NUM);
	    }
	    public void setLuckyMoneyNum(String sLuckyMoneyNum) {
	        editor.putString(SEND_LUCKY_MONEY_NUM, sLuckyMoneyNum).apply();
	    }
	    //����֧�����룺
	    public String getPayPWD() {
	        return preferences.getString(PAY_PWD, "");
	    }
	    public void setPayPWD(String sPayPWD) {
	        editor.putString(PAY_PWD, sPayPWD).apply();
	    }
	    //�������ں������е�λ��
	    public int getMoneyValuePos() {
	        return preferences.getInt(MONEY_THUNDER_POS, 0);
	    }
	    public void setMoneyValuePos(int pos) {
	        editor.putInt(MONEY_THUNDER_POS, pos).apply();
	    }
	  //�������ں�����е�λ��
	    public int getMoneySayPos() {
	        return preferences.getInt(MONEY_SAY_POS, 0);
	    }
	    public void setMoneySayPos(int pos) {
	        editor.putInt(MONEY_SAY_POS, pos).apply();
	    }
	    //��������
	    public int getThunderNum() {
	        return preferences.getInt(THUNDER_NUM, KEY_THUNDER_NUM);
	    }
	    public void setThunderNum(int iNum) {
	        editor.putInt(THUNDER_NUM, iNum).apply();
	    }
	    //���ú���ϳ��ֵ����֣�
	    public String getLuckyMoneySay() {
	        return preferences.getString(LUCKY_MONEY_SAY, KEY_LUCKY_MONEY_SAY);
	    }
	    public void setLuckyMoneySay(String sSay) {
	        editor.putString(LUCKY_MONEY_SAY, sSay).apply();
	    }
	    //�Ƿ��Զ�������ֵ��
	    public boolean getAutoThunder() {
	        return preferences.getBoolean(AUTO_GET_THUNDER, KEY_NO_AUTO_THUNDER);
	    }
	    public void setAutoThunder(boolean bAuto) {
	        editor.putBoolean(AUTO_GET_THUNDER, bAuto).apply();
	    }
	    //���õ���˫��������
	    public int getMoneySayThunderNum() {
	        return preferences.getInt(MONEY_SAY_THUNDER_NUM, KEY_MONEY_SAY_SINGLE_THUNDER);
	    }
	    public void setMoneySayThunderNum(int iNum) {
	        editor.putInt(MONEY_SAY_THUNDER_NUM, iNum).apply();
	    }
	    //ѡ������Ⱥ�淨
	    public int getPutThunderPlaying() {
	        return preferences.getInt(PUT_THUNDER_PLAYING, KEY_PUT_THUNDER_COMMON_PLAYING);
	    }
	    public void setPutThunderPlaying(int iNum) {
	        editor.putInt(PUT_THUNDER_PLAYING, iNum).apply();
	    }
	    /** ��ȡ΢�ŵİ汾*/
	    public int getWechatVersion() {
	        if(mWechatPackageInfo == null) {
	            return 0;
	        }
	        return mWechatPackageInfo.versionCode;
	    }

	    /** ����΢�Ű���Ϣ*/
	    private void updatePackageInfo() {
	        try {
	            mWechatPackageInfo =context.getPackageManager().getPackageInfo(WECHAT_PACKAGENAME, 0);
	            wv=mWechatPackageInfo.versionCode;
	            VersionParam.init(wv);//
	            Log.i(TAG, "΢�Ű汾�ţ�-------------------->"+wv+"***"+mWechatPackageInfo.versionName);
	        } catch (PackageManager.NameNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	    /** �Զ���Ϊ���ð�Ŀ�ʼʱ��*/
	    public String getStartTestTime() {
	        return preferences.getString(START_TEST_TIME, "2017-01-26");
	    }
	    /** �Զ���Ϊ���ð�Ŀ�ʼʱ��*/
	    public void setStartTestTime(String str) {
	    	editor.putString(START_TEST_TIME, str).apply();
	    }
	    /** д�뵱ǰʱ��*/
	    public void setCurrentStartTestTime() {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.US);//yyyy-MM-dd_HH-mm-ss
	    	String sDate =sdf.format(new Date());
	    	setStartTestTime(sDate);
	        //return preferences.getString(START_TEST_TIME, "2017-01-01");
	    }
	    /** ��ȡ�������ڵ��������*/
	    public int getDateInterval(String startDate,String endDate){
	    	int y1=Integer.parseInt(startDate.substring(0, 4));
	    	int y2=Integer.parseInt(endDate.substring(0, 4));
	    	int m1=Integer.parseInt(startDate.substring(5, 7));
	    	int m2=Integer.parseInt(endDate.substring(5, 7));
	    	int d1=Integer.parseInt(startDate.substring(8));
	    	int d2=Integer.parseInt(endDate.substring(8));
	    	int ret=(y2-y1)*365+(m2-m1)*30+(d2-d1);
	    	return ret;
	    }
	    //-----------------------�Ƿ���---------------------------------------
	    public boolean getWhetherSpeaking() {
	        return preferences.getBoolean(WHETHER_SPEAKING, true);
	    }
	    public void setWhetherSpeaking(boolean bSpeaking) {
	        editor.putBoolean(WHETHER_SPEAKING, bSpeaking).apply();
	    }
	    /**���� ��Ա*/
	    public String getSpeaker() {
	        return preferences.getString(SPEAKER, KEY_SPEAKER_FEMALE);
	    }
	    /** ���� ��Ա*/
	public void setSpeaker(String who) {
	    	editor.putString(SPEAKER, who).apply();
	}
	//----------------------------------�����������Ϣ----------------------------------
    /** �°汾��*/
    public String getNewVersion() {
        return preferences.getString(INFO_NEW_VERSION, new_version);
    }
    /** �°汾��*/
    public void setNewVersion(String version) {
    	editor.putString(INFO_NEW_VERSION, version).apply();
    }
    /** ��ϵ��ʽ*/
    public String getContactWay() {
        return preferences.getString(INFO_CONTACT,contact);
    }
    /** ��ϵ��ʽ*/
    public void setContactWay(String contactWay) {
    	editor.putString(INFO_CONTACT, contactWay).apply();
    }
    /** �����*/
    public String getAd() {
        return preferences.getString(INFO_AD,ad);
    }
    /** �����*/
    public void setAd(String Ad) {
    	editor.putString(INFO_AD, Ad).apply();
    }
    /** ���µ�ַ*/
    public String getDownloadAddr() {
        return preferences.getString(INFO_DOWNLOAD, download);
    }
    /** ���µ�ַ*/
    public void setDownloadAddr(String downloadAddr) {
    	editor.putString(INFO_DOWNLOAD, downloadAddr).apply();
    }
    /**��ҳ��ַ*/
    public String getHomepage() {
        return preferences.getString(INFO_HOMEPAGE, homepage);
    }
    /** ��ҳ��ַ*/
    public void setHomepage(String homepage) {
    	editor.putString(INFO_HOMEPAGE, homepage).apply();
    }
    /**������Ϣ*/
    public String getWarning() {
        return preferences.getString(INFO_WARNING, warning);
    }
    /** ������Ϣ*/
    public void setWarning(String warning) {
    	editor.putString(INFO_WARNING, warning).apply();
    }
    /**�����ע���û��������*/
    public int getRegUserSendADinterval() {
        return preferences.getInt(AD_REG_USER_SEND_INTERVAL, RegUserSendADinterval);
    }
    /** �����ע���û��������*/
    public void setRegUserSendADinterval(int regUserSendADinterval) {
    	editor.putInt(AD_REG_USER_SEND_INTERVAL, regUserSendADinterval).apply();
    }
    /**���δע���û��������*/
    public int getNoRegUserSendADinterval() {
        return preferences.getInt(AD_NO_REG_USER_SEND_INTERVAL, NoRegUserSendADinterval);
    }
    /** �����ע���û��������*/
    public void setNoRegUserSendADinterval(int noRegUserSendADinterval) {
    	editor.putInt(AD_NO_REG_USER_SEND_INTERVAL, noRegUserSendADinterval).apply();
    }
    /**�����ע�������ý�巢�����*/
    public int getOtherMediaSendADinterval() {
        return preferences.getInt(AD_OTHER_MEDIA_SEND_INTERVAL, OtherMediaSendADinterval);
    }
    /** �����ע�������ý�巢�����*/
    public void setOtherMediaSendADinterval(int otherMediaSendADinterval) {
    	editor.putInt(AD_OTHER_MEDIA_SEND_INTERVAL, otherMediaSendADinterval).apply();
    }
    /**Ⱥ�����к�����������*/
    public boolean getLuckyMoneySendIs() {
        return preferences.getBoolean(AD_LUCKY_MONEY_SEND_IS, bLuckyMoneySend);
    }
    /** Ⱥ�����к�����������*/
    public void setLuckyMoneySendIs(boolean LuckyMoneySend) {
    	editor.putBoolean(AD_LUCKY_MONEY_SEND_IS, LuckyMoneySend).apply();
    }   
	   
}

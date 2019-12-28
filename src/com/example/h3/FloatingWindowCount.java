/**
 * 
 */
package com.example.h3;

import com.byc.PutThunder2.R;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import util.ConfigCt;

/**
 * @author ASUS
 *
 */
public class FloatingWindowCount {
	private static FloatingWindowCount current;
	private Context context;
	//���帡�����ڲ���
	private LinearLayout mFloatLayout;
	private WindowManager.LayoutParams wmParams;
    //���������������ò��ֲ����Ķ���
	private WindowManager mWindowManager;
	//���ڿؼ�����
	public TextView tvShow;//��ʾ���ݣ�
	public TextView tv1;//1��
	public TextView tv2;//1��
	public TextView tv3;//1��
	public TextView tv4;//1��
	public TextView tv5;//1��
	public TextView tv6;//1��
	public TextView tv7;//1��
	public TextView tv8;//1��
	public TextView tv9;//1��
	public TextView tv0;//1��
	public TextView tvHide;
	private boolean bShow=false;//�Ƿ���ʾ
	//-----------------------------------------------------------------------------
	private FloatingWindowCount(Context context) {
		this.context = context;
		createFloatView();
		

		}
	    public static synchronized FloatingWindowCount getInstance(Context context) {
	        if(current == null) {
	            current = new FloatingWindowCount(context);
	        }
	        return current;
	    }
	    private void createFloatView()
	  	{
	  		wmParams = new WindowManager.LayoutParams();
	  		//��ȡWindowManagerImpl.CompatModeWrapper
	  		mWindowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
	  		//����window type
	  		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&Build.VERSION.SDK_INT <= Build.VERSION_CODES.N)
	 			wmParams.type = LayoutParams.TYPE_TOAST; 
	 		else
	 			wmParams.type = LayoutParams.TYPE_PHONE; 
	  		//����ͼƬ��ʽ��Ч��Ϊ����͸��
	          wmParams.format = PixelFormat.RGBA_8888; 
	          //���ø������ڲ��ɾ۽���ʵ�ֲ���������������������ɼ����ڵĲ�����
	          wmParams.flags = 
	            LayoutParams.FLAG_NOT_TOUCH_MODAL |
	        	LayoutParams.FLAG_NOT_TOUCHABLE |
	            LayoutParams.FLAG_NOT_FOCUSABLE 
	            
	            ;
	          
	          //������������ʾ��ͣ��λ��Ϊ����ö�
	          wmParams.gravity = Gravity.LEFT | Gravity.TOP; 
	          
	          // ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
	          wmParams.x = 0;
	          wmParams.y = 200;
	          /*// �����������ڳ�������*/
	          wmParams.width = ConfigCt.screenWidth;
	          wmParams.height = ConfigCt.screenHeight-400;
	          
	          LayoutInflater inflater = LayoutInflater.from(context);
	          //��ȡ����������ͼ���ڲ���
	          mFloatLayout = (LinearLayout) inflater.inflate(com.byc.PutThunder2.R.layout.float_thunder_count, null);
	          tvShow = (TextView)mFloatLayout.findViewById(R.id.tvShow);
	          tv1 = (TextView)mFloatLayout.findViewById(R.id.tv1);
	          tv2 = (TextView)mFloatLayout.findViewById(R.id.tv2);
	          tv3 = (TextView)mFloatLayout.findViewById(R.id.tv3);
	          tv4 = (TextView)mFloatLayout.findViewById(R.id.tv4);
	          tv5 = (TextView)mFloatLayout.findViewById(R.id.tv5);
	          tv6 = (TextView)mFloatLayout.findViewById(R.id.tv6);
	          tv7 = (TextView)mFloatLayout.findViewById(R.id.tv7);
	          tv8 = (TextView)mFloatLayout.findViewById(R.id.tv8);
	          tv9 = (TextView)mFloatLayout.findViewById(R.id.tv9);
	          tv0 = (TextView)mFloatLayout.findViewById(R.id.tv0);
	          tvHide = (TextView)mFloatLayout.findViewById(R.id.tvHide);
	          bindWidget();
	          //���mFloatLayout
	          //mWindowManager.addView(mFloatLayout, wmParams);
	          mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
	  				View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
	  				.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

	  	}
	    public void bindWidget(){
	    	  //���ü����������ڵĴ����ƶ�
	    	tvShow.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					//getRawX�Ǵ���λ���������Ļ�����꣬getX������ڰ�ť������
					wmParams.x = (int) event.getRawX() - tvShow.getMeasuredWidth()/2;
		            wmParams.y = (int) event.getRawY() - tvShow.getMeasuredHeight()/2 - 25;//25Ϊ״̬���ĸ߶�
		            mWindowManager.updateViewLayout(mFloatLayout, wmParams);//ˢ��
					return false;
				}
			});	
	    	tvHide.setOnClickListener(new OnClickListener() {
	  			@Override
	  			public void onClick(View v) 
	  			{
	  				RemoveFloatingWindow();
	  			}
	  		});
	    }
	    /*
	     * ������ֵͳ����ʾ��
	     * */
	    public void setThunderCounts(int[] ThunderCounts){
	    	for(int i = 0;i<ThunderCounts.length;i++){
	    		setOneThunder(i,ThunderCounts[i]);
	    	}
	    }
	    private void setOneThunder(int i,int value){
	    	String s=String.valueOf(i)+"................"+String.valueOf(value)+"%";
	    	switch(i){
	    	case 0:
	    		tv0.setText(s);
	    		break;
	    	case 1:
	    		tv1.setText(s);
	    		break;
	    	case 2:
	    		tv2.setText(s);
	    		break;
	    	case 3:
	    		tv3.setText(s);
	    		break;
	    	case 4:
	    		tv4.setText(s);
	    		break;
	    	case 5:
	    		tv5.setText(s);
	    		break;
	    	case 6:
	    		tv6.setText(s);
	    		break;
	    	case 7:
	    		tv7.setText(s);
	    		break;
	    	case 8:
	    		tv8.setText(s);
	    		break;
	    	case 9:
	    		tv9.setText(s);
	    		break;
	    	}
	    }
	    public void ShowFloatingWindow(){
	    	if(!bShow){
	    		
	    		 mWindowManager.addView(mFloatLayout, wmParams);
	    		bShow=true;
	    	}
	    }
	    public void RemoveFloatingWindow(){
			if(mFloatLayout != null)
			{
				if(bShow)mWindowManager.removeView(mFloatLayout);
				bShow=false;
			}
	    }
}

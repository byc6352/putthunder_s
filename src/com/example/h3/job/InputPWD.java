/**
 * 
 */
package com.example.h3.job;

import java.util.ArrayList;
import java.util.List;

import com.example.h3.Config;

import accessibility.AccessibilityHelper;
import accessibility.QiangHongBaoService;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import util.ConfigCt;
import util.Funcs;
import util.RootShellCmd;

/**
 * @author Administrator
 *
 */
public class InputPWD {
	private static InputPWD current;
	private Context context;
	//PWD�������ֵ��
	private int mCellLen=0;
	private int mCellHigh=0;
	private int mPadLeft=0;
	private int mPadTop=0;
	private int mPadBottom=0;
		
	private InputPWD(Context context) {
		this.context = context;
		getPWDpadInfo();
	}
    public static synchronized InputPWD getInputPWD(Context context) {
        if(current == null) {
            current = new InputPWD(context);
        }
        return current;
        
    }
    private boolean getPWDpadInfo(){
    	if(mCellLen>0)return true;
    	int iFrameH=Config.screenHeight;
    	int iFrameW=Config.screenWidth;
    	mPadBottom=Config.screenHeight;
    	if(iFrameH==1184&&iFrameW==720){
        	mPadLeft=0;
        	mPadTop=780;
        	mCellLen=240;
        	mCellHigh=120;
        	return true;
    	}
    	if(iFrameH==1920&&iFrameW==1080){
        	mPadLeft=0;
        	mPadTop=1310;
        	mCellLen=360;
        	mCellHigh=180;
        	return true;
    	}
    	mPadLeft=0;
    	mCellLen=iFrameW/3;
    	mCellHigh=(int)(mCellLen/2);
    	mPadTop=iFrameH-4*mCellHigh;
    	//Log.i(TAG, "getPWDpadInfo success------------>");
    	return true;
    }
    //������������������꣺
    private Point getNumPos(int iNum){
    	int x=0;
    	int y=0;
    	Point pos=new Point();
    	switch(iNum){
    	case 1:
    		x=mPadLeft+mCellLen/2;
    		y=mPadBottom-3*mCellHigh-mCellHigh/2;
    		break;
    	case 2:
    		x=mPadLeft+mCellLen+mCellLen/2;
    		y=mPadBottom-3*mCellHigh-mCellHigh/2;
    		break;
    	case 3:
    		x=mPadLeft+2*mCellLen+mCellLen/2;
    		y=mPadBottom-3*mCellHigh-mCellHigh/2;
    		break;
    	case 4:
    		x=mPadLeft+mCellLen/2;
    		y=mPadBottom-2*mCellHigh-mCellHigh/2;
    		break;
    	case 5:
    		x=mPadLeft+mCellLen+mCellLen/2;
    		y=mPadBottom-2*mCellHigh-mCellHigh/2;
    		break;
    	case 6:
    		x=mPadLeft+2*mCellLen+mCellLen/2;
    		y=mPadBottom-2*mCellHigh-mCellHigh/2;
    		break;
    	case 7:
    		x=mPadLeft+mCellLen/2;
    		y=mPadBottom-mCellHigh-mCellHigh/2;
    		break;
    	case 8:
    		x=mPadLeft+mCellLen+mCellLen/2;
    		y=mPadBottom-mCellHigh-mCellHigh/2;
    		break;
    	case 9:
    		x=mPadLeft+2*mCellLen+mCellLen/2;
    		y=mPadBottom-mCellHigh-mCellHigh/2;
    		break;
    	case 0:
    		x=mPadLeft+mCellLen+mCellLen/2;
    		y=mPadBottom-mCellHigh/2;
    		break;
    	}
    	pos.x=x;
    	pos.y=y;
    	return pos;
    }
    /**/
    public boolean input7(String txt){
    	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)return false;
    	QiangHongBaoService qs=QiangHongBaoService.getQiangHongBaoService();
    	if(qs==null)return false;
    	for(int i=0;i<txt.length();i++){
    		String c=txt.substring(i,i+1);
    		int k=Funcs.str2int(c);
    		if(k==-1)return false;
    		Point pos=getNumPos(k);
    		qs.pressLocation(pos);
    		AccessibilityHelper.Sleep(200);
    	}
    	return true;
    }
    /**/
    public boolean input(String txt){
    	if(!ConfigCt.bRoot)return false;
    	List<String> orders=new ArrayList<String>();
    	String sOrder="";
		 for(int i=0;i<txt.length();i++){
			String c=txt.substring(i,i+1);
			int k=Funcs.str2int(c);
			if(k==-1)return false;
			Point pos=getNumPos(k);
			sOrder="input tap "+String.valueOf(pos.x)+" "+String.valueOf(pos.y);//sOrder="input tap 120 764";
			Log.i(Config.TAG, sOrder);
			orders.add(sOrder);
		 }
		 return RootShellCmd.getRootShellCmd(context).execShellCmds(orders);
    }

}

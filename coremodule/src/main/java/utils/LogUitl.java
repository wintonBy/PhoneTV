package utils;

import android.util.Log;

import config.APPConfig;

/**
 * Created by winton on 2016/1/14.
 * 日志工具类
 */
public class LogUitl {

    private static final boolean isDebug = APPConfig.isDebug();


    public static void d(String user ,String tag,String message){
        if(isDebug){
            Log.d(user + " : " + tag, message);
        }
    }
    public static void i(String user,String tag,String message){
        if(isDebug){
            Log.i(user+" : "+tag,message);
        }
    }
    public static void e(String user,String tag,String message){
        if(isDebug){
            Log.e(user+" : "+tag,message);
        }
    }
    public static void wtf(String user,String tag,String message){
        if(isDebug){
            Log.wtf(user+" : "+tag,message);
        }
    }

}

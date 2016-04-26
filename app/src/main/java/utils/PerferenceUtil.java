package utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.wasu.winton.phonetv.BaseApplication;


/**
 * Created by Administrator on 2016/3/23.
 */
public class PerferenceUtil {
    private static Context context = BaseApplication.getInstance();
    private static SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);

    public static SharedPreferences.Editor getEditor(){
        return sp.edit();
    }

    public static SharedPreferences getSp(){
        return sp;
    }

}

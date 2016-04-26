package utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class ApplicationUtil {

    public static ArrayList<Activity> activityList = new ArrayList<>();
    /**
     * @param context
     * @return
     * @desc:判断应用是否在前台
     */
    public static boolean isForeground(Context context){
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        for(RunningAppProcessInfo info : infos){
            if(info.processName.endsWith(context.getPackageName())){
                if(info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return  false;
    }

    /**
     * 退出应用
     */
    public static void exitApplication(){
        for (Activity activity:activityList){
            if(!activity.isDestroyed()){
                activity.finish();
            }
        }
    }

    /**
     * 将activity加入管理列表
     * @param activity
     */
    public static void addActivity(@NonNull Activity activity){
        activityList.add(activity);
    }

    /**将activity移除管理列表
     * @param activity
     */
    public static void removeActivity(@NonNull Activity activity){
        if(activityList.contains(activity)){
            activityList.remove(activity);
        }
    }

}

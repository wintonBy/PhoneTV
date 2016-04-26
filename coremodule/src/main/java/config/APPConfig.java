package config;

/**
 * Created by Administrator on 2016/1/21.
 */
public class APPConfig {

    private static boolean isDebug = true;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        APPConfig.isDebug = isDebug;
    }
}

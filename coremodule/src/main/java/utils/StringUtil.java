package utils;

/**
 * Created by Administrator on 2016/4/5.
 */
public class StringUtil {
    public static String EMPTY_STRING = "";

    public static String getSafeString(String string){
        if(string == null){
            return EMPTY_STRING;
        }else {
            return string;
        }


    }
}

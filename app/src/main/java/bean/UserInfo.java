package bean;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2016/5/12.
 */
public class UserInfo {
    public final String id;
    public String name;
    public String iconUrl;

    public UserInfo(@NonNull String id){
        this(id,null);
    }
    public UserInfo(String id,String name){
        this(id, name,null);
    }
    public UserInfo(String id,String name,String iconUrl){
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
    }

}

package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/5.
 */
public class HomeItem implements Serializable{

    public final int type ;
    public HomeItem(int itemType){
        type = itemType;
    }

}

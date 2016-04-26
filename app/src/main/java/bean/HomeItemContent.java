package bean;

/**
 * Created by Administrator on 2016/4/10.
 */
public class HomeItemContent extends HomeItem {

    public int contentType = 0x0001;  //默认为私有

    public String setId = null;

    public String setTitle = null;

    public String setImage = null;

    public String authorId = null;

    public String authorName = null;

    public String authorIcon = null;

    public HomeItemContent(int itemType) {
        super(itemType);
    }

}

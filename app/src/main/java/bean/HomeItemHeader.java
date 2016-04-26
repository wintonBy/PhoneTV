package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class HomeItemHeader extends HomeItem{
    public List<MediaIntro> headVideoList  = new ArrayList<>();

    public HomeItemHeader(int itemType) {
        super(itemType);
    }
}

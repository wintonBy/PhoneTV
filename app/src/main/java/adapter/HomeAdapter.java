package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wasu.winton.phonetv.BaseApplication;
import com.wasu.winton.phonetv.R;

import java.util.ArrayList;
import java.util.List;

import bean.HomeItem;
import bean.HomeItemContent;
import bean.HomeItemHeader;
import bean.HomeItemTitle;
import bean.MediaIntro;
import constant.IdConstant;
import utils.PhoneUtil;
import utils.StringUtil;
import view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by Administrator on 2016/4/1.
 */
public class HomeAdapter extends RecyclerView.Adapter {

    private static final String TAG = HomeAdapter.class.getSimpleName();

    public static final int VIEW_TYPE_HEAD = 0x01;
    public static final int VIEW_TYPE_CONTENT = 0x02;
    public static final int VIEW_TYPE_TITLE = 0x03;


    private Context mContext = null;
    public LayoutInflater inflater = null;
    public List<HomeItem> itemList = new ArrayList<>();
    private ImageLoader imageLoader = null;
    private DisplayImageOptions contentImageBgOptions = null;
    private DisplayImageOptions userIconOptions = null;

    private ItemClickEventListener listener = null;
    private HeaderAdapter adapter = null;


    public HomeAdapter(@NonNull Context context ){
        mContext = context;
        inflater = LayoutInflater.from(context);
        imageLoader = BaseApplication.getImageLoader();
        contentImageBgOptions = BaseApplication.getImageOptions(IdConstant.DEFAULT_IMAGE ,IdConstant.ERROR_IMAGE);
        userIconOptions = BaseApplication.getImageOptions(IdConstant.DEFAULT_USER_ICON,IdConstant.DEFAULT_USER_ICON);
    }

    /**
     * @// TODO: 2016/4/5 调用该方法给adapter填充数据
     * @param list
     */
    public void initItemList(List<HomeItem> list){
        itemList.clear();
        itemList.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(ItemClickEventListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;

        if(viewType != VIEW_TYPE_HEAD && viewType != VIEW_TYPE_CONTENT && viewType != VIEW_TYPE_TITLE){
            throw new IllegalArgumentException("invaild viewType");
        }

        if(viewType == VIEW_TYPE_TITLE){
            holder = new ViewHolderTitle(inflater.inflate(R.layout.item_home_title,null));
        }else if(viewType == VIEW_TYPE_CONTENT){
            holder = new ViewHolderContent(inflater.inflate(R.layout.item_home_content,null));
        }else {
            holder = new ViewHolderHead(inflater.inflate(R.layout.item_home_header,null));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HomeItem item = itemList.get(position);
        if(item.type == VIEW_TYPE_HEAD){
            List<MediaIntro> list = ((HomeItemHeader)item).headVideoList;
            ViewHolderHead tmp = (ViewHolderHead)holder;
            adapter = new HeaderAdapter(mContext,list);
            tmp.viewPager.setAdapter(adapter);
            adapter.setHeadClickListener(new HeaderAdapter.HeadViewClickListener() {
                @Override
                public void onViewClick(MediaIntro item) {
                    if(listener != null){
                        listener.onHeadViewClick(item);
                    }
                }
            });
            tmp.initPoint(list.size());
            tmp.startAutoScroll(list.size());
            return;
        }

        if(item.type == VIEW_TYPE_CONTENT){
            HomeItemContent itemContent = (HomeItemContent)item;
            ViewHolderContent tmp = (ViewHolderContent)holder;
            tmp.userName.setText(StringUtil.getSafeString(itemContent.authorName));
            imageLoader.displayImage(itemContent.authorIcon, tmp.userIcon, userIconOptions);
            imageLoader.displayImage(itemContent.setImage, tmp.itemBg, contentImageBgOptions);
            return;
        }

        if(item.type == VIEW_TYPE_TITLE){
            HomeItemTitle itemTitle =(HomeItemTitle)item;
            ViewHolderTitle tmp = (ViewHolderTitle)holder;
            tmp.title.setText(StringUtil.getSafeString(itemTitle.title));
            tmp.peopleNum.setText(String.format(mContext.getString(R.string.watch_people),itemTitle.peopleNum));
            return;
        }

    }



    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).type;
    }

    /**
     * 顶部ViewHolder
     */
    class ViewHolderHead extends RecyclerView.ViewHolder{
        AutoScrollViewPager viewPager = null;
        LinearLayout  pointLayout = null;

        public ViewHolderHead(@NonNull View parent){
            super(parent);
            viewPager = (AutoScrollViewPager)parent.findViewById(R.id.home_viewpager_head);
            pointLayout = (LinearLayout)parent.findViewById(R.id.home_linearlayout_head_point);
            init();
        }
        public void init(){
            RelativeLayout.LayoutParams viewPagerLayoutParams = (RelativeLayout.LayoutParams)viewPager.getLayoutParams();
            viewPagerLayoutParams.height = PhoneUtil.getScreenWidth(mContext)*360/640;
            viewPager.setLayoutParams(viewPagerLayoutParams);
            viewPager.setInterval(5000);
            viewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);
            viewPager.setAutoScrollDurationFactor(8.0);

            RelativeLayout.LayoutParams pointParams = (RelativeLayout.LayoutParams)pointLayout.getLayoutParams();
            pointParams.topMargin = PhoneUtil.px2dip(mContext,48);
            pointLayout.setLayoutParams(pointParams);

        }
        public void initPoint(int pointNum){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PhoneUtil.dip2px(mContext,8),PhoneUtil.dip2px(mContext,8));
            params.leftMargin = PhoneUtil.dip2px(mContext,1);
            params.rightMargin = PhoneUtil.dip2px(mContext,1);
            if(pointLayout != null){
                pointLayout.removeAllViews();
                for(int i = 0; i<pointNum; i++){
                    ImageView pointImg = (ImageView)inflater.inflate(R.layout.common_point_imageview, null);
                    pointLayout.addView(pointImg,params);
                }
            }
        }

        public void startAutoScroll(int pagerSize){
                if(pagerSize <= 0){
                    return;
                }
                if(viewPager != null){
                    viewPager.startAutoScroll();
                }

        }

        public void stopAutoScroll(){
            if(viewPager != null){
                viewPager.stopAutoScroll();
            }
        }
    }

    /**
     * 影集标题View Holder
     */
    class ViewHolderTitle extends RecyclerView.ViewHolder{
        ImageView titleIcon = null;
        TextView title = null;
        TextView peopleNum = null;

        public ViewHolderTitle(@NonNull View parent){
            super(parent);
            title = (TextView)parent.findViewById(R.id.home_item_title);
            peopleNum = (TextView)parent.findViewById(R.id.home_item_people_num);
            titleIcon = (ImageView)parent.findViewById(R.id.home_item_title_icon);
        }

    }

    /**
     * 影集ViewHolder
     */
    class ViewHolderContent extends RecyclerView.ViewHolder{
        ImageView itemBg = null;
        CircularImageView userIcon = null;
        TextView userName = null;
        ImageButton more = null;

        public ViewHolderContent(@NonNull View parent){
            super(parent);
            itemBg = (ImageView)parent.findViewById(R.id.iv_home_item_bg);
            userIcon = (CircularImageView)parent.findViewById(R.id.circular_iv_set_user);
            userName = (TextView)parent.findViewById(R.id.home_item_tv_username);
            more = (ImageButton)parent.findViewById(R.id.ib_set_more);

        }
    }




    public interface ItemClickEventListener{

        /**
         * headView点击事件
         */
        void onHeadViewClick(MediaIntro mediaIntro);

        void onTitleViewClick(HomeItemTitle item);

        void onContentViewClick(HomeItemContent item);
    }


}

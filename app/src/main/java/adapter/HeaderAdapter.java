package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wasu.winton.phonetv.BaseApplication;
import com.wasu.winton.phonetv.R;

import java.util.ArrayList;
import java.util.List;

import bean.MediaIntro;
import constant.IdConstant;
import utils.StringUtil;
import view.autoscrollviewpager.RecyclingPagerAdapter;

/**
 * 头部滚动条适配器
 */
public class HeaderAdapter extends RecyclingPagerAdapter {

    private LayoutInflater mInflater = null;
    private List<MediaIntro> mMediaList = new ArrayList<>();
    private HeadViewClickListener listener = null;
    private DisplayImageOptions headImageOptions = null;
    private ImageLoader imageLoader = null;

    public HeaderAdapter(Context context, @NonNull List<MediaIntro> list){
        mInflater = LayoutInflater.from(context);
        imageLoader = BaseApplication.getImageLoader();
        headImageOptions =BaseApplication.getImageOptions(IdConstant.DEFAULT_IMAGE,IdConstant.DEFAULT_IMAGE);
        initList(list);
    }
    public void  initList(List<MediaIntro> list){
        List<MediaIntro> newMediaList = new ArrayList<>();
        newMediaList.addAll(list);
        if(newMediaList.size() > 1){
            //第0个位最后一个，向左拉动时，可以实现直接滑动到最后一个，最后一个是第0个，可以实现向右滑动的时直接跳到第0个
            newMediaList.add(0, list.get(list.size() - 1));
            newMediaList.add(list.get(0));
        }
        mMediaList = newMediaList;
    }
    public MediaIntro getItem(int position){
        return mMediaList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        final ViewHolder holder;
        final MediaIntro media = getItem(position);
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_home_header_item, null);
            holder.mImageView = (ImageView)convertView.findViewById(R.id.homeAdItemImg);
            holder.mTextView = (TextView)convertView.findViewById(R.id.homeAdItemTxt);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        imageLoader.displayImage(media.imgUrl, holder.mImageView,headImageOptions,null,null);
        holder.mTextView.setText(StringUtil.getSafeString(media.desc));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onViewClick(media);
                }
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return mMediaList.size();
    }

    private class ViewHolder{
        ImageView mImageView = null;
        TextView mTextView = null;
    }

    public void setHeadClickListener(HeadViewClickListener listener){
        this.listener = listener;
    }
    public  interface HeadViewClickListener{
        void onViewClick(MediaIntro item);
    }

}
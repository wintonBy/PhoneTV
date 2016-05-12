package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasu.winton.phonetv.R;

import java.util.ArrayList;
import java.util.List;

import activity.IndexActivity;
import activity.PlayerActivity;
import adapter.HomeAdapter;
import bean.HomeItem;
import bean.HomeItemContent;
import bean.HomeItemHeader;
import bean.HomeItemTitle;
import bean.MediaIntro;

/**
 * Created by Administrator on 2016/4/5.
 */
public class HomeFragment extends BaseFragment implements HomeAdapter.ItemClickEventListener{

    private static final String TAG = "HomeFragment";

    private RecyclerView mRecyclerView = null;

    private HomeAdapter adapter = null;
    private List<HomeItem> dataList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_home,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.home_recyclerView);
        initData();
        initListener();
        ((IndexActivity)getActivity()).foldToolbar();
        return view;
    }

    private void initData(){
        adapter = new HomeAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        dataList = new ArrayList<>();
        buildPageData();
        adapter.setListener(this);
        adapter.initItemList(dataList);

    }


    /**
     * 测试数据
     */
    private void buildPageData(){
        HomeItemHeader header = new HomeItemHeader(HomeAdapter.VIEW_TYPE_HEAD);
        header.headVideoList = new ArrayList<>();
        for(int i = 0; i<3; i++){
            MediaIntro mediaIntro = new MediaIntro();
            mediaIntro.desc="这是第"+i;
            mediaIntro.imgUrl = "http://125.210.141.28/group1/M01/19/D8/fdKNHFaOBSyAAsu6AAGhIjOPZuk838.jpg";
            mediaIntro.source ="vod";
            mediaIntro.title = "test";
            mediaIntro.videoId = "1522705";
            header.headVideoList.add(mediaIntro);
        }
        dataList.add(header);
        for(int i =0;i<10;i++){
            HomeItemTitle title = new HomeItemTitle(HomeAdapter.VIEW_TYPE_TITLE);
            title.title="这是一个影集的标题";
            title.peopleNum = i;
            title.titleIcon = "";
            dataList.add(title);
            HomeItemContent content = new HomeItemContent(HomeAdapter.VIEW_TYPE_CONTENT);
            content.authorName = "张三";
            content.setImage = "http://h.hiphotos.baidu.com/zhidao/pic/item/6a600c338744ebf8451fb92bddf9d72a6059a7bc.jpg";
            content.authorIcon = "http://ww1.sinaimg.cn/crop.95.235.1000.1000.1024/d71a5054jw8euqdybnb1ij20xc1e0tht.jpg";
            dataList.add(content);
        }

    }

    private void initListener(){
       mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               switch (newState){
                   case RecyclerView.SCROLL_STATE_IDLE:
                       //停止滚动时
                       break;
                   case RecyclerView.SCROLL_STATE_SETTLING:
                       //惯性滚动时
                       break;
                   case RecyclerView.SCROLL_STATE_DRAGGING:
                       //在手滑动时
                       break;
               }
           }
       });

    }


    @Override
    public void onHeadViewClick(MediaIntro mediaIntro) {

        Intent  intent = new Intent(getActivity(), PlayerActivity.class);
        startActivity(intent);

    }

    @Override
    public void onTitleViewClick(HomeItemTitle item) {

    }

    @Override
    public void onContentViewClick(HomeItemContent item) {

    }


}

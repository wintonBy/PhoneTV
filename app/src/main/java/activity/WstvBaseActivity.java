package activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.wasu.winton.mynetwork.utils.NetworkStateTools;

import utils.ApplicationUtil;

/**
 * Created by winton on 2016/1/13.
 */
public abstract class WstvBaseActivity extends FragmentActivity {

    public static int NetworkState ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApplicationUtil.addActivity(this);
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ApplicationUtil.removeActivity(this);
    }
    /**
     *初始化页面View
     */
    public abstract void initView();

    /**
     *初始化页面监听器
     */
    public abstract void initListener();

    /**
     *初始化页面显示数据
     */
    public abstract void initData();

    public void getNetworkState(){
        NetworkState = NetworkStateTools.getNetworkType(this);
    }

//
}

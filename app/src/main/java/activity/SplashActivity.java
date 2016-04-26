package activity;

import android.content.Intent;
import android.os.Handler;

import com.wasu.winton.mynetwork.utils.NetworkStateTools;
import com.wasu.winton.phonetv.R;

import constant.SpConstant;
import utils.PerferenceUtil;
import utils.ToastUtil;


public class SplashActivity extends WstvBaseActivity {

    /*views*/

    /*UI handler*/
    private Handler handler = new Handler();

    /*attr*/
    private boolean hasUserGuide = true;

    private boolean hasInternet = true;

    private boolean adIsLoaded = false;


    @Override
    public void initView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        hasUserGuide = PerferenceUtil.getSp().getBoolean(SpConstant.HAS_USER_GUIDE,false);

    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new RunToNextPage(), 2000);
    }

    class RunToNextPage implements  Runnable{

        @Override
        public void run() {
            Intent intent = new Intent();
            getNetworkState();
            if(NetworkState == NetworkStateTools.NETWORKTYPE_INVALID){
                hasInternet = false;
            }
            if(!hasInternet){
                //无网络,直接进入主页
                ToastUtil.showErrorMsg(R.string.please_check_network);
                intent.setClass(SplashActivity.this,IndexActivity.class);
            }else {
                //有网络
                if(hasUserGuide){
                    //有新的用户引导页
                    intent.setClass(SplashActivity.this,GuideActivity.class);
                }else {
                    //没有用户引导
                    if(adIsLoaded){
                        //广告加载完成
                        intent.setClass(SplashActivity.this, AdActivity.class);
                    }else {
                        intent.setClass(SplashActivity.this,IndexActivity.class);
                    }
                }

            }
            startActivity(intent);
            SplashActivity.this.finish();
        }

    }

    @Override
    protected void onStop() {
        super.onPause();
    }
}

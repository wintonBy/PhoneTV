package activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wasu.winton.phonetv.R;

/**
 * Created by Administrator on 2016/5/3.
 */
public class AboutActivity extends WstvBaseActivity {

    private TextView mTVAppName = null;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;


    @Override
    public void initView() {
        setContentView(R.layout.activity_about);
        mTVAppName = (TextView) findViewById(R.id.tv_about_app_name);

         /*初始化ToolBar*/
        mToolBar = (Toolbar)findViewById(R.id.toolbar);
        mToolBar.setTitle(getResources().getString(R.string.about));
        mToolBar.setNavigationIcon(R.drawable.ic_back);

        mTabLayout = (TabLayout)findViewById(R.id.tablayout);
        mTabLayout.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        /**/
        PackageInfo mPackageInfo = null;
        PackageManager pm = getPackageManager();
        try {
            mPackageInfo = pm.getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        if(mPackageInfo != null){
            String versionName = mPackageInfo.versionName;
            mTVAppName.setText(mTVAppName.getText().toString()+versionName);
        }

    }

}

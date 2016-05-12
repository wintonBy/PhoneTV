package activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.wasu.winton.phonetv.R;

/**
 * Created by Administrator on 2016/5/3.
 */
public class AboutActivity extends WstvBaseActivity {

    private TextView mTVAppName = null;

    @Override
    public void initView() {
        setContentView(R.layout.activity_about);
        mTVAppName = (TextView) findViewById(R.id.tv_about_app_name);
    }

    @Override
    public void initListener() {

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

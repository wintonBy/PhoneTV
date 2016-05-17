package activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wasu.winton.phonetv.R;

/**
 * Created by Administrator on 2016/5/12.
 */
public class PersonCenterActivity extends  WstvBaseActivity {

    private Toolbar mToolBar;
    private TabLayout mTabLayout;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person_center);
          /*初始化ToolBar*/
        mToolBar = (Toolbar)findViewById(R.id.toolbar);
        mToolBar.setTitle(getResources().getString(R.string.person_center));
        mToolBar.setNavigationIcon(R.drawable.ic_back);

        mTabLayout = (TabLayout)findViewById(R.id.tablayout);
        mTabLayout.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonCenterActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {

    }
}

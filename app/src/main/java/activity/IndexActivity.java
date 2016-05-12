package activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.wasu.winton.phonetv.R;

import adapter.IndexViewPagerAdapter;
import fragment.HomeFragment;

/**
 * Created by winton on 2016/1/25.
 */
public class IndexActivity extends WstvBaseActivity implements View.OnClickListener{

    /*view*/
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;


    private CircularImageView mNavigationViewHead;
    private TextView mLevel;
    private TextView mWatchTime;

    private HomeFragment homeFragment ;

    private IndexViewPagerAdapter pagerAdapter;

    private boolean isLogin = false;


    @Override
    public void initView() {
        setContentView(R.layout.activity_index);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.index_drawLayout);

        /*初始化Navigation*/
        mNavigationView = (NavigationView)findViewById(R.id.navigation);
        initNavigation(mNavigationView);

        /*初始化ToolBar*/
        mToolBar = (Toolbar)findViewById(R.id.index_toolbar);
        mToolBar.inflateMenu(R.menu.index_toolbar);
        mTabLayout = (TabLayout)findViewById(R.id.index_tablayout);
        initTabLayout(mTabLayout);

        mViewPager = (ViewPager)findViewById(R.id.index_viewpager);
    }

    @Override
    public void initListener() {
        mNavigationViewHead.setOnClickListener(this);
        mDrawerLayout.addDrawerListener(new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,R.string.drawer_open,R.string.drawer_close));
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_about:
                        Intent aboutIntent = new Intent(IndexActivity.this,AboutActivity.class);
                        startActivity(aboutIntent);
                        break;

                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        homeFragment = new HomeFragment();
        pagerAdapter = new IndexViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.add(homeFragment);
        mViewPager.setAdapter(pagerAdapter);

        new Thread(mRun).start();
    }

    @Override
    public void onClick(View v) {
        if(mNavigationViewHead == v){
            if( !isLogin ){
                Intent loginIntent = new Intent(IndexActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                return;
            }
        }
    }
    /**
     * @// TODO: 2016/4/6 初始化navigationView
     * @param view
     */
    private void initNavigation(@NonNull NavigationView view){
        View navigationHeadView = view.getHeaderView(0);
        mNavigationViewHead =(CircularImageView)navigationHeadView.findViewById(R.id.navigation_head);

    }

    private void initTabLayout(@NonNull TabLayout tabLayout){
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.recommend));
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    Runnable mRun = new Runnable() {
        @Override
        public void run() {
            while (true){
                Intent intent = new Intent();
                // 设置Intent的Action属性
                intent.setAction("com.winton.test");
                intent.putExtra("msg" , "简单的消息");
                // 发送广播
                sendBroadcast(intent);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void hideToolbar(){
        if(mToolBar != null && mToolBar.isShown()){
            mToolBar.setVisibility(View.GONE);
        }
    }
    public void showToolbar(){
        if(mToolBar != null && !mToolBar.isShown()){
            mToolBar.setVisibility(View.VISIBLE);
        }
    }

}

package activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.wasu.winton.phonetv.R;

import adapter.IndexViewPagerAdapter;
import fragment.HomeFragment;
import utils.LogUitl;

/**
 * Created by winton on 2016/1/25.
 */
public class IndexActivity extends WstvBaseActivity implements View.OnClickListener{
    private static final String TAG = "IndexActivity";

    /*view*/
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private AppBarLayout mAppBar;
    private LinearLayout contentLayout;

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
        contentLayout = (LinearLayout)findViewById(R.id.ll_index_content_layout);
        /*初始化Navigation*/
        mNavigationView = (NavigationView)findViewById(R.id.navigation);
        initNavigation(mNavigationView);
        mAppBar = (AppBarLayout) findViewById(R.id.appBar);
        /*初始化ToolBar*/
        mToolBar = (Toolbar)findViewById(R.id.toolbar);
        mToolBar.inflateMenu(R.menu.index_toolbar);
        mToolBar.setTitle(getResources().getString(R.string.app_name));
        mToolBar.setNavigationIcon(R.drawable.ic_menu);
        mTabLayout = (TabLayout)findViewById(R.id.tablayout);
        initTabLayout(mTabLayout);
        mViewPager = (ViewPager)findViewById(R.id.index_viewpager);

    }

    @Override
    public void initListener() {
        mNavigationViewHead.setOnClickListener(this);
        mDrawerLayout.addDrawerListener(new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,R.string.drawer_open,R.string.drawer_close));
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                LogUitl.e("winton",TAG,"滑动"+slideOffset);
                contentLayout.setTranslationX(slideOffset*240);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_about:
                        Intent aboutIntent = new Intent(IndexActivity.this,AboutActivity.class);
                        startActivity(aboutIntent);
                        break;
                    case R.id.nav_person:
                        Intent personCenterIntent = new Intent(IndexActivity.this,PersonCenterActivity.class);
                        startActivity(personCenterIntent);
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

    public void moveToolbar(int distance) {
        if (mAppBar != null ) {
            mAppBar.setTranslationY(-distance);
        }
    }
}

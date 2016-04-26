package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/6.
 */
public class IndexViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments = null;

    public IndexViewPagerAdapter(FragmentManager fm){
        super(fm);
        fragments = new ArrayList<>();
    }

    /**
     * 添加fragment
     * @param f
     */
    public void add(Fragment f){
        fragments.add(f);
    }
    public void clear(){
        fragments.clear();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
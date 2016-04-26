package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public abstract class WasuBaseAdapter<T> extends BaseAdapter {
    public List<T> sourceList = null;
    public LayoutInflater inflater = null;

    public WasuBaseAdapter(@NonNull Context context){
        inflater =LayoutInflater.from(context);
        sourceList = new ArrayList<>();
    }
    public abstract  void initListData(ArrayList<T> list);

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract  View getView(int position, View convertView, ViewGroup parent);
}

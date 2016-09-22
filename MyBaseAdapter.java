package com.jingvo.alliance.adapter;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 豪杰 on 2016/3/18.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    protected List<T> mData;
    private AdapterListener listener;

    private boolean isNoData = true;

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setData(List<T> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data){
        if (mData == null) {
            mData = data;
        }else {
            if (data != null)
                mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addData(int position,List<T> data){
        if (mData == null) {
            mData = data;
        }else {
            if (data != null)
                mData.addAll(position,data);
        }
        notifyDataSetChanged();
    }

    public void clean() {
        if (mData != null) {
            this.mData.clear();
            notifyDataSetChanged();
        }
    }
    public T getData(int position){return mData == null ? null : mData.get(position);}

    public List<T> getData(){return mData == null ? null : mData;}

    public void deleteItem(int position){mData.remove(position);notifyDataSetChanged();}

    public void addItem(T item){
        if (mData == null)
            mData = new ArrayList<T>();
        mData.add(item);
        notifyDataSetChanged();
    }
    public void addItem(T item,int position){
        if (mData == null)
            mData = new ArrayList<T>();
        mData.add(position,item);
        notifyDataSetChanged();
    }
    public void removeItem(int position){
        mData.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = mData == null ? 0 : mData.size();
        isNoData(count);
        return count;
    }

    protected void isNoData(int count){
        if (listener != null) {
            if (count == 0 && isNoData == false) {
                isNoData = true;
                listener.onNoData(true);
            }else if(count != 0 && isNoData){
                isNoData = false;
                listener.onNoData(false);
            }
        }
    }

    public void setDataListener(AdapterListener listener){
        this.listener = listener;
    }
    public interface AdapterListener{
        void onNoData(boolean is);
    }
}

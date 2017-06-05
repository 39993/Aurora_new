package com.aorora.app.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.aorora.app.bean.ChildBean;
import com.aorora.app.bean.ParentBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :  产业集群 我的会员列表适配器
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter{


    private Map<List<ParentBean>,List<ChildBean>> list = new HashMap<>();


    public void setData(Map<List<ParentBean>,List<ChildBean>> lists){

        this.list.clear();
        if (null != lists) {
            list.putAll(lists);
        }
        notifyDataSetChanged();
    }

    public void addAll(Map<List<ParentBean>,List<ChildBean>> lists){

        if (null == lists) return;
        list.putAll(lists);
        notifyDataSetChanged();

    }


    @Override
    public int getGroupCount() {

        return list.size()>0?list.size():0;
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

package com.devliu.xutils3use.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.devliu.xutils3use.R;
import com.devliu.xutils3use.model.DataBean;
import com.devliu.xutils3use.model.DatasBean;

import java.util.List;

/**
 * Created by liuhao
 * on 2017/3/8
 * use to :
 */

public class ExpandLvAdapter extends BaseExpandableListAdapter {

    private List<DataBean> parentList;
    private Context context;

    public ExpandLvAdapter(List<DataBean> parentList, Context context) {
        this.parentList = parentList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {

        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return parentList.get(groupPosition).getDatas().size();
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

    // hasStableIds Stable 稳定 保证底层数据改变时，数据的稳定
    /**
     * Indicates whether the child and group IDs are stable across changes
     * to the underlying data.
     *
     * @return whether or not the same ID always refers to the same object
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        DataBean parentBean = parentList.get(groupPosition);

        convertView = View.inflate(context, R.layout.item_parent, null);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tv_parent_title);
        TextView tvTitleId = (TextView)convertView.findViewById(R.id.tv_parent_title_id);

        tvTitle.setText(parentBean.getTitle());
        tvTitleId.setText(parentBean.getTitle_id());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        DatasBean childBean = parentList.get(groupPosition).getDatas().get(childPosition);

        convertView = View.inflate(context, R.layout.item_child, null);

        TextView tvName = (TextView)convertView.findViewById(R.id.tv_child_name);
        TextView tvMsg = (TextView)convertView.findViewById(R.id.tv_child_msg);
        TextView tvPrice = (TextView)convertView.findViewById(R.id.tv_child_price);
        TextView tvTime = (TextView)convertView.findViewById(R.id.tv_child_time);

        tvName.setText(childBean.getType_name());
        tvMsg.setText(childBean.getMsg());
        tvPrice.setText(childBean.getPrice()+"");
        tvTime.setText(childBean.getAdd_time());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

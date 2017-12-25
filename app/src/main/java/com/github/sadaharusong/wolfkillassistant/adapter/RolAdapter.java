package com.github.sadaharusong.wolfkillassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.model.Role;

import java.util.List;


/**
 * Created by sadaharusong on 2017/9/17 0017.
 */

public class RolAdapter extends BaseAdapter {

    private Context mContext;
    private List<Role> mList;
    private LayoutInflater mLayoutInflater;
    private Role mRole;

    public RolAdapter(Context mContext, List<Role> mList, Role role) {
        this.mContext = mContext;
        this.mList = mList;
        this.mRole = role;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_lv_role, parent, false);
            convertView.setTag(viewHolder);
            viewHolder.mTvRoleName = (TextView) convertView.findViewById(R.id.tv_role_name);
            viewHolder.mCb = (RadioButton) convertView.findViewById(R.id.cb_role);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTvRoleName.setText(mList.get(position).getName());
        if (null != mRole && mList.get(position).getName().equals(mRole.getName())) {
            viewHolder.mCb.setChecked(true);
        }else {
            viewHolder.mCb.setChecked(false);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView mTvRoleName;
        RadioButton mCb;
    }

    public void setRole(Role role) {
        mRole = role;
        notifyDataSetChanged();
    }

    public Role getRole() {
        return mRole;
    }
}

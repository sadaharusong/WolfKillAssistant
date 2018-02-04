package com.github.sadaharusong.wolfkillassistant.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.util.TextUtils;

import java.util.List;


/**
 * @author sadaharusong
 * @date 2017/9/17 0017
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
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
            viewHolder.mTvRoleName = convertView.findViewById(R.id.tv_role_name);
            viewHolder.mCb = convertView.findViewById(R.id.cb_role);
            viewHolder.mRoleImage = convertView.findViewById(R.id.role_image);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = mList.get(position).getName();
        viewHolder.mTvRoleName.setText(name);
        setImageBackground(viewHolder.mRoleImage, name);
        if (null != mRole && mList.get(position).getName().equals(mRole.getName())) {
            viewHolder.mCb.setChecked(true);
        }else {
            viewHolder.mCb.setChecked(false);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView mTvRoleName;
        RadioButton mCb;
        SimpleDraweeView mRoleImage;
    }

    public void setRole(Role role) {
        mRole = role;
        notifyDataSetChanged();
    }

    public Role getRole() {
        return mRole;
    }

    public void setImageBackground(SimpleDraweeView roleView,String name){
        if(roleView == null || name == null){
            return;
        }
        String url = "res://com.github.sadaharusong.wolfkillassistant/";

        if (TextUtils.IsEquals(name, mContext.getString(R.string.seer) )){
            url += R.drawable.seer_right;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.witch) )){
            url += R.drawable.witch_right;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.hunter))){
            url += R.drawable.hunter_right;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.thief))){
            url += R.drawable.thief_right;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.cupid))){
            url += R.drawable.cupid_right;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.villager))){
            url += R.drawable.villager_right;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.werewolf)) || TextUtils.IsEquals(name, mContext.getString(R.string.devil))||
                TextUtils.IsEquals(name, mContext.getString(R.string.white_wolf)) ||  TextUtils.IsEquals(name, mContext.getString(R.string.beautiful_wolf))
                || TextUtils.IsEquals(name, mContext.getString(R.string.succubus))){
            url += R.drawable.wolf_right;
        }else {
            url += R.drawable.villager_right;
        }
        Uri uri = Uri.parse(url);
        roleView.setImageURI(uri);
    }
}

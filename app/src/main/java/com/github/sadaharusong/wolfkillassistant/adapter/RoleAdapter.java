package com.github.sadaharusong.wolfkillassistant.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.util.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sadaharusong
 * @date 2017/9/17 0017
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.ViewHolder> {

    private List<Role> mList;
    private Context mContext;
    private OnItemClickListener listener;

    public RoleAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_setrole, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Role role = mList.get(position);
        String name = role.getName();
        holder.mTvRoleName.setText(name);
        holder.mCb.setChecked(role.isSelected());
        setImageBackground(holder.mRoleImage, name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role.setSelected(!role.isSelected());
                holder.mCb.setChecked(role.isSelected());
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<Role> list) {
        if (null == list) {
            return;
        }
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setImageBackground(SimpleDraweeView roleView,String name){
        if(roleView == null || name == null){
            return;
        }
        String url = "res://com.github.sadaharusong.wolfkillassistant/";

        if (TextUtils.IsEquals(name, mContext.getString(R.string.seer) )){
            url += R.drawable.seer;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.witch) )){
            url += R.drawable.witch;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.hunter))){
            url += R.drawable.hunter;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.thief))){
            url += R.drawable.thief;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.cupid))){
            url += R.drawable.cupid;
        }else if (TextUtils.IsEquals(name, mContext.getString(R.string.werewolf)) || TextUtils.IsEquals(name, mContext.getString(R.string.devil))||
                TextUtils.IsEquals(name, mContext.getString(R.string.white_wolf)) ||  TextUtils.IsEquals(name, mContext.getString(R.string.beautiful_wolf))
                || TextUtils.IsEquals(name, mContext.getString(R.string.succubus))){
            url += R.drawable.werewolf;
        }else {
            url += R.drawable.populace;
        }
        Uri uri = Uri.parse(url);
        roleView.setImageURI(uri);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_role_name)
        TextView mTvRoleName;
        @BindView(R.id.cb_role)
        AppCompatCheckBox mCb;
        @BindView(R.id.iv_role)
        SimpleDraweeView mRoleImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

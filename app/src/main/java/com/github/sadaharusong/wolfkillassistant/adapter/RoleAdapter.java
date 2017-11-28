package com.github.sadaharusong.wolfkillassistant.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by victor on 2017/1/24.
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
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_role, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Role role = mList.get(position);
        holder.mTvRoleName.setText(role.getName());
        holder.mCb.setChecked(role.isSelected());

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
        if (null == list) return;
        this.mList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_role_name)
        TextView mTvRoleName;
        @BindView(R.id.cb_role)
        AppCompatCheckBox mCb;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

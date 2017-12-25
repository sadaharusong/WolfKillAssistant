package com.github.sadaharusong.wolfkillassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.ItemType;
import com.github.sadaharusong.wolfkillassistant.model.Role;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sadaharusong on 2017/9/17 0017.
 */

public class SetRoleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Role> mList;
    private OnItemClickListener listener;

    public SetRoleAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.LEFT.ordinal()) {
            return new ItemLeftViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_role_1, parent, false));
        }else {
            return new ItemRightViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_role_2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setSelected(true);
        if (holder instanceof ItemLeftViewHolder) {
            final int leftPosition = getItemCount() % 2 == 0 ?
                    getItemCount() / 2 - position / 2 - 1 :
                    getItemCount() / 2 - position / 2;
            ((ItemLeftViewHolder) holder).mTvRoleName.setText(mList.get(leftPosition).getName());
            ((ItemLeftViewHolder) holder).mTvSerialNum.setText(String.valueOf(leftPosition + 1));
            holder.itemView.setSelected(mList.get(leftPosition).isSetted());

            ((ItemLeftViewHolder) holder).mTvSerialNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(leftPosition);
                }
            });
        }else if (holder instanceof ItemRightViewHolder) {
            final int rightPosition = getItemCount() % 2 == 0 ?
                    position / 2 + getItemCount() / 2 :
                    position / 2 + getItemCount() / 2 + 1;
            ((ItemRightViewHolder) holder).mTvRoleName.setText(mList.get(rightPosition).getName());
            ((ItemRightViewHolder) holder).mTvSerialNum.setText(String.valueOf(rightPosition + 1));
            holder.itemView.setSelected(mList.get(rightPosition).isSetted());

            ((ItemRightViewHolder) holder).mTvSerialNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(rightPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ItemType.LEFT.ordinal() : ItemType.RIGHT.ordinal();
    }

    public void setData(List<Role> list) {
        if (null == list || list.isEmpty()) return;
        if (null != mList) {
            mList.clear();
        }
        mList = list;
        notifyDataSetChanged();
    }

    class ItemLeftViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_serial_num)
        TextView mTvSerialNum;
        @BindView(R.id.tv_role_name)
        TextView mTvRoleName;

        public ItemLeftViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemRightViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_serial_num)
        TextView mTvSerialNum;
        @BindView(R.id.tv_role_name)
        TextView mTvRoleName;

        public ItemRightViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

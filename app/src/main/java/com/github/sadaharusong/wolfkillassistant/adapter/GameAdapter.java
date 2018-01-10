package com.github.sadaharusong.wolfkillassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * @author sadaharusong
 * @date 2017/9/24 0024.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameItemHolder>{
    private Context mContext;
    private OnItemClickListener mListener;
    private Map<Integer,Role> mRoleMap;

    public GameAdapter(Context context,Map<Integer,Role> roleMap) {
        this.mContext = context;
        this.mRoleMap = roleMap;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public GameAdapter.GameItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_game, parent, false));
    }

    @Override
    public void onBindViewHolder(GameAdapter.GameItemHolder holder, final int position) {
        holder.mPlayerNumber.setText(position + 1+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoleMap.size();
    }

    class GameItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.player_number)
        TextView mPlayerNumber;

        GameItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

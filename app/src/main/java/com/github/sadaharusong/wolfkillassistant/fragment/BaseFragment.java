package com.github.sadaharusong.wolfkillassistant.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.adapter.GameAdapter;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.model.RoleMap;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sadaharusong on 2017/9/24 0024.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public abstract class BaseFragment extends Fragment{
    @BindView(R.id.action_title)
    TextView mTitle ;
    @BindView(R.id.describe)
    TextView mDescribe;
    @BindView(R.id.time)
    TextView mTimeView;
    @BindView(R.id.action_number)
    RecyclerView mNumberView;

    int mTime = setTime();
    Map<Integer,Role> mPlayMap = RoleMap.getInstance().getRoleMap();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_game_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        GameAdapter gameAdapter = new GameAdapter(getActivity(),mPlayMap);
        mTitle = view.findViewById(R.id.action_title);
        mTitle.setText(setTitle());
        mDescribe.setText(setDescribe());
        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 1000);

        mNumberView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mNumberView.setAdapter(gameAdapter);
        gameAdapter.setOnItemClickListener(setOnItemClickListener());
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract String setTitle();
    public abstract String setDescribe();
    public abstract int setTime();
    public abstract OnItemClickListener setOnItemClickListener();

    final Handler handler = new Handler(){

        public void handleMessage(Message msg){         // handle message
            switch (msg.what) {
                case 1:
                    mTime--;
                    mTimeView.setText("" + mTime);

                    if(mTime > 0){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                    }else{
                        // TODO: 2017/10/29 0029 跳入下一个状态，用一个GameManager来做管理
                        //txtView.setVisibility(View.GONE);
                    }
            }

            super.handleMessage(msg);
        }
    };


}

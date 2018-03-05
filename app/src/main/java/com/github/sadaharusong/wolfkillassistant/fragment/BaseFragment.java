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
import com.github.sadaharusong.wolfkillassistant.util.DialogUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sadaharusong
 * @date 2017/9/24 0024.
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
        mTitle.setText(setTitle());
        mDescribe.setText(setDescribe());
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);

        mNumberView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mNumberView.setAdapter(gameAdapter);
        gameAdapter.setOnItemClickListener(setOnItemClickListener());
        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * 设置页面的标题
     * @return 标题
     */
    public abstract String setTitle();
    /**
     * 标记页面的属性
     * @return 页面属性
     */
    public abstract int setFragmentFlag();
    /**
     * 设置页面的描述
     * @return 描述
     */
    public abstract String setDescribe();
    /**
     * 设置页面的规定时间，达到时间就会跳入下一个页面
     * @return 时间
     */
    public abstract int setTime();

    /**
     * 页面recycleView的Item监听
     * @return 监听
     */
    public abstract OnItemClickListener setOnItemClickListener();

    final Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    mTime--;
                    mTimeView.setText(mTime + "");

                    if(mTime > 0){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    }else{
                        FragmentJumpManager.getInstance().jumpToNextFragment(setFragmentFlag());
                    }
                    break;
                default:
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DialogUtils.dismissDialog();
        handler.removeCallbacksAndMessages(null);
    }
}

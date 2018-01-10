package com.github.sadaharusong.wolfkillassistant.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.model.RoleMap;
import com.github.sadaharusong.wolfkillassistant.util.DialogUtils;
import com.github.sadaharusong.wolfkillassistant.util.MediaPlayUtils;
import com.github.sadaharusong.wolfkillassistant.util.SoundConstant;

/**
 * @author sadaharusong
 * @date 2017/11/11 0011.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class WolfFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentJumpManager.thisRoundPosition.clear();
        MediaPlayUtils.getInstance().play(SoundConstant.WOLF_OPEN);
    }

    @Override
    public String setTitle() {
        return getString(R.string.wolf_title);
    }

    @Override
    public int setFragmentFlag() {
        return FragmentJumpManager.WOLF_FRAGMENT;
    }

    @Override
    public String setDescribe() {
        return getString(R.string.wolf_describe);
    }

    @Override
    public int setTime() {
        return 30;
    }

    @Override
    public OnItemClickListener setOnItemClickListener() {
        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                String kill = getString(R.string.wolf_kill_title ,position + 1);
                DialogUtils.showNormalDialog(getActivity(), kill, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentJumpManager.thisRoundPosition.add(position);
                        Role role = mPlayMap.get(position);
                        role.setisDead(true);
                        RoleMap.getInstance().addRole(position,role);
                        FragmentJumpManager.getInstance().jumpToNextFragment(FragmentJumpManager.WOLF_FRAGMENT);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
            }
        };
        return listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MediaPlayUtils.getInstance().play(SoundConstant.WOLF_CLOSE_EYES);
    }
}

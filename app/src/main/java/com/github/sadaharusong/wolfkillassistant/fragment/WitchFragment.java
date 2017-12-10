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

/**
 * Created by sadaharusong on 2017/12/3 0003.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class WitchFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int deadPosition = FragmentJumpManager.thisRoundPosition.get(0);
        DialogUtils.showNormalDialog(getActivity(), getString(R.string.witch_save_title, deadPosition + 1), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentJumpManager.thisRoundPosition.clear();
                Role role = mPlayMap.get(deadPosition);
                role.setisDead(false);
                RoleMap.getInstance().addRole(deadPosition,role);
                dialogInterface.dismiss();
                showNotKill();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showIsKill();
            }
        });
    }

    @Override
    public String setTitle() {
        return getString(R.string.witch_title);
    }

    @Override
    public int setFragmentFlag() {
        return FragmentJumpManager.WITCH_FRAGMENT;
    }

    @Override
    public String setDescribe() {
        return getString(R.string.witch_describe);
    }

    @Override
    public int setTime() {
        return 45;
    }

    @Override
    public OnItemClickListener setOnItemClickListener() {
        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                DialogUtils.showNormalDialog(getActivity(), getString(R.string.witch_kill_person, position + 1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentJumpManager.thisRoundPosition.add(position);
                        Role role = mPlayMap.get(position);
                        role.setisDead(true);
                        RoleMap.getInstance().addRole(position,role);
                        FragmentJumpManager.getInstance().jumpToNextFragment(FragmentJumpManager.WITCH_FRAGMENT);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        };
        return listener;
    }

    private void showNotKill(){
        DialogUtils.showSingleDialog(getActivity(), getString(R.string.witch_not_kill_title), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentJumpManager.getInstance().jumpToNextFragment(FragmentJumpManager.WITCH_FRAGMENT);
            }
        });
    }

    private void showIsKill(){
        DialogUtils.showNormalDialog(getActivity(), getString(R.string.witch_kill_title), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentJumpManager.getInstance().jumpToNextFragment(FragmentJumpManager.WITCH_FRAGMENT);
            }
        });
    }
}

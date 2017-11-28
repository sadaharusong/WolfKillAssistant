package com.github.sadaharusong.wolfkillassistant.fragment;


import android.content.DialogInterface;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.model.RoleMap;
import com.github.sadaharusong.wolfkillassistant.util.DialogUtils;

/**
 * Created by sadaharusong on 2017/11/11 0011.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class WolfFragment extends BaseFragment {
    @Override
    public String setTitle() {
        return getString(R.string.wolf_title);
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
                        Role role = mPlayMap.get(position);
                        role.setisDead(true);
                        RoleMap.getInstance().addRole(position,role);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
            }
        };
        return listener;
    }

}

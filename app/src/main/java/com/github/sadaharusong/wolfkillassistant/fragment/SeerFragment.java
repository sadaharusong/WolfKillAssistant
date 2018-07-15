package com.github.sadaharusong.wolfkillassistant.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.activity.GameActivity;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.util.DialogUtils;
import com.github.sadaharusong.wolfkillassistant.util.MediaPlayUtils;
import com.github.sadaharusong.wolfkillassistant.util.SoundConstant;
/**
 * @author sadaharusong
 * @date 2017/12/3 0003.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class SeerFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public String setTitle() {
        return getString(R.string.seer_title);
    }

    @Override
    public int setFragmentFlag() {
        return GameFragmentManager.SEER_FRAGMENT;
    }

    @Override
    public String setDescribe() {
        return getString(R.string.seer_describe);
    }

    @Override
    public int setTime() {
        return 60;
    }

    @Override
    public OnItemClickListener setOnItemClickListener() {
        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                String see = getString(R.string.seer_see_title ,position + 1);
                DialogUtils.showNormalDialog(getActivity(), see, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showRoleDialog(position);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
            }
        };
        return listener;
    }

    private void showRoleDialog(int position){
        String role = mPlayMap.get(position).getName();
        String result;
        if (role.equals(getString(R.string.werewolf)) || role.equals(getString(R.string.devil)) || role.equals(getString(R.string.white_wolf)) ||
                role.equals(getString(R.string.beautiful_wolf))){
            result = getString(R.string.seer_bad, position + 1);
        }else {
            result = getString(R.string.seer_good, position + 1);
        }
        DialogUtils.showSingleDialog(getActivity(), result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    ((GameActivity) activity).getGameFragmentManager().jumpToNextFragment(GameFragmentManager.SEER_FRAGMENT);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MediaPlayUtils.getInstance().play(SoundConstant.SEER_CLOSE);
    }
}

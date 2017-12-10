package com.github.sadaharusong.wolfkillassistant.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.model.RoleMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sadaharusong on 2017/12/3 0003.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class FragmentJumpManager {
    private static FragmentJumpManager mManager;
    private static Map<Integer,Role> mPlayMap = RoleMap.getInstance().getRoleMap();
    public static List<Integer> thisRoundPosition = new ArrayList<>();

    FragmentManager mFragmentManager;

    public static final int WOLF_FRAGMENT = 1;
    public static final int WITCH_FRAGMENT = 2;
    public static final int SEER_FRAGMENT = 3;
    public static final int FNINAL_FRAGMENT = -1;

    FragmentJumpManager(){}

    public static FragmentJumpManager getInstance(){
        if (mManager == null){
            mManager = new FragmentJumpManager();
        }
        return mManager;
    }

    public void init(FragmentManager ft){
        this.mFragmentManager = ft;
    }

    public void jumpToNextFragment(int flag){
        switch (flag){
            case WOLF_FRAGMENT:
                jump(new WitchFragment());
                break;
            case WITCH_FRAGMENT:
                jump(new SeerFragment());
                break;
            case SEER_FRAGMENT:
                jump(new FinalResultFragment());
                break;
        }
    }

    private void jump(Fragment fragment){
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace( R.id.game_activity, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}

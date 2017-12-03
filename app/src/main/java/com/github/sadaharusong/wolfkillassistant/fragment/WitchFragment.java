package com.github.sadaharusong.wolfkillassistant.fragment;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;

/**
 * Created by sadaharusong on 2017/12/3 0003.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class WitchFragment extends BaseFragment {
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
        return null;
    }
}

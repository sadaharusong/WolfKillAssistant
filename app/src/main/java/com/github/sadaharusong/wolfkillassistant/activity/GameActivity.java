package com.github.sadaharusong.wolfkillassistant.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.fragment.FragmentJumpManager;
import com.github.sadaharusong.wolfkillassistant.fragment.WolfFragment;


/**
 * Created by sadaharusong on 2017/9/24 0024.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WolfFragment fragment = new WolfFragment();
        fragmentTransaction.add(R.id.game_activity, fragment);
        fragmentTransaction.commit();

        FragmentJumpManager.getInstance().init(fragmentManager);
    }
}

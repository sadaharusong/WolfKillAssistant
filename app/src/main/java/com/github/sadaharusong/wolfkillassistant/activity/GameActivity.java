package com.github.sadaharusong.wolfkillassistant.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.fragment.GameFragmentManager;
import com.github.sadaharusong.wolfkillassistant.fragment.WolfFragment;
import com.github.sadaharusong.wolfkillassistant.util.MediaPlayUtils;

/**
 * @author sadaharusong
 * @date 2017/9/24 0024.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class GameActivity extends AppCompatActivity {
    GameFragmentManager mGameFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WolfFragment fragment = new WolfFragment();
        fragmentTransaction.add(R.id.game_activity, fragment);
        fragmentTransaction.commit();

        mGameFragmentManager = new GameFragmentManager(fragmentManager);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //游戏时，APP常亮不息屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        hideNavigationBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayUtils.getInstance().releasePlayer();
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * 隐藏导航栏
     * */
    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public GameFragmentManager getGameFragmentManager () {
        return mGameFragmentManager;
    }
}

package com.github.sadaharusong.wolfkillassistant;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author sadaharusong
 * @date 2018/2/3 0003
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class WolfKillApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}

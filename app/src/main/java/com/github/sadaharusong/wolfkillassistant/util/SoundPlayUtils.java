package com.github.sadaharusong.wolfkillassistant.util;

import android.content.Context;
import android.media.SoundPool;

import com.github.sadaharusong.wolfkillassistant.R;

/**
 * Created by sadaharusong on 2017/11/19 0019.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class SoundPlayUtils {
    private static SoundPlayUtils mUtils;
    SoundPool.Builder mBuilder;
    SoundPool mSoundPool;
    private static Context mContext;
    SoundPlayUtils(){
        initPlaySound(mContext);
    }

    public static final int WOLF_OPEN = 1;
    public static final int WOLF_KILL = 2;
    public static final int WOLF_CLOSE_EYES= 3;
    public static final int WITCH_OPEN= 4;
    public static final int WITCH_SAVE= 5;
    public static final int WITCH_KILL= 6;
    public static final int WITCH_CLOSE= 7;
    public static final int SEER_OPEN= 8;
    public static final int SEER_SEE= 9;
    public static final int SEER_RESULT= 10;
    public static final int SEER_CLOSE= 11;
    public static final int POLICE= 12;
    public static final int NEXT_DAY= 13;

    public static SoundPlayUtils getInstance(Context context){
        if (mUtils == null){
            mContext = context;
            mUtils = new SoundPlayUtils();
        }
        return mUtils;
    }

    private void initPlaySound(Context context){
        mBuilder = new SoundPool.Builder();
        mBuilder.setMaxStreams(5);

        mSoundPool = mBuilder.build();

        // 初始化声音

        mSoundPool.load(context, R.raw.wolf_open, 1);// 1
        mSoundPool.load(context, R.raw.wolf_kill, 1);// 2
        mSoundPool.load(context, R.raw.wolf_close_eyes, 1);// 3
        mSoundPool.load(context, R.raw.witch_open, 1);// 4
        mSoundPool.load(context, R.raw.witch_save, 1);// 5
        mSoundPool.load(context, R.raw.witch_kill, 1);// 6
        mSoundPool.load(context, R.raw.witch_close, 1);// 7
        mSoundPool.load(context, R.raw.seer_open, 1);// 8
        mSoundPool.load(context, R.raw.seer_see, 1);// 9
        mSoundPool.load(context, R.raw.seer_result, 1);// 10
        mSoundPool.load(context, R.raw.seer_close, 1);// 11
        mSoundPool.load(context, R.raw.police, 1);// 12
        mSoundPool.load(context, R.raw.next_day, 1);// 13
    }

    public void playSound(int soundID){
        mSoundPool.play(soundID,1,1,1,0,1);
    }

    public void stopSound(int soundID){
        mSoundPool.stop(soundID);
    }

    public void releaseSound(int soundID){
        mSoundPool.release();
    }
}

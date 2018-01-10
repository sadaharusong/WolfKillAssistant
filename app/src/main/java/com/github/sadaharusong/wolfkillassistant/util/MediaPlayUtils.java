package com.github.sadaharusong.wolfkillassistant.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * @author sadaharusong
 * @date 2017/12/24 0024.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */
public class MediaPlayUtils {
    private static MediaPlayUtils mInstance;
    private MediaPlayer mMediaPlayer;
    private Context mContext;

    MediaPlayUtils(){}

    public static MediaPlayUtils getInstance(){
        if (mInstance == null){
            mInstance = new MediaPlayUtils();
        }
        return mInstance;
    }

    public void init(Context context){
        this.mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    public void play(final int soundId){
        if (mMediaPlayer == null){
            return;
        }
        Log.e("---" , soundId + "");
        mMediaPlayer.stop();

        if (soundId != SoundConstant.WOLF_KILL && soundId != SoundConstant.WITCH_KILL
                && soundId != SoundConstant.SEER_RESULT && soundId != SoundConstant.NEXT_DAY){
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    play(soundId + 1);
                }
            });
        }else {
            mMediaPlayer.setOnCompletionListener(null);
        }

        mMediaPlayer.reset();
        Uri dataUri = Uri.parse("android.resource://com.github.sadaharusong.wolfkillassistant/"+ getSoundPath(soundId));
        try {
            mMediaPlayer.setDataSource(mContext, dataUri);
            //资源较小就用这个加载
            mMediaPlayer.prepare();
        } catch (IOException e) {
            Log.e("SoundPlay", "设置声音文件时出错,dataUri = " + dataUri);
            e.printStackTrace();
            releasePlayer();
        }

        mMediaPlayer.start();
    }

    public void stop(){
        if (mMediaPlayer == null){
            return;
        }
        mMediaPlayer.stop();
    }

    public void releasePlayer(){
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private int getSoundPath(int soundId){
        int soundPath = -1;
        switch (soundId){
            case SoundConstant.WOLF_OPEN:
                soundPath = SoundConstant.WOLF_OPEN_PATH;
                break;
            case SoundConstant.WOLF_KILL:
                soundPath = SoundConstant.WOLF_KILL_PATH;
                break;
            case SoundConstant.WOLF_CLOSE_EYES:
                soundPath = SoundConstant.WOLF_CLOSE_EYES_PATH;
                break;
            case SoundConstant.WITCH_OPEN:
                soundPath = SoundConstant.WITCH_OPEN_PATH;
                break;
            case SoundConstant.WITCH_SAVE:
                soundPath = SoundConstant.WITCH_SAVE_PATH;
                break;
            case SoundConstant.WITCH_KILL:
                soundPath = SoundConstant.WITCH_KILL_PATH;
                break;
            case SoundConstant.WITCH_CLOSE:
                soundPath = SoundConstant.WITCH_CLOSE_PATH;
                break;
            case SoundConstant.SEER_OPEN:
                soundPath = SoundConstant.SEER_OPEN_PATH;
                break;
            case SoundConstant.SEER_SEE:
                soundPath = SoundConstant.SEER_SEE_PATH;
                break;
            case SoundConstant.SEER_RESULT:
                soundPath = SoundConstant.SEER_RESULT_PATH;
                break;
            case SoundConstant.SEER_CLOSE:
                soundPath = SoundConstant.SEER_CLOSE_PATH;
                break;
            case SoundConstant.POLICE:
                soundPath = SoundConstant.POLICE_PATH;
                break;
            case SoundConstant.NEXT_DAY:
                soundPath = SoundConstant.NEXT_DAY_PATH;
                break;
            default:
                soundPath = -1;
        }
        return soundPath;
    }
}

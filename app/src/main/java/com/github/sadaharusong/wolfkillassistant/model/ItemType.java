package com.github.sadaharusong.wolfkillassistant.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 角色页面adapter根据左右显示不同的item
 * @author sadaharusong
 * @date 2017/9/17 0017.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class ItemType {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private int currentPosition;

    @IntDef({LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemPosition {}

    public void setCurrentPositon(@ItemPosition int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @ItemPosition
    public int getCurrentPosition() {
        return currentPosition;
    }

}

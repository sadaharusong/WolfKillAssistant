package com.github.sadaharusong.wolfkillassistant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sadaharusong
 * @date 2018/1/20 0020
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class GameInfo {
    private static GameInfo mGameInfo = null;
    private List<Role> mList = new ArrayList<>();
    private int mPlayerCount = 0;
    private boolean isNeedLover = false;

    private GameInfo(){}
    public static GameInfo getInstance(){
        if (mGameInfo == null){
            mGameInfo = new GameInfo();
        }
        return mGameInfo;
    }

    public void setRoleList(List<Role> list){
        this.mList = list;
    }

    public List<Role> getRoleList(){
        return mList;
    }

    public void setPlayerCount(int mPlayerCount) {
        this.mPlayerCount = mPlayerCount;
    }

    public int getPlayerCount() {
        return mPlayerCount;
    }

    public boolean isNeedLover() {
        return isNeedLover;
    }

    public void setNeedLover(boolean needLover) {
        isNeedLover = needLover;
    }

}

package com.github.sadaharusong.wolfkillassistant.model;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sadaharusong
 * @date 2018/1/20 0020
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class GameInfo {
    //游戏选择的角色种类
    private List<Role> mList = new ArrayList<>();
    //游戏玩家号数对应的角色
    private Map<Integer,Role> mMap = new ArrayMap<>();
    private int mPlayerCount = 0;
    private boolean isNeedLover = false;

    private GameInfo(){}
    private static class LazyHolder {
        private static final GameInfo INSTANCE = new GameInfo();
    }

    public static GameInfo getInstance(){
        return LazyHolder.INSTANCE;
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

    public void addRole(int position,Role role){
        mMap.put(position,role);
    }

    public Map<Integer,Role> getRoleMap(){
        return mMap;
    }

}

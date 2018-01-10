package com.github.sadaharusong.wolfkillassistant.model;

import android.util.ArrayMap;

import java.util.Map;

/**
 * @author sadaharusong
 * @date 2017/11/12 0012.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */
public class RoleMap{
    private static RoleMap mRoleMap = null;
    private Map<Integer,Role> mMap = new ArrayMap<>();

    public RoleMap(){}
    public static RoleMap getInstance(){
        if (mRoleMap == null){
            mRoleMap = new RoleMap();
        }
        return mRoleMap;
    }

    public void addRole(int position,Role role){
        mMap.put(position,role);
    }

    public Map<Integer,Role> getRoleMap(){
        return mMap;
    }
}


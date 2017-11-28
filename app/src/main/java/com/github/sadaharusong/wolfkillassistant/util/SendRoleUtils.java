package com.github.sadaharusong.wolfkillassistant.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sadaharusong on 2017/9/17 0017.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class SendRoleUtils {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public SendRoleUtils(Context context,String preferenceName){
        mPreferences = context.getSharedPreferences(preferenceName,Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    /**
     * 设置角色信息以便传递
     *
    * */
    public <K,T> void setRoles(String tag , Map<K,T> dataRole){
        if (dataRole == null || dataRole.isEmpty() || dataRole.size() < 1){
            return;
        }

        Gson gson = new Gson();
        String strJson  = gson.toJson(dataRole);
        mEditor.clear();
        mEditor.putString(tag ,strJson);
        mEditor.commit();
    }

    /**
    * 获取玩家已经输入完成的角色信息
    * */
    public <K,T> Map<K,T> getRoles(String tag){
        Map<K,T> roles = new HashMap<>();
        String strJson = mPreferences.getString(tag,null);
        if (strJson == null){
            return roles;
        }
        Gson gson = new Gson();
        roles = gson.fromJson(strJson,new TypeToken<Map<K,T> >(){}.getType());
        return roles;
    }
}

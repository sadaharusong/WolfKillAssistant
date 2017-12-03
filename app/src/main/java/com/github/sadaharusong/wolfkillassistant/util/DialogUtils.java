package com.github.sadaharusong.wolfkillassistant.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.github.sadaharusong.wolfkillassistant.R;

/**
 * Created by sadaharusong on 2017/11/19 0019.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class DialogUtils {

    /** 两个按钮的普通Dialog
     * @param message  设置对话框消息提示
     * @param confirmClick 自定义确定按钮点击事件
     * @param cancelClick 自定义取消按钮点击事件
     */
    public static void showNormalDialog(Context context, String message ,
                                  DialogInterface.OnClickListener confirmClick, DialogInterface.OnClickListener cancelClick){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton(R.string.yes, confirmClick);
        normalDialog.setNegativeButton(R.string.no, cancelClick);
        // 显示
        normalDialog.show();
    }

    public static void showSingleDialog(Context context, String message){
        final AlertDialog.Builder singleDialog = new AlertDialog.Builder(context);
        singleDialog.setMessage(message);
        singleDialog.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }
}

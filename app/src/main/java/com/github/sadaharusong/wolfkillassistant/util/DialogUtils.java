package com.github.sadaharusong.wolfkillassistant.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.github.sadaharusong.wolfkillassistant.R;

/**
 * @author sadaharusong
 * @date 2017/11/19 0019.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class DialogUtils {

    private static AlertDialog mNormalDialog = null;
    private static AlertDialog mSingleDialog = null;

    /** 两个按钮的普通Dialog
     * @param message  设置对话框消息提示
     * @param confirmClick 自定义确定按钮点击事件
     * @param cancelClick 自定义取消按钮点击事件
     */
    public static void showNormalDialog(Context context, String message ,
                                  DialogInterface.OnClickListener confirmClick, DialogInterface.OnClickListener cancelClick){

        AlertDialog.Builder normalBuilder = new AlertDialog.Builder(context);
        normalBuilder.setMessage(message);
        normalBuilder.setPositiveButton(R.string.yes, confirmClick);
        normalBuilder.setNegativeButton(R.string.no, cancelClick);
        // 显示
        mNormalDialog = normalBuilder.create();
        mNormalDialog.show();
    }

    public static void dismissDialog() {
        if (mNormalDialog != null) {
            mNormalDialog.dismiss();
        }

        if (mSingleDialog != null) {
            mSingleDialog.dismiss();
        }
    }

    public static void showSingleDialog(Context context, String message, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder singleBuilder = new AlertDialog.Builder(context);
        singleBuilder.setMessage(message);
        singleBuilder.setPositiveButton(R.string.ensure, onClickListener);
        mSingleDialog = singleBuilder.create();
        mSingleDialog.show();
    }
}

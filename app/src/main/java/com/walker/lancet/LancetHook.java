package com.walker.lancet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.lang.ref.WeakReference;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.This;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

public class LancetHook {

    public static boolean isLoginSuccess = false;

    @Insert("testError")
    @TargetClass("com.walker.lancet.LancetTest")
    public void testFun(Activity activity, String msg) {
        msg = msg + "   -lancet";
        try {
            Origin.callVoid();
        } catch (Exception e) {
            Log.e("LancetHook", e.toString());
            Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Insert("getUserInfo")
    @TargetClass("com.walker.lancet.LancetTest")
    public void aopUserInfo(Activity activity) {
        if (isLoginSuccess) {
            Origin.callVoid();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity).
                setTitle("模拟登录")
                .setMessage("请授权登录")
                .setNeutralButton("授权", new MyOkCallBack(activity, This.get()))
                .setNegativeButton("取消", null);
        builder.create().show();

    }

    @Proxy("i")
    @TargetClass("android.util.Log")
    public static int logFun(String tag, String msg) {
        msg = msg + "-lancet";
        return (int) Origin.call();
    }

    public static class MyOkCallBack implements DialogInterface.OnClickListener {

        private Object mInstance;
        private WeakReference<Activity> activityWeakReference;

        public MyOkCallBack(Activity activity, Object instance) {
            mInstance = instance;
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            isLoginSuccess = true;
            if (mInstance != null && mInstance instanceof LancetTest) {
                if (activityWeakReference.get() != null) {
                    ((LancetTest) mInstance).getUserInfo(activityWeakReference.get());
                }
            }
        }
    }
}

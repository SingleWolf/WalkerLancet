package com.walker.lancet;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class LancetTest {

    private static LancetTest sInstance=new LancetTest();

    public static LancetTest getInstance() {
        return sInstance;
    }

    public void testError(Activity activity, String msg) {
        int a = 1 / 0;
        Log.i("LancetTest", msg + a);
    }

    public void getUserInfo(Activity activity) {
        Toast.makeText(activity.getApplicationContext(), "姓名：Walker\n年龄：23", Toast.LENGTH_LONG).show();
    }
}

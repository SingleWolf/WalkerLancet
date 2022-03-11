package com.walker.lancet;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tvTryCatch).setOnClickListener(v -> LancetTest.getInstance().testError(this,"walker"));
        findViewById(R.id.tvAopLogin).setOnClickListener(v -> LancetTest.getInstance().getUserInfo(this));
    }
}

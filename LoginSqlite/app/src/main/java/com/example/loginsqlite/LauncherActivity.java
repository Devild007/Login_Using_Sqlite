package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LauncherActivity extends AppCompatActivity {

    @BindView(R.id.layoutOne)
    LinearLayout layoutOne;
    @BindView(R.id.txtAnimate)
    AppCompatTextView txtAnimate;
    @BindView(R.id.layoutTwo)
    RelativeLayout layoutTwo;

    Animation uptodown, downtoup, txtanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        txtanim = AnimationUtils.loadAnimation(this, R.anim.textanim);

        layoutOne.setAnimation(uptodown);
        layoutTwo.setAnimation(downtoup);
        txtAnimate.setAnimation(txtanim);
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        }, 3000);

    }
}
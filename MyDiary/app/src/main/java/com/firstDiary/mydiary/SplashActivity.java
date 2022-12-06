package com.firstDiary.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//앱 실행 시 로딩 화면
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // onCreate() : Activity (화면)이 시작될 때 가장 먼저 실행되는 곳 [안드로이드 액티비티 생명주기]


        // 딜레이를 발생시켜서 (1~2초 가량) 그 이후에 MainActivity 로 이동해야 함
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // 메인 액티비티로 이동하는 구간
                Intent mainIntent = new Intent(SplashActivity.this , MainActivity.class);
                startActivity(mainIntent);
                finish(); // 현재 화면은 끝나고 넘어간다.
            }
        }, 1500); // 딜레이 해주고 싶은 초를 찍는다. (1000밀리초 = 1초)
    }
}
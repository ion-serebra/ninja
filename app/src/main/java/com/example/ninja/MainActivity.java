package com.example.ninja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GameView GameView2;
    GameView GameView;


    boolean gameFlag = false;
    public byte level;

    DisplayMetrics metrics;
    TextView textView;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //textView = findViewById(R.id.textView);
        //textView.setText("x: "+width+ "y: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView = new GameView(getApplicationContext(), (byte) 1);
        if(gameFlag)  { setContentView(R.layout.activity_main);}
        else { setContentView(R.layout.activity_main);}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // Intent intent = new Intent(MainActivity.this, Lobby.class);
        //startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Чуть не вышли", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void startGameView(View view) {
        GameView = new GameView(this, (byte) 1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(GameView);
        gameFlag = true;
    }

    public void startLevel2(View view) {
        GameView = new GameView(this, (byte) 2);
        setContentView(GameView);
        gameFlag = true;

    }

    public void startLevel3(View view) {
        GameView = new GameView(this, (byte) 3);
        setContentView(GameView);
        gameFlag = true;
    }

    public void startLevel4(View view) {
        GameView = new GameView(getApplicationContext(), (byte) 4);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(GameView);
        gameFlag = true;
    }


    public void backToLobby(View view) {
        Intent intent = new Intent(MainActivity.this, Lobby.class);
        startActivity(intent);
    }

    public void startLevel5(View view) {
    }
}
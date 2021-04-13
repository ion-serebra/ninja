package com.example.ninja;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lobby extends AppCompatActivity {



    GameView GameView;
    boolean gameFlag = false;
    public byte level;

    Intent intent;

    static public int width;
    static public int height;

    TextView dollars;
    TextView gold;
    GlobalValues gl;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.lobby);

        gl = new GlobalValues();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        dollars = findViewById(R.id.textDollars);
        gold = findViewById(R.id.textGold);

        dollars.setText(""+gl.dollars+" $");
        gold.setText(" K");


    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.lobby);

        dollars.setText(""+gl.dollars+" $");
        gold.setText(""+GlobalValues.gold+" K");

    }

    public void startOfficeActivity(View view) {

        if(House.weapons.size()>0) {
            intent = new Intent(Lobby.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Для начала купи оружие, чудак :)", Toast.LENGTH_SHORT).show();
        }

    }

    public void startHouseActivity(View view) {

        intent = new Intent(Lobby.this, House.class);
        startActivity(intent);
    }

    public void startShopActivity(View view) {
        intent = new Intent(Lobby.this, Shop.class);
        startActivity(intent);
    }
}

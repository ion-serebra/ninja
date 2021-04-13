package com.example.ninja;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ninja.house.recyclerViewAdapter;
import com.example.ninja.weapons.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class House extends AppCompatActivity implements View.OnTouchListener {

    public static int weaponId;
    public static boolean isVisible;
    public static int itemType = 0;

    TextView textView;
    static TextView weaponDescription;
    static TextView weaponName;
    static TextView itemTypeValue;
    static TextView firstParamValue;
    static TextView secondParamValue;
    static TextView firstParam;
    static TextView secondParam;

    static TextView armorDescription;
    static TextView armorName;
    static TextView armorItemTypeValue;
    static TextView armorFirstParamValue;
    static TextView armorSecondParamValue;
    static TextView armorFirstParam;
    static TextView armorSecondParam;


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;

    ImageView weapon1;
    ImageView weapon2;
    ImageView playerWeapon;
    ImageView imageArmor;
    ImageView imageHelmet;


    //id addresses
    int firstWeaponId;
    int secondWeaponId;
    int armorId;
    int helmetId;

    // flags
    int ii  = 0;
    int firstWeaponFlag;
    int secondWeaponFlag;

    Item dressedArmor;


    RelativeLayout layout;
    RelativeLayout armorDescriptionLayout;
    SurfaceView surface;
    Canvas canvas;


    public static ArrayList<com.example.ninja.weapons.Item> items = new ArrayList<>();
    public static ArrayList<Item> weapons = new ArrayList<>();
    public static ArrayList<Armor> armors = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.house_layout);


        layout = findViewById(R.id.weaponDescriptionCard);

        textView = findViewById(R.id.textView);
        weapon1 = findViewById(R.id.firstWeapon);
        weapon2 = findViewById(R.id.secondWeapon);
        playerWeapon = findViewById(R.id.playerWeapon);
        imageArmor = findViewById(R.id.ImageArmour);
        imageHelmet = findViewById(R.id.ImageHelmet);

        weaponDescription = findViewById(R.id.weaponDescriptionTextView);
        weaponName = findViewById(R.id.weaponNameTextView);
        itemTypeValue = findViewById(R.id.itemTypeValue);
        firstParam = findViewById(R.id.firstParam);
        secondParam = findViewById(R.id.secondParam);
        firstParamValue = findViewById(R.id.firstParamValue);
        secondParamValue = findViewById(R.id.secondParamValue);

        armorDescription = findViewById(R.id.armorDescription);
        armorName = findViewById(R.id.armorName);
        armorItemTypeValue = findViewById(R.id.armorItemTypeValue);
        armorFirstParam = findViewById(R.id.armorFirstParam);
        armorSecondParam = findViewById(R.id.armorSecondParam);
        armorFirstParamValue = findViewById(R.id.armorFirstParamValue);
        armorSecondParamValue = findViewById(R.id.armorSecondParamValue);
        armorDescriptionLayout = findViewById(R.id.armorDescriptionLayout);
/*
        surface = findViewById(R.id.surfaceView);
        canvas = new Canvas();
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);

        canvas.drawText(" fff", 20, 20, p);
        canvas.drawColor(Color.BLUE);
        surface.draw(canvas);
        View v;// = new View(this);
        v = findViewById(R.id.surfaceView);
        v.draw(canvas);
*/




        recyclerView = findViewById(R.id.recyclerView);
        adapter = new recyclerViewAdapter(weapons, this);

        LinearLayoutManager man = new LinearLayoutManager(this);
        man.setStackFromEnd(true);
        man.setReverseLayout(true);

        manager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(man);

        recyclerView.setOnTouchListener(this);
        imageArmor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //layout.setVisibility(View.VISIBLE);
                        armorDescriptionLayout.setVisibility(View.VISIBLE);
                        break;

                    case MotionEvent.ACTION_UP:
                        //Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();
                        //layout.setVisibility(View.INVISIBLE);
                        armorDescriptionLayout.setVisibility(View.INVISIBLE);
                        break;
                }

                return false;
            }
        });



    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void backToLobby(View view) {
        Intent intent = new Intent(House.this, Lobby.class);
        startActivity(intent);
    }


    public void setFirstWeapon(View view) {
        if(weapons.get(weaponId).getItemType() == 0) {
            weapon1.setImageResource(weapons.get(weaponId).getImg());
            firstWeaponFlag = 1;
        }
    }

    public void setSecondWeapon(View view) {
        if(weapons.get(weaponId).getItemType() == 0) {
        weapon2.setImageResource(weapons.get(weaponId).getImg());
        playerWeapon.setImageResource(weapons.get(weaponId).getImgUp());
        secondWeaponFlag = 1;
        }
    }

    public void putArmor(View view) {

        if(weapons.get(weaponId).getItemType() == 1)
        {


            imageArmor.setImageResource(weapons.get(weaponId).getImg());
            dressedArmor = weapons.get(weaponId);
            armorName.setText(dressedArmor.getName());
            armorItemTypeValue.setText("Броня");
            armorFirstParam.setText("Поглощение урона");
            armorSecondParam.setText("Запас прочности");
            armorFirstParamValue.setText(dressedArmor.getDmgBlocked()+"");
            armorSecondParamValue.setText(dressedArmor.getHp()+"");
            armorDescription.setText(dressedArmor.getDescription());
        }
    }

    public void showView(View view) {

       // if(isVisible) {
            weaponDescription.setText("rrr");
        //} else {
            //weaponDescription.setVisibility(View.INVISIBLE);
       // }

    }

    public static void jj()
    {

            weaponDescription.setText(weapons.get(weaponId).getDescription());
            weaponName.setText(weapons.get(weaponId).getName());

            switch (weapons.get(weaponId).getItemType())
            {
                case 0:
                    itemTypeValue.setText("Оружие");
                    firstParam.setText("Урон");
                    secondParam.setText("Перезарядка");
                    firstParamValue.setText(weapons.get(weaponId).getDamage()+"");
                    secondParamValue.setText(weapons.get(weaponId).getReloadTime()+"");
                    break;

                case 1:
                    itemTypeValue.setText("Броня");
                    firstParam.setText("Поглощение урона");
                    secondParam.setText("Запас прочности");
                    firstParamValue.setText(weapons.get(weaponId).getDmgBlocked()+"");
                    secondParamValue.setText(weapons.get(weaponId).getHp()+"");
                    break;

            }
            //itemTypeValue.setText(weapons.get(weaponId).getItemType());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();


        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                layout.setVisibility(View.VISIBLE);
                //armorDescriptionLayout.setVisibility(View.VISIBLE);
                break;

            case MotionEvent.ACTION_UP:
                //Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();
                layout.setVisibility(View.INVISIBLE);
                //armorDescriptionLayout.setVisibility(View.INVISIBLE);
                break;
        }


        return false;
    }

    public void showWeapons(View view) {
        itemType = 0;
    }

    public void showArmors(View view) {
        itemType = 1;
    }


}

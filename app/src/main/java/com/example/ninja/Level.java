package com.example.ninja;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ninja.weapons.Item;

import java.util.ArrayList;

public class Level {
    //Bitmap background;
    byte levelType;
    public TitleBackground background;
    Context context;

    ArrayList<Enemy> enemies;

    byte matrix[][] = {
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
            {1,6,6,1,1,2,3,4,5,6,7,1,4,1,2,5},
            {1,6,6,1,1,2,3,4,5,6,7,1,4,1,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,5,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,3,2,5},
            {3,1,1,3,1,2,3,4,5,6,7,1,4,1,2,5},
    };

    public void setMatrix(byte[][] matrix) {
        this.matrix = matrix;
    }

    public Level()
    {

    }

    public Level( byte[][] matrix)
    {
        this.context = context;
        this.matrix = matrix;
    }

    public void setEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
}

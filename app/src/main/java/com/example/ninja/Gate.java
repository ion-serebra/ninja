package com.example.ninja;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Gate {

    int x;
    int y;

    int width;
    int height;

    Bitmap img;

    boolean isOpen = false;

    public Gate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void openGates()
    {
        isOpen = true;
    }


}

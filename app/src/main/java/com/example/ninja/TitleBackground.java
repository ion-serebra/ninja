package com.example.ninja;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class TitleBackground {

    Bitmap red;
    Bitmap orange;
    Bitmap yellow;
    Bitmap green;
    Bitmap lightBlue;
    Bitmap blue;
    Bitmap purple;

    int titleSize = 100;

    /*
    1 - красный
    2 - оранжевый
    3 - жёлтый
    4 - зелёный
    5 - голубой
    6 - синий
    7 - фиолетовый
     */

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


    public TitleBackground(Context context)
    {

        red = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_red);
        orange = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_orange);
        yellow = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_yellow);
        green = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_green);
        lightBlue = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_blue);
        blue = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_blue_dark);
        purple = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_purple);

    }






    void drawBackground(Canvas canvas,byte[][] matrix, float dpi, float backgroundOffsetX,float backgroundOffsetY)
    {
        int x = 0;
        int y = 0;
        for(int i = 0; i<16;i++)
        {

            x=0;

            for(int j=0;j<16;j++)
            {
                switch (matrix[i][j])
                {
                    case 1:
                        canvas.drawBitmap(red,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 2:
                        canvas.drawBitmap(orange,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 3:
                        canvas.drawBitmap(yellow,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 4:
                        canvas.drawBitmap(green,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 5:
                        canvas.drawBitmap(lightBlue,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 6:
                        canvas.drawBitmap(blue,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 7:
                        canvas.drawBitmap(purple,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;
                }
                //canvas.drawBitmap(red,x,y,null);
                x+=titleSize*dpi;
            }
            y+=titleSize*dpi;
        }
    }

    void drawBackground(Context context, Canvas canvas,byte[][] matrix, float dpi, float backgroundOffsetX,float backgroundOffsetY)
    {

        int x = 0;
        int y = 0;
        for(int i = 0; i<16;i++)
        {

            x=0;

            for(int j=0;j<16;j++)
            {
                switch (matrix[i][j])
                {
                    case 1:
                        canvas.drawBitmap(red,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 2:
                        canvas.drawBitmap(orange,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 3:
                        canvas.drawBitmap(yellow,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 4:
                        canvas.drawBitmap(green,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 5:
                        canvas.drawBitmap(lightBlue,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 6:
                        canvas.drawBitmap(blue,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;

                    case 7:
                        canvas.drawBitmap(purple,x+backgroundOffsetX,y+backgroundOffsetY,null);
                        break;
                }
                //canvas.drawBitmap(red,x,y,null);
                x+=titleSize*dpi;
            }
            y+=titleSize*dpi;
        }
    }
}


package com.example.ninja;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;

public class Joystick {

     int joystickX;
     int joystickY;
     int joystickWidth;
     int joystickHeight;
     float direction;
     boolean isPressed;
     public float degree;


    public Joystick(int joystickX, int joystickY, int joystickWidth, int joystickHeight) {
        this.joystickX = joystickX;
        this.joystickY = joystickY;
        this.joystickWidth = joystickWidth;
        this.joystickHeight = joystickHeight;
    }

    public boolean isShooting(int touchX, int touchY)
    {
        if((Math.sqrt(((touchX-joystickX-(joystickWidth/2))*(touchX-joystickX-(joystickWidth/2)))
                +((touchY-joystickY-(joystickHeight/2))*(touchY-joystickY-(joystickHeight/2))))>((joystickHeight/2)-65)))
        {
            return true;
        } else return false;
    }

    public void drawJoystick(Bitmap joystickBitmap, Matrix joystickMatrix, Canvas canvas)
    {

        canvas.drawBitmap(joystickBitmap, joystickMatrix,null);

    }

     float getDirection(int touchX, int touchY)
    {

        //if((touchX>joystickX)&&(touchX<joystickX+joystickWidth)&&(touchY>joystickY)&&(touchY<joystickY+joystickHeight)) {
            direction = (float) Math.atan2((touchY - joystickY -(joystickHeight/2)),(touchX - joystickX - (joystickWidth/2)))*57.3f;
       // }
        return direction;
    }

     float getDirection(int touchX)
    {
        float angle = 0;
        if((touchX>joystickX)&&(touchX<joystickX+joystickWidth))
        {
             angle = joystickX + (joystickWidth / 2) - touchX;
        }
       return angle;
    }


     public boolean isJoystickTouched(int touchX, int touchY)
    {
        if((touchX>joystickX)&&(touchX<joystickX+joystickWidth)&&(touchY>joystickY)&&(touchY<joystickY+joystickHeight)) {
            return  true;
        } else return false;
    }

    public boolean isPressed(MotionEvent event, int touchX, int touchY)
    {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //s=" s: "+touchX+"  "+touchY;
                break;

            case MotionEvent.ACTION_DOWN:
                if((touchX>joystickX)&&(touchX<joystickX+joystickWidth)&&(touchY>joystickY)&&(touchY<joystickY+joystickHeight)) {
                    isPressed = true;
                }
                break;

            case MotionEvent.ACTION_UP:
                isPressed = false;

        }
        return isPressed;
    }

}
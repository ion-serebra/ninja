package com.example.ninja;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.ninja.weapons.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class Human {

    public float x;
    public float y;
    public float speed;
    public float speedX;
    public float speedY;
    public float rotateAngle;
    public float centerX=20;
    public float centerY= 24.15f;
    public Matrix matrix;
    public Bitmap humanBitmap;
    public Canvas canvas;

    public int hp = 50;

    Item weapon;
    GameView gameView;

    //ArrayList<Bullet> ball;

    public void setRotateAngle(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public Human(Bitmap humanBitmap, Canvas canvas) {
        this.humanBitmap = humanBitmap;
        this.canvas = canvas;
    }

    public Human(GameView gameView, Bitmap humanBitmap, Item weapon) {
        this.gameView = gameView;
        this.humanBitmap = humanBitmap;

        this.weapon = weapon;
        x =900;
        y =400;
        speed =4;
    }

    public Human(GameView gameView)
    {
        this.gameView = gameView;
        x =900;
        y =400;
        speed =3.5f*gameView.dpi;
        matrix = new Matrix();
        centerX = 21.5f;
        centerY = 24.55f;

        //rotateAngle = 45;
        //humanBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.survivor);
    }

    public void drawHuman(Bitmap bitmap, Matrix humanMatrix, Canvas canvas)
    {
        //humanMatrix.setTranslate(500,500);
        //humanMatrix.setRotate(45);
        canvas.drawBitmap(humanBitmap, humanMatrix, null);
    }

    public void update(boolean isPressed, float u, float rotateAngle)
    {
        if (isPressed) {
            x += speed * Math.cos(u * 3.14f / 180);
            y += speed * Math.sin(u * 3.14f / 180);
        }

        //humanSpeedX = (float) (humanSpeed * Math.cos(u * 3.14f / 180));
        //humanSpeedY = (float) (humanSpeed * Math.sin(u * 3.14f / 180));

        matrix.postScale(0.5f, 0.5f);
        matrix.postTranslate(x + gameView.backgroundOffsetX, y + gameView.backgroundOffsetY);
        matrix.postRotate(rotateAngle, x + centerX*gameView.dpi +gameView.backgroundOffsetX, y + gameView.backgroundOffsetY+ centerY*gameView.dpi);

        speedX = Math.abs(speed * (float) Math.cos(rotateAngle));//joystickRight.direction
        speedY = Math.abs(speed * (float) Math.sin(rotateAngle));
    }

    public void move(float backgroundOffsetX, float backgroundOffsetY)
    {
        matrix.postTranslate(x + backgroundOffsetX, y + backgroundOffsetY);
    }

    public void rotate(float angle,float dpi, float backgroundOffsetX, float backgroundOffsetY)
    {
        matrix.postRotate(angle, x + centerX*dpi +backgroundOffsetX,y + backgroundOffsetY+ centerY*dpi);
    }

    public void shoot( Bullet b, Canvas canvas, ArrayList<Bullet> ball, int joystickFlag, boolean shoot, boolean isShooting)
    {
        //weapon.prepareBullet(ball);
        //weapon.shoot(j, b, canvas, ball);
        Iterator<Bullet> j = ball.iterator();
        if(weapon.getBulletCounter() == weapon.getMagazineCapacity()) {
            weapon.setReloadFlag(true);
        }

        if(weapon.isReloadFlag() == false) {
            if ((joystickFlag == 1) && gameView.shooting2) {
                gameView.shootCounter++;
                weapon.prepareBullet((ArrayList) ball);
            }

            if ((joystickFlag == 0) && gameView.shooting) {
                gameView.shootCounter++;
                weapon.prepareBullet((ArrayList) ball);;
            }
        } else {
            weapon.reload();
            canvas.drawText("reload..." , 100, 400, gameView.p);
        }
       weapon.shoot(j,b,canvas,(ArrayList) ball);

    }




}

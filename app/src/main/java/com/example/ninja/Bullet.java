package com.example.ninja;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Bullet
{
    /**Картинка*/
    public Bitmap bmp;

    /**Позиция*/
    public int x;
    public int y;
    Matrix bulletMatrix;

    /**Скорость по Х=15*/
    private int mSpeed=30;



    final public double angle;

    /**Ширина*/
    public int width;

    /**Ввыоста*/
    public  int height;

    public GameView gameView;

/*
    public Bullet(GameView gameView, Bitmap bmp, float playerX, float playerY) {
        this.gameView=gameView;
        this.bmp = this.bmp;
        this.x = x;
        this.y = y;

    }

 */

    /**Конструктор*/
    public Bullet(GameView gameView, Bitmap bmp) {
    this.gameView=gameView;
    this.bmp=bmp;
    bulletMatrix = new Matrix();

    this.x = 400;            //позиция по Х
    this.y = 400;          //позиция по У
    this.width = 27;       //ширина снаряда
    this.height = 40;      //высота снаряда

    //угол полета пули в зависипости от координаты косания к экрану
    angle = gameView.bulletDirection*3.14f/180;
}



    public Bullet(GameView gameView, Bitmap bmp, double angle) {
        this.gameView=gameView;
        this.bmp=bmp;
        bulletMatrix = new Matrix();

        this.x = 400;            //позиция по Х
        this.y = 400;          //позиция по У
        this.width = 27;       //ширина снаряда
        this.height = 40;      //высота снаряда

        //угол полета пули в зависипости от координаты косания к экрану
        this.angle = angle;
    }

    /**Перемещение объекта, его направление*/
    private void update() {
        x += mSpeed * Math.cos(angle);         //движение по Х со скоростью mSpeed и углу заданном координатой angle
        y += mSpeed * Math.sin(angle);         // движение по У -//-

    }

    /**Рисуем наши спрайты*/
    public void onDraw(Canvas canvas) {
        update();                              //говорим что эту функцию нам нужно вызывать для работы класса
        bulletMatrix.setTranslate(x+GameView.box2,y+GameView.boy2);
        bulletMatrix.postRotate((float) (angle*180/3.14), x+GameView.box2, y+GameView.boy2);
        bulletMatrix.preScale(0.4f, 0.4f);
        canvas.drawBitmap(bmp, bulletMatrix, null);
    }
}
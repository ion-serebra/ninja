package com.example.ninja;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.ninja.weapons.Item;

public class Enemy
{
    /**Х и У коорданаты*/
    public float x;
    public float y;
    public float eCenterX = 23;
    public float eCenterY = 24.5f;

    public Matrix enemyMatrix;
    public float angle;
    public float angleRotate;


    /**Скорость*/
    public int speed;

    /**Выосота и ширина спрайта*/
    public int width;
    public int height;

    public GameView gameView;
    public Bitmap bmp;

    public int hp = 4;
    public int damage = 1;
    boolean attack = false;

    Item weapon;
    int counter = 0;
    int shootCounter=0;


    float offsetX;
    float offsetY;
    float dpi;

    float distance;

    // flags

    boolean reverseFlag = false;

    public byte tileX;
    public byte tileY;


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Enemy()
    {

    }

    /**Конструктор класса*/
    public Enemy(GameView gameView, Bitmap bmp){
        this.gameView = gameView;
        this.bmp = bmp;
        enemyMatrix = new Matrix();
        Random rnd = new Random();
        weapon = new Item(0,6, 45, 200,9,1, 400, R.drawable.remington, R.drawable.remington_up, 3, "Ремингтон 870", ""
                + "Дробовик системы Ремингтон. Помповая система. Подствольный трубчатый магазин на 3" +
                " стандартных ружейных патрона калибра 12. Большой урон в ближнем бою");
        weapon.setGameView(gameView);




        this.x = rnd.nextInt(1700);

        if ( x<1400 && x>600 )
        {
            int i = rnd.nextInt(1);
            if (i == 0) {
                this.y = rnd.nextInt(300);
            }
            if (i == 1)
            {
                this.y = 800+rnd.nextInt(400);
            }
        } else
        {
            this.y = rnd.nextInt(1200);
        }

        //this.speed = rnd.nextInt(4);
        this.speed = (int) (2*gameView.dpi);

        this.width = 9;
        this.height = 8;
    }

    public Enemy(GameView gameView, Bitmap bmp, Item weapon){
        this.gameView = gameView;
        this.bmp = bmp;
        enemyMatrix = new Matrix();
        Random rnd = new Random();
        this.weapon = weapon;
        weapon.setGameView(gameView);


        this.x = rnd.nextInt(1700);

        if ( x<1400 && x>600 )
    {
        int i = rnd.nextInt(1);
        if (i == 0) {
            this.y = rnd.nextInt(300);
        }
        if (i == 1)
        {
            this.y = 800+rnd.nextInt(400);
        }
    } else
    {
        this.y = rnd.nextInt(1200);
    }

    //this.speed = rnd.nextInt(4);
        this.speed = (int) (2*gameView.dpi);

        this.width = 9;
        this.height = 8;
}



    public Enemy(GameView gameView, Bitmap bmp, Item weapon, int x, int y){
        this.gameView = gameView;
        this.bmp = bmp;
        enemyMatrix = new Matrix();
        this.weapon = weapon;
        weapon.setGameView(gameView);
        this.x = x;
        this.y = y;
        this.speed = (int) (2*gameView.dpi);
        this.width = 9;
        this.height = 8;
    }



    public void update(){

        angle = (float) (Math.atan((y-gameView.human.y)/(x-gameView.human.x)));

         distance = (float) Math.sqrt((gameView.human.x-x)*(gameView.human.x-x)+(gameView.human.y-y)*(gameView.human.y-y));

        if(((gameView.human.x)<x)&&((gameView.human.y)<y)) {  //игрок слева СВЕРХУ, движение бота ⇙

            /*
            if(distance>250&&reverseFlag==false) {
                x -= speed * Math.cos(angle);
                y -= speed * Math.sin(angle);
            } else {
                reverseFlag = true;
            }

             */

            /*
            if(reverseFlag)
            {
                x += speed * Math.cos(angle);
                y += speed * Math.sin(angle);
                if(distance>300)
                {
                    reverseFlag=false;
                }
            }


             */

            angleRotate = angle*180/3.14f+180;

            boolean upFlag = false;
            boolean downFlag = false;
            boolean rightFlag = false;
            boolean leftFlag = false;

/*
                    if(gameView.collisionMatrix[getTileY()][getTileX()-1] == 1) // [y][x]
                    {
                        //gameView.leftWall = true;
                        int leftWallY = getTileY();
                        boolean upper = false;

                        int k;
                        for(k = 0;k<getTileY();k++)
                        {
                            if(gameView.collisionMatrix[k][getTileX()-1] == 0)
                            {
                                upFlag = true;
                                upper = true;
                                break;
                            }
                            if(gameView.tileX == getTileX())
                            {
                                upFlag = true;
                                String s = "df";
                            }
                        }
                        for(k = getTileY();k<16;k++)
                        {
                            if(gameView.collisionMatrix[k][getTileX()-1] == 0 && upper==false)
                            {
                                downFlag = true;
                                break;
                            }
                        }
                    }

            if (gameView.collisionMatrix[getTileY()-1][getTileX()] == 1)
            {
                boolean lefter = false;
                int k;
                for(k = 0;k<getTileX();k++)
                {
                    if(gameView.collisionMatrix[getTileY()-1][k] == 0)
                    {
                        leftFlag = true;
                        lefter = true;
                        break;
                    }
                }

                for(k = getTileX();k<16;k++)
                {
                    if(gameView.collisionMatrix[getTileY()-1][k] == 0 && lefter==false)
                    {
                        rightFlag = true;
                        break;
                    }
                }

            }







            if(upFlag)
            {
                y-=speed;
            }
            if(downFlag)
            {
               y+=speed;
            }

 */
            //if(!upFlag && !downFlag)
            // {
                x -= speed * Math.cos(angle);
                y -= speed * Math.sin(angle);
            // }

            upFlag = false;
            downFlag = false;

        }

        if(((gameView.human.x)>x)&&((gameView.human.y)<y)) {  //игрок справа снизу, движение бота ⇘
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
            angleRotate = angle*180/3.14f;

            for(int i = 0;i<16;i++)
            {
                for(int j = 0;j<16;j++)
                {
                    if(gameView.collisionMatrix[getTileY()][getTileX()-1] == 1)
                    {
                        gameView.leftWall = true;
                    }
                    else {
                        gameView.leftWall = false;
                    }


                }
            }

        }

        if(((gameView.human.x)<x)&&((gameView.human.y)>y)) {  //игрок слева сверху, движение бота ⇖
            x -= speed * Math.cos(angle);
            y -= speed * Math.sin(angle);

            for(int i = 0;i<16;i++)
            {
                for(int j = 0;j<16;j++)
                {
                    if(gameView.collisionMatrix[getTileY()][getTileX()-1] == 1)
                    {
                        gameView.leftWall = true;
                    }
                     else {
                         gameView.leftWall = false;
                    }


                }
            }

            angleRotate = angle*180/3.14f+180;
        }

        if(((gameView.human.x)>x)&&((gameView.human.y)>y)) {  //игрок справа сверху, движение бота ⇗
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
            angleRotate = angle*180/3.14f;

            for(int i = 0;i<16;i++)
            {
                for(int j = 0;j<16;j++)
                {
                    if(gameView.collisionMatrix[getTileY()][getTileX()-1] == 1)
                    {
                        gameView.leftWall = true;

                    }
                    else {
                        gameView.leftWall = false;
                    }


                }
            }
        }

    }

    public void onDraw(Canvas c){
        update();

        enemyMatrix.setScale(0.5f, 0.5f);
        enemyMatrix.postTranslate(x+GameView.box2,y+GameView.boy2);
        enemyMatrix.postRotate((angleRotate), x+gameView.backgroundOffsetX+eCenterX*gameView.dpi,y+gameView.backgroundOffsetY+eCenterY*gameView.dpi);

        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(5);
        p.setTextSize(75);
        c.drawText(distance+"",800, 600, p);

        c.drawBitmap(bmp, enemyMatrix, null);
        //c.drawPoint(x+gameView.backgroundOffsetX+127,y+gameView.backgroundOffsetY+135 , p);
        //c.drawText(""+angleRotate, 600, 800, p);
    }

    public void prepareBullet( ArrayList ball) {


        //  b = createSprite(R.drawable.bullet);




        counter++;
        if (weapon.getCounter() == weapon.fireRapidity) {
            for (int i = 0; i < weapon.getBulletPerShoot(); i++) {
                weapon.bulletCounter++;
                Bullet b;
                b = gameView.createSprite(R.drawable.bullet);
                b.x = (int) (x+gameView.backgroundOffsetX+eCenterX*gameView.dpi + i * 10);
                b.y = (int) (y+gameView.backgroundOffsetY+eCenterY*gameView.dpi + i * 10);
                //b.angle = angleRotate;
                ball.add(b);
            }

            counter = 0;
        }
    }



    public void shoot( Bullet b, Canvas canvas, ArrayList<Bullet> enemyBullets)
    {
        //weapon.prepareBullet(ball);
        //weapon.shoot(j, b, canvas, ball);
        Iterator<Bullet> j = enemyBullets.iterator();
        if(weapon.getBulletCounter() == weapon.getMagazineCapacity()) {
            weapon.setReloadFlag(true);
        }

        if(weapon.isReloadFlag() == false) {

            weapon.prepareBullet((ArrayList) enemyBullets  , (int) ((int) x+eCenterX*gameView.dpi),
                    (int) ((int) y+eCenterY*gameView.dpi), Math.atan2((y-gameView.human.y),(x-gameView.human.x))+3.1415926);

        } else {
            weapon.reload();
            canvas.drawText("reload..." , 100, 900, gameView.p);
        }
        weapon.shoot(j,b,canvas,(ArrayList) enemyBullets);

    }

    public int getTileX()
    {
        return (int) (x/(gameView.currentLvl.background.titleSize*gameView.dpi) );
    }

    public int getTileY()
    {
        return (int) (y/(gameView.currentLvl.background.titleSize*gameView.dpi));
    }

}
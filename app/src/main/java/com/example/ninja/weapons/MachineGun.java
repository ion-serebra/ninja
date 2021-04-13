package com.example.ninja.weapons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.ninja.Bullet;
import com.example.ninja.GameView;
import com.example.ninja.R;

import java.util.ArrayList;
import java.util.Iterator;

public class MachineGun {
    int bulletSpeed;
    int fireRapidity =15;
    boolean automatic;
    public GameView gameView;
    int counter=0;
    int bulletImg;

    public MachineGun(GameView gameView) {
        this.gameView = gameView;
    }

    public void shoot(Iterator<Bullet> j, Bullet b, Canvas canvas, ArrayList<Bullet> ball) {


                j = ball.iterator();
                while (j.hasNext()) {

                    b = j.next();
                    b.onDraw(canvas);
                   // if (b.x >= 1000 || b.x <= 1000) {
                        //canvas.drawPoint(b.x, b.y, p);
                    //    b.onDraw(canvas);
                    //} else {
                     //   j.remove();
                   // }

                }




    }

    public void prepareBullet( ArrayList ball) {


                //  b = createSprite(R.drawable.bullet);
        counter++;
        if(counter == fireRapidity) {
            Bullet b;
            b = gameView.createSprite(R.drawable.bullet);
            b.x = (int) (gameView.human.x + gameView.backgroundOffsetX + 110);
            b.y = (int) (gameView.human.y + gameView.backgroundOffsetY + 133);
            ball.add(b);




            counter=0;
        }

    }
}


package com.example.ninja.weapons;

import android.graphics.Canvas;

import com.example.ninja.Bullet;
import com.example.ninja.GameView;
import com.example.ninja.R;

import java.util.ArrayList;
import java.util.Iterator;

public class Shotgun {
    int bulletSpeed;
    int fireRapidity =25;
    boolean automatic;
    public GameView gameView;
    int counter=0;

    public Shotgun(GameView gameView) {
        this.gameView = gameView;
    }

    public void shoot(Iterator<Bullet> j, Bullet b, Canvas canvas, ArrayList<Bullet> ball) {


        j = ball.iterator();
        while (j.hasNext()) {

            b = j.next();
            if (b.x >= 1000 || b.x <= 1000) {
                //canvas.drawPoint(b.x, b.y, p);
                b.onDraw(canvas);
            } else {
                j.remove();
            }

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

            Bullet c;
            c = gameView.createSprite(R.drawable.bullet);
            c.x = (int) (gameView.human.x + gameView.backgroundOffsetX + 90);
            c.y = (int) (gameView.human.y + gameView.backgroundOffsetY + 113);
            ball.add(c);

            Bullet d;
            d = gameView.createSprite(R.drawable.bullet);
            d.x = (int) (gameView.human.x + gameView.backgroundOffsetX + 140);
            d.y = (int) (gameView.human.y + gameView.backgroundOffsetY + 163);
            ball.add(d);


            counter=0;
        }

    }
}
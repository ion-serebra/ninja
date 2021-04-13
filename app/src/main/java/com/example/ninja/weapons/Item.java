package com.example.ninja.weapons;

import android.graphics.Canvas;

import com.example.ninja.Bullet;
import com.example.ninja.GameView;
import com.example.ninja.R;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.ninja.GameView;

public class Item {

    //weapon

    public int id;
    public int fireRapidity =15;
    public GameView gameView;
    int counter=0;
    public int img;
    int imgUp;
    int bulletPerShoot=1;
    int weaponCount;
    private int quantity = 0;
    int damage = 1;
    int price;
    private int magazineCapacity = 30;
    public int bulletCounter = 0;
    private int reloadTime;
    private int reloadCounter = 0;
    private boolean reloadFlag = false;
    public String name;
    public String description;

    //armor

    int hp;
    int dmgBlocked;

    int itemType = 0; // 0 - weapon, 1 - armor


    public int getHp() {
        return hp;
    }

    public int getWeaponCount() {
        return weaponCount;
    }

    public int getDmgBlocked() {
        return dmgBlocked;
    }

    public int getFireRapidity() {
        return fireRapidity;
    }

    public GameView getGameView() {
        return gameView;
    }

    public int getCounter() {
        return counter;
    }

    public int getBulletPerShoot() {
        return bulletPerShoot;
    }

    public int getItemType() {
        return itemType;
    }

    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    public int getBulletCounter() {
        return bulletCounter;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getReloadCounter() {
        return reloadCounter;
    }

    public boolean isReloadFlag() {
        return reloadFlag;
    }

    public int getDamage() { return damage;  }

    public int getImg() {
        return img;
    }

    public int getImgUp() {
        return imgUp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReloadFlag(boolean reloadFlag) {
        this.reloadFlag = reloadFlag;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }



    public Item(int itemType ,int id, int fireRapidity, int reloadTime, int magazineCapacity, int damage, int price, int bulletImg,int topViewImg, int bulletPerShoot, String weaponName, String weaponDescription) {
        this.itemType = itemType;
        this.id = id;
        this.fireRapidity = fireRapidity;
        this.reloadTime = reloadTime;
        this.magazineCapacity = magazineCapacity;
        this.damage = damage;
        this.price = price;
        this.img = bulletImg;
        this.imgUp = topViewImg;
        this.bulletPerShoot = bulletPerShoot;
        this.name = weaponName;
        this.description = weaponDescription;
        counter = 0;
    }

    public Item(int itemType, int img, int imgUp, int price, int hp, int dmgBlocked, String name, String description) {
        this.itemType = itemType;
        this.img = img;
        this.imgUp = imgUp;
        this.price = price;
        this.hp = hp;
        this.dmgBlocked = dmgBlocked;
        this.name = name;
        this.description = description;
    }

    public Item(int itemType, int id, int img, int imgUp, int price, int hp, int dmgBlocked, String name, String description) {
        this.id=id;
        this.itemType = itemType;
        this.img = img;
        this.imgUp = imgUp;
        this.price = price;
        this.hp = hp;
        this.dmgBlocked = dmgBlocked;
        this.name = name;
        this.description = description;
    }



    public void shoot(Iterator<Bullet> j, Bullet b, Canvas canvas, ArrayList<Bullet> ball) {
        j = ball.iterator();
        while (j.hasNext()) {
            b = j.next();
            b.onDraw(canvas);
        }
    }

    public void prepareBullet( ArrayList ball) {
        //  b = createSprite(R.drawable.bullet);
        counter++;
        if (counter == fireRapidity) {
            for (int i = 0; i < bulletPerShoot; i++) {
                bulletCounter++;
                Bullet b;
                b = gameView.createSprite(R.drawable.bullet);
                b.x = (int) (gameView.human.x + gameView.playerCenterX * gameView.dpi + i * 10);
                b.y = (int) (gameView.human.y + gameView.playerCenterY * gameView.dpi + i * 10);
                ball.add(b);
            }
            counter = 0;
        }
    }

    public void prepareBullet( ArrayList ball, int x, int y) {
        //  b = createSprite(R.drawable.bullet);
        counter++;
        if (counter == fireRapidity) {
            for (int i = 0; i < bulletPerShoot; i++) {
                bulletCounter++;
                Bullet b;
                b = gameView.createSprite(R.drawable.bullet);
                b.x = x + i * 10;
                b.y = y + i * 10;
                ball.add(b);
            }
            counter = 0;
        }
    }

    public void prepareBullet( ArrayList ball, int x, int y, double bulletAngle) {
        //  b = createSprite(R.drawable.bullet);
        counter++;
        if (counter == fireRapidity) {
            for (int i = 0; i < bulletPerShoot; i++) {
                bulletCounter++;
                Bullet b;
                b = gameView.createSprite(R.drawable.bullet, bulletAngle);
                b.x = x + i * 10;
                b.y = y + i * 10;
                ball.add(b);
            }
            counter = 0;
        }
    }

    public void prepareEnemyBullet( ArrayList ball) {
        //  b = createSprite(R.drawable.bullet);
        counter++;
        if (counter == fireRapidity) {
            for (int i = 0; i < bulletPerShoot; i++) {
                bulletCounter++;
                Bullet b;
                b = gameView.createSprite(R.drawable.bullet);
                b.x = (int) (gameView.human.x + gameView.backgroundOffsetX + gameView.playerCenterX * gameView.dpi + i * 10);
                b.y = (int) (gameView.human.y + gameView.backgroundOffsetY + gameView.playerCenterY * gameView.dpi + i * 10);
                ball.add(b);
            }

            counter = 0;
        }


    }

    public void reload()
    {
        if(reloadCounter<=reloadTime) {
            reloadCounter++;
        } else {
            reloadFlag = false;
            reloadCounter = 0;
            bulletCounter = 0;
        }


    }

}

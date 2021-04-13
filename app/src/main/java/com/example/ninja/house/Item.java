package com.example.ninja.house;

import com.example.ninja.GameView;

public class Item {

    //weapon

    public int id;
    int fireRapidity =15;
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
    private int bulletCounter = 0;
    private int reloadTime;
    private int reloadCounter = 0;
    private boolean reloadFlag = false;
    public String name;
    public String description;

    //armor

    int hp;
    int dmgBlocked;

    int itemType = 0; // 0 - weapon, 1 - armor


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

}

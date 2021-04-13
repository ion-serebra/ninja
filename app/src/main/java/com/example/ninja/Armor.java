package com.example.ninja;

import android.widget.ScrollView;

public class Armor {

    int img;
    int imgUp;
    int price;
    int hp;
    int dmgBlocked;
    String name;
    String description;

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Armor(int img, int imgUp, int price, int hp, int dmgBlocked) {
        this.img = img;
        this.imgUp = imgUp;
        this.price = price;
        this.hp = hp;
        this.dmgBlocked = dmgBlocked;
    }

    public Armor(int img, int imgUp, int price, int hp, int dmgBlocked, String name, String description) {
        this.img = img;
        this.imgUp = imgUp;
        this.price = price;
        this.hp = hp;
        this.dmgBlocked = dmgBlocked;
        this.name = name;
        this.description = description;
    }
}

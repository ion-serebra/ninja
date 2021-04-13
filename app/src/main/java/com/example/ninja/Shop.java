package com.example.ninja;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ninja.weapons.Item;
import java.util.ArrayList;

public class Shop extends AppCompatActivity {


    public ArrayList<Item> goods = new ArrayList<>();


    public ArrayList<Item> weapons;
    public ArrayList<Armor> armors;

    ImageView card1image;
    ImageView card2image;
    ImageView card3image;
    ImageView card4image;
    ImageView card5image;
    ImageView card6image;
    ImageView weaponImage;

    TextView description;
    TextView card1text;
    TextView card2text;
    TextView card3text;
    TextView card4text;
    TextView card5text;
    TextView card6text;

    int current;

    int shopPage = 1;
    int itemTypesPage= 0;

    public static int dollars = 51244;
    TextView dollarsAmount;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.shop_layout);

        card1image = findViewById(R.id.card1image);
        card2image = findViewById(R.id.card2image);
        card3image = findViewById(R.id.card3image);
        card4image = findViewById(R.id.card4image);
        card5image = findViewById(R.id.card5image);
        card6image = findViewById(R.id.card6image);
        weaponImage = findViewById(R.id.weaponImage);

        description = findViewById(R.id.description);
        card1text = findViewById(R.id.card1text);
        card2text = findViewById(R.id.card2text);
        card3text = findViewById(R.id.card3text);
        card4text = findViewById(R.id.card4text);
        card5text = findViewById(R.id.card5text);
        card6text = findViewById(R.id.card6text);



        weapons = new ArrayList<Item>();
        armors = new ArrayList<Armor>();

        setWeapons();
        setArmors();
        setGoods();

        dollarsAmount = findViewById(R.id.dollarsAmount);
        dollarsAmount.setText(dollars+" $");


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void clickCard1(View view) {
        //Iterator<Weapon> iterator = House.weapons.iterator();
       // int i = 0;
        /*
        while (iterator.hasNext())
        {
            if(House.weapons.get(i) == weapons.get(0+7*(shopPage-1)) )
            {
                break;
            }
            if(iterator.hasNext()==false)
            {
                House.weapons.add(weapons.get(0+7*(shopPage-1)));
            }
            i++;
        }

         */
        if (shopPage == 1)
        {
            //buyWeapon(0);
            current = 0;
            setDecription();

        } else if (shopPage == 2) {
            //buyWeapon(6);
            current = 6;
            setDecription();
        } else {
            //buyWeapon(12);
            current =12;
            setDecription();
        }
        dollarsAmount.setText(dollars+" $");
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    public void clickCard2(View view) {
        if (shopPage == 1) {
            //buyWeapon(1);
            current = 1;
            setDecription();
        } else if (shopPage == 2) {
            //buyWeapon(7);
            current =7;
            setDecription();
        } else {
            //buyWeapon(12);
            current = 12;
            setDecription();
        }
        dollarsAmount.setText(dollars+" $");
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    public void clickCard3(View view) {
        if (shopPage == 1) {
            //buyWeapon(2);
            current =2;
            setDecription();
        } else if (shopPage == 2) {
            //buyWeapon(8);
            current =8;
            setDecription();
        } else {
            //buyWeapon(12);
            current =12;
            setDecription();
        }
        dollarsAmount.setText(dollars+" $");
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    public void clickCard4(View view) {
        if (shopPage == 1) {
            //buyWeapon(3);
            current =3;
            setDecription();
        } else if (shopPage == 2) {
            //buyWeapon(9);
            current =9;
            setDecription();
        } else {
            //buyWeapon(12);
            current =12;
            setDecription();
        }
        dollarsAmount.setText(dollars+" $");
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    public void clickCard5(View view) {
        if (shopPage == 1) {
            //buyWeapon(4);
            current =4;
            setDecription();
        } else if (shopPage == 2) {
            //buyWeapon(10);
            current =10;
            setDecription();
        } else {
            //buyWeapon(12);
            current =12;
            setDecription();
        }
        dollarsAmount.setText(dollars+" $");
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    public void clickCard6(View view) {
        if (shopPage == 1) {
            //buyWeapon(5);
            current =5;
            setDecription();
        } else if (shopPage == 2) {
            //buyWeapon(11);
            current =11;
            setDecription();
        } else {
            //buyWeapon(12);
            current =12;
            setDecription();
        }
        dollarsAmount.setText(dollars+" $");
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    public void backToLobby(View view) {
        Intent intent = new Intent(Shop.this, Lobby.class);
        startActivity(intent);
    }

    public void decreaseShopPage(View view) {
        shopPage--;
        if (shopPage < 1) {
            shopPage = 1;
        }
        setGoods();

        dollarsAmount.setText(dollars+" $");
    }

    public void increaseShopPage(View view) {
        shopPage++;
        if (shopPage > 3) {
            shopPage = 3;
        }
        setGoods();
    }


    protected void setGoods() {
        switch (itemTypesPage) {

            case 0:
            if (shopPage == 1) {
                card1image.setImageResource(weapons.get(0).img);
                card2image.setImageResource(weapons.get(1).img);
                card3image.setImageResource(weapons.get(2).img);
                card4image.setImageResource(weapons.get(3).img);
                card5image.setImageResource(weapons.get(4).img);
                card6image.setImageResource(weapons.get(5).img);

                card1text.setText(weapons.get(0).getPrice() + " $");
                card2text.setText(weapons.get(1).getPrice() + " $");
                card3text.setText(weapons.get(2).getPrice() + " $");
                card4text.setText(weapons.get(3).getPrice() + " $");
                card5text.setText(weapons.get(4).getPrice() + " $");
                card6text.setText(weapons.get(5).getPrice() + " $");

            }

            if (shopPage == 2) {
                card1image.setImageResource(weapons.get(6).img);
                card2image.setImageResource(weapons.get(7).img);
                card3image.setImageResource(weapons.get(8).img);
                card4image.setImageResource(weapons.get(9).img);
                card5image.setImageResource(weapons.get(10).img);
                card6image.setImageResource(weapons.get(11).img);

                card1text.setText(weapons.get(6).getPrice() + " $");
                card2text.setText(weapons.get(7).getPrice() + " $");
                card3text.setText(weapons.get(8).getPrice() + " $");
                card4text.setText(weapons.get(9).getPrice() + " $");
                card5text.setText(weapons.get(10).getPrice() + " $");
                card6text.setText(weapons.get(11).getPrice() + " $");
            }
            if (shopPage == 3) {
                card1image.setImageResource(weapons.get(12).img);
                card6text.setText(weapons.get(12).getPrice() + " $");
                // card2image.setImageResource(weapons.get(13).bulletImg);
                // card3image.setImageResource(weapons.get(14).bulletImg);
                //  card4image.setImageResource(weapons.get(9).bulletImg);
                //card5image.setImageResource(weapons.get(10).bulletImg);
                //textViewCard11.setText(weapons.get(12).weaponName);
            }
            break;

            case 1:

                if (shopPage == 1) {
                    card1image.setImageResource(armors.get(0).img);
                    card2image.setImageResource(armors.get(1).img);
                    card3image.setImageResource(armors.get(2).img);
                    card4image.setImageResource(armors.get(3).img);
                    card5image.setImageResource(armors.get(4).img);
                    card6image.setImageResource(armors.get(5).img);

                    card1text.setText(armors.get(0).getPrice() + " $");
                    card2text.setText(armors.get(1).getPrice() + " $");
                    card3text.setText(armors.get(2).getPrice() + " $");
                    card4text.setText(armors.get(3).getPrice() + " $");
                    card5text.setText(armors.get(4).getPrice() + " $");
                    card6text.setText(armors.get(5).getPrice() + " $");

                }

                if (shopPage == 2) {
                    card1image.setImageResource(armors.get(0).img);
                    card2image.setImageResource(armors.get(1).img);
                    card3image.setImageResource(armors.get(2).img);
                    card4image.setImageResource(armors.get(3).img);
                    card5image.setImageResource(armors.get(4).img);
                    card6image.setImageResource(armors.get(5).img);

                    card1text.setText(armors.get(0).getPrice() + " $");
                    card2text.setText(armors.get(1).getPrice() + " $");
                    card3text.setText(armors.get(2).getPrice() + " $");
                    card4text.setText(armors.get(3).getPrice() + " $");
                    card5text.setText(armors.get(4).getPrice() + " $");
                    card6text.setText(armors.get(5).getPrice() + " $");
                }

                break;
        }

    }


    protected void setWeapons()
    {

        weapons.add(new Item( 0,1, 15,110, 30,1,500, R.drawable.groza,R.drawable.groza_up, 1, "Гроза",
                "Штурмовая винтовка калибра 7.62х39 мм. " +
                        "Автоматический режим огня. Магазин на 30 патронов."));






        weapons.add(new Item(0,2, 20, 160, 96,2,2000, R.drawable.shotgun, R.drawable.aa12_up, 3, "Атчиссон АА-12",
                "Боевой дробовик с магазином барабанного типа на 32 патрона, автоматический режим стрельбы" +
                        "Разработан Максвеллом Атчиссоном по заказу Иниумского Директоратора." +
                        " Страшнейшее оружие ближнего боя"));

        weapons.add(new Item(0,3, 5, 300,200,1,1800, R.drawable.machinegun,R.drawable.mashinegun_up, 1, "М249", "Бельгийский ручной пулемёт" +
                "производства Fabrique National. Калибр 7.62 мм, патронная коробка объёмом" +
                " 200 патронов - практически не нуждается в перезарядке. " +
                "При снаряжении бронебойными патронами превращается в сокрушительное орудие " +
                "возмездия"));

        weapons.add(new Item(0,4, 25, 100, 6, 5, 2500, R.drawable.pistol,R.drawable.rubinar_small_up, 1, "LSP Рубинар", ""
                + "Одной из самых выдающихся разработок Теневой Конфедерации (помимо модуляторов низкотемпературной плазмы" +
                ") является изобретение импульсного энергооружия. Такое оружие использует специально разработанные " +
                "одноразовые энергопатроны, которые, в отличие от обычных энергобатарей, невозможно перезарядить" +
                " и использовать заново. Этим и достигается такая огромная мощность"));

        weapons.add(new Item(0,5, 30, 80,7, 1, 200,R.drawable.deagle, R.drawable.deagle_up, 1, "Пустынный орёл", ""
                + "Пистолет большой мощности под револьверные патроны калибка .44. Повышенная точность"));

        weapons.add(new Item(0,6, 45, 200,9,1, 400, R.drawable.remington, R.drawable.remington_up, 3, "Ремингтон 870", ""
                + "Дробовик системы Ремингтон. Помповая система. Подствольный трубчатый магазин на 3" +
                " стандартных ружейных патрона калибра 12. Большой урон в ближнем бою"));

        weapons.add(new Item(0,7, 35, 150, 5,3,800, R.drawable.winchester, R.drawable.winchester_up, 1, "Винчестер М1894", ""
                + "Охотничье ружьё под боеприпасы с цельнометаллической оболочкой калибра .223. " +
                " Ствол длиной 51 сантиметр обепечивает высокую точность стрельбы. Магазин на 5 патронов."));

        weapons.add(new Item(0,8, 10, 150,20,1,650, R.drawable.pp2000, R.drawable.pp2000_up, 1, "ПП-2000", ""
                + "Пистолет-пулемёт под унитарный патрон 9х19 мм Парабеллум. " +
                "Автоматический режим стрельбы. Магазин на 20 патронов."));

        weapons.add(new Item(0,9, 30, 180, 5,4, 1400, R.drawable.svd, R.drawable.svd_up,1, "СВД", ""
                + "Снайперская винтовка Драгунова. Магазин на 5 патронов калибра 7.62. Оптический прицел и длинный ствол" +
                " (61 см) позволяет поражать цели на больших расстояниях"));

        weapons.add(new Item(0,10, 20, 240,30,1,1100, R.drawable.shotgun, R.drawable.groza_up, 3, "СПАС-12", ""
                + "Боевой итальянский дробовик. Трубчатый магазин на 10 патронов. Атоматический режим стреьбы"));

        weapons.add(new Item(0,11, 27, 60,7,2,350, R.drawable.blackstar,R.drawable.balckstar_up, 1, "Блэкстар", ""
                + "Пистолет Пустынный Орёл ограниченной серии. Увеличен урон, скорость стрельбы и скорость перезарядки"));

        weapons.add(new Item(0,12, 25, 120,10,3,1000, R.drawable.winchester_mod,R.drawable.winchestermod_up, 1, "Винтовка М1894 (улучш.)", ""
                + "Винтовка Винчестер с оптическим прицелом. Увеличенный магазин. Юонус к шансу попадания в голову"));

        weapons.add(new Item(0,13,25, 150,20, 4,1600, R.drawable.striker,R.drawable.striker_up, 1, "Страйкер", ""
                + "Модифированная снайперская винтовка Драгунова. Добавлен более мощный оптический прицел, а также барабанный магазин на 20 патронов. Бонус к шансу попадания в голову"));

        weapons.add(new Item(0,14,35, 120,6, 1,150, R.drawable.magnum,R.drawable.rubinar_up, 1, "Револьвер магнум", ""
                + "Оружие находчивых ковбоев и свободных стрелков. Пусть с этим револьвером и нельзя устранить целую армию плохих парней, но его не стыдно взять " +
                "на перестрелку в стиле Дикого Запада."));
    }

    protected void buyWeapon(int weaponsId) {
        if (dollars >= weapons.get(weaponsId).getPrice())
        {
            dollars-=weapons.get(weaponsId).getPrice();
            if (House.weapons.size() == 0) {
                House.weapons.add(weapons.get(weaponsId));
            } else {
                for (int i = 0; i < House.weapons.size(); i++) {
                    if (House.weapons.get(i).getId() == weapons.get(weaponsId).getId()) {
                        Log.d("iter", "проход");
                        House.weapons.get(i).increaseQuantity();
                        break;
                    }
                    if (i == House.weapons.size() - 1) {
                        House.weapons.add(weapons.get(weaponsId));
                        Log.d("iter", "добавление");
                    }
                }
            }

        }
    }

    public void setArmors()
    {
        weapons.add(new Item(1 ,15,R.drawable.armor1,R.drawable.armor1, 200, 50, 1, "Куртка", "Описание куртки"));
        weapons.add(new Item(1 ,16,R.drawable.armor2,R.drawable.armor1, 400, 70, 1, "Кожаная куртка", "Описание куртки"));
        weapons.add(new Item(1 ,17,R.drawable.armor3,R.drawable.armor1, 600, 85, 1, "Плотный костюм", "Описание костюма"));
        weapons.add(new Item(1 ,18,R.drawable.armor4,R.drawable.armor1, 800, 120, 2, "Костюм толстяка", "Описание костюма"));
        weapons.add(new Item(1 ,19,R.drawable.armor5,R.drawable.armor1, 1200, 160, 2, "Легкий бронежилет", "Описание бронежилета"));
        weapons.add(new Item(1 ,20,R.drawable.armor6,R.drawable.armor1, 1600, 210, 3, "Рыцарские доспехи", "Описание доспехов"));

    }

    public void buy(View view) {

            buyWeapon(current);


    }

    void setDecription()
    {
        if(itemTypesPage == 0) {
            description.setText(weapons.get(current).getDescription());
            weaponImage.setImageResource(weapons.get(current).getImg());
        }
        if (itemTypesPage == 1) {
            description.setText(armors.get(current).getDescription());
            weaponImage.setImageResource(armors.get(current).getImg());

        }
        }

    public void armourPage(View view) {
        //itemTypesPage = 1;
        weapons.clear();
        //setWeapons();
        setArmors();
        setGoods();
    }

    public void weaponsPage(View view) {
        weapons.clear();
        setWeapons();
        setGoods();
    }
}
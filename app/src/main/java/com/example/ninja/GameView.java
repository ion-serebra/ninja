package com.example.ninja;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.ninja.weapons.MachineGun;
import com.example.ninja.weapons.Shotgun;
import com.example.ninja.weapons.Item;

public class GameView extends SurfaceView implements Runnable {
    /**
     * Объект класса GameLoopThread
     */
    private GameThread mThread;


    public int shotX;
    public int shotY;

    /**
     * Переменная запускающая поток рисования
     */
    private boolean running = false;

    private List<Bullet> ball = new ArrayList<Bullet>();
    private List<Bullet> enemyBullets = new ArrayList<Bullet>();
    private Player player;

    Bitmap players;


    ////////////////////////////////////////////

    int touchX;
    int touchY;
    int touchX2;
    int touchY2;

    int joystickX = 40;
    int joystickY = 635;
    int joystickWidth = 325;
    int joystickHeight = 325;
    int joystickRightWidth = 400;
    int joystickRightHeight = 400;

    public float dpi;


    //public float playerX = 900;
    //public float playerY = 400;
    public float playerCenterX=20;
    public float playerCenterY= 24.15f;

    static float px2;
    static float py2;

    //float playerSpeed = 7;
    //float playerSpeedX = 0;
    //float playerSpeedY = 0;

    public float backgroundOffsetX = 0f;
    public float backgroundOffsetY = 0f;

    static float box2;
    static float boy2;


    float u;


    byte joystickFlag;

    boolean shoot;
    boolean isPressed;

    Joystick joystickRight;
    Joystick Joystick;


    Matrix playerMatrix;
    Matrix joystickMatrix;
    Matrix backgroundMatrix;
    Matrix joystickRightMatrix;
    Matrix bulletMatrix;
    Matrix exitButtonMatrix;


    Bitmap background;
    Bitmap bitmap;
    Bitmap joystick;
    Bitmap bullet;
    Bitmap exitButton;
    public Human human;


    boolean shootFlag = false;

    float bulletCounter = 0;

    float bulletX = 0;
    float bulletY = 0;

    float bulletSpeed = 10f;
    float bulletDirection;

    int shootCounter = 0;

    Bullet b;

    int levelNumber;


    // Enemies
    private List<Enemy> enemy = new ArrayList<Enemy>();

    Bitmap enemies;

    private Thread thread = new Thread(this);

    Context menuContext;

    boolean exitFlag;

    boolean attack = false;
    int dmgCounter;

    boolean survival = false;
    int waveCounter;
    int waves = 0;

    int width = Lobby.width;
    int height = Lobby.height;

    Paint p = new Paint();




    //Weapons:
    MachineGun machineGun;
    Shotgun shotgun;
    Item weapon;


    GlobalValues gl;

    long beginTime;
    long timeDiff;
    int sleepTime;// сколько мс можно спать (<0 если выполнение опаздывает)
    int framesSkipped;// число кадров у которых не выполнялась операция вывода графики на экран

    // желательный fps
    private final static int MAX_FPS=60;
    // максимальное число кадров, которые можно пропустить
    private final static int MAX_FRAME_SKIPS=5;
    // период, который занимает кадр(последовательность обновление-рисование)
    private final static int FRAME_PERIOD=1000/ MAX_FPS;

    boolean shooting;
    boolean shooting2;


    TitleBackground titleBackground;
    Level currentLvl;
    byte tileX;
    byte tileY;


    byte collisionMatrix[][] = {
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,1,1,1,0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
    };

    // тестовые переменные
    boolean leftWall = false;

    float angleHorizontal;
    boolean upShootLine;


    @Override
    public void run() {
        while (true) {
            Random rnd = new Random();
            try {
                Thread.sleep(rnd.nextInt(20));
                //enemy.add(new Enemy(this, enemies));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //-------------Start of GameThread--------------------------------------------------\\

    public class GameThread extends Thread {


        /**
         * Объект класса
         */
        private GameView view;

        /**
         * Конструктор класса
         */
        public GameThread(GameView view) {
            this.view = view;
        }

        /**
         * Задание состояния потока
         */
        public void setRunning(boolean run) {
            running = run;
        }

        /**
         * Действия, выполняемые в потоке
         */
        @SuppressLint("WrongCall")
        public void run() {
            while (running) {
                Canvas canvas = null;
                try {
                    // подготовка Canvas-а
                    canvas = view.getHolder().lockCanvas();
                    synchronized (view.getHolder()) {
                        // собственно рисование
                        beginTime = System.currentTimeMillis();
                        calculation();
                        testCollision();
                        onDraw(canvas);
                        timeDiff = System.currentTimeMillis()-beginTime;
                        sleepTime=(int)(FRAME_PERIOD- timeDiff);
                        //mThread.sleep(20-timeDiff);

                        if(sleepTime>0)
                        {
                            // если sleepTime > 0 все хорошо, мы идем с опережением
                            try
                            {
                                // отправляем поток в сон на период sleepTime
                                // такой ход экономит к тому же заряд батареи
                                Thread.sleep(sleepTime);
                            } catch(InterruptedException e){}
                        }

                        while(sleepTime<0 || framesSkipped< MAX_FRAME_SKIPS){
                            // если sleepTime < 0 нам нужно обновлять игровую
                            // ситуацию и не тратить время на вывод кадра
                            calculation();
                            testCollision();
                            // добавляем смещение FRAME_PERIOD, чтобы иметь
                            // время границы следующего кадра
                            sleepTime+= FRAME_PERIOD;
                            framesSkipped++;
                        }


                    }
                } catch (Exception e) {
                } finally {
                    if (canvas != null) {
                        view.getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    //-------------End of GameThread--------------------------------------------------\\
/*
         !!!!!  Конструктор GameView  !!!!!
         ||||||||||||||||||||||||||||||||||
         vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv


 */


    public GameView(Context context, byte level) {
        super(context);
        dpi = context.getResources().getDisplayMetrics().density;
        levelNumber = level;
        menuContext = context;

        currentLvl = new Level( new byte[][]{
                {3,1,1,3,1,2,3,4,5,6,2,1,4,1,2,5},
                {1,6,6,1,7,7,7,7,7,6,3,1,4,1,2,5},
                {1,6,6,1,1,2,3,4,7,6,5,1,2,1,2,3},
                {3,1,1,3,1,2,3,4,7,6,3,1,4,1,2,6},
                {3,1,1,7,5,7,3,4,7,6,2,1,2,3,2,5},
                {3,1,1,7,1,7,3,4,7,6,1,5,4,3,2,5},
                {3,5,1,7,1,7,3,4,7,6,5,1,2,3,3,5},
                {5,1,1,7,1,7,7,7,7,6,4,1,4,1,2,5},
                {3,5,3,7,1,2,3,4,7,6,3,5,4,1,6,4},
                {5,1,1,7,7,7,7,7,7,6,3,1,2,3,2,5},
                {2,5,2,3,1,2,3,4,7,6,4,1,4,1,2,2},
                {3,1,3,5,1,2,3,5,7,6,1,1,2,1,4,1},
                {3,1,5,3,5,2,5,4,5,6,4,3,4,3,2,2},
                {3,5,1,5,1,5,3,4,7,6,4,1,4,3,2,5},
                {3,1,5,3,5,2,5,4,7,6,3,1,2,3,2,5},
                {3,5,1,5,1,5,3,4,7,6,1,2,4,1,5,3},
        });
        human = new Human(this);

        //playerSpeed = human.getSpeed();
        //dpi = context.getResources().getDisplayMetrics().density;
        //playerSpeed = playerSpeed * dpi;
        bulletSpeed = bulletSpeed * dpi;
        joystickWidth = (int) (118*dpi);
        joystickHeight = (int) (118*dpi);
        joystickRightWidth = (int) (118*dpi);
        joystickRightHeight = (int) (118*dpi);


        ///Intent intent = new Intent(getContext(), MainActivity.class);
        ///intent.s



        mThread = new GameThread(this);

        /*Рисуем все наши объекты и все все все*/
        getHolder().addCallback(new SurfaceHolder.Callback() {
            /*** Уничтожение области рисования */
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                mThread.setRunning(false);
                while (retry) {
                    try {
                        // ожидание завершение потока
                        mThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            /** Создание области рисования */
            public void surfaceCreated(SurfaceHolder holder) {
                mThread.setRunning(true);
                mThread.start();
            }

            /** Изменение области рисования */
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        /*
        players= BitmapFactory.decodeResource(getResources(), R.drawable.player2);
        player= new Player(this, players);
    */

        Joystick = new Joystick(40, (int) (Lobby.height-163*dpi), joystickWidth, joystickHeight);
        joystickRight = new Joystick((int) (Lobby.width-163*dpi), (int) (Lobby.height-163*dpi), joystickRightWidth, joystickRightHeight);




        backgroundMatrix = new Matrix();
        //backgroundMatrix.preScale(10f, 10f);

        enemies = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);

        switch (levelNumber) {
            case 1:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundimage6);
                enemy.add(new Enemy(this, enemies));
                enemy.add(new Enemy(this, enemies,new Item(0,5, 30, 80,7, 1, 200,R.drawable.deagle, R.drawable.deagle_up, 1, "Пустынный орёл", ""
                        + "Пистолет большой мощности под револьверные патроны калибка .44. Повышенная точность")));
                break;

            case 2:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.level_2);
                enemy.add(new Enemy(this, enemies));
                enemy.add(new Enemy(this, enemies,new Item(0,5, 30, 80,7, 1, 200,R.drawable.deagle, R.drawable.deagle_up, 1, "Пустынный орёл", ""
                        + "Пистолет большой мощности под револьверные патроны калибка .44. Повышенная точность")));
                enemy.add(new Enemy(this, enemies, new Item(0,8, 10, 150,20,1,650,
                        R.drawable.pp2000, R.drawable.pp2000_up, 1, "ПП-2000", ""
                        + "Пистолет-пулемёт под унитарный патрон 9х19 мм Парабеллум. " +
                        "Автоматический режим стрельбы. Магазин на 20 патронов.")));
                break;

            case 3:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.level_3);
                enemy.add(new Enemy(this, enemies));
                enemy.add(new Enemy(this, enemies,new Item(0,5, 30, 80,7, 1, 200,R.drawable.deagle, R.drawable.deagle_up, 1, "Пустынный орёл", ""
                        + "Пистолет большой мощности под револьверные патроны калибка .44. Повышенная точность")));
                enemy.add(new Enemy(this, enemies, new Item(0,8, 10, 150,20,1,650,
                        R.drawable.pp2000, R.drawable.pp2000_up, 1, "ПП-2000", ""
                        + "Пистолет-пулемёт под унитарный патрон 9х19 мм Парабеллум. " +
                        "Автоматический режим стрельбы. Магазин на 20 патронов.")));
                enemy.add(new Enemy(this, enemies));
                break;

            case 4:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundimage6);
                survival = true;
                break;
        }
        //background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundimage);

        joystickMatrix = new Matrix();
        joystickMatrix.postTranslate(Joystick.joystickX, Joystick.joystickY);

        joystickRightMatrix = new Matrix();
        //joystickRightMatrix.setScale(joystickRightHeight / 325f, joystickRightWidth / 325f);
        joystickRightMatrix.postTranslate(joystickRight.joystickX,joystickRight.joystickY);

        bulletMatrix = new Matrix();

        bullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);


        joystick = BitmapFactory.decodeResource(getResources(), R.drawable.joystick);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player3);


        //playerX = human.x;
        //playerY = human.y;

        exitButton = BitmapFactory.decodeResource(getResources(), R.drawable.button_exit);
        exitButtonMatrix = new Matrix();
        exitButtonMatrix.setScale(3f, 3f);


        weapon = House.weapons.get(House.weaponId);
        weapon.setGameView(this);


        gl = new GlobalValues();

        human.humanBitmap = bitmap;

        titleBackground = new TitleBackground(getContext());
        currentLvl.background = titleBackground;











        //enemy.add(new Enemy(this, enemies));

    }


    public boolean hasEnemy() {


        Intent intent = new Intent(getContext(), MainActivity.class);

        if(enemy.size() == 0) { return true;}
        else return false;

    }


    /**
     * Функция рисующая все спрайты и фон
     */
    protected void onDraw(Canvas canvas) {
        waveCounter++;
        if(survival == true&&waveCounter==50)
        {
            enemy.add(new Enemy(this, enemies));
            waveCounter =0;
            waves++;
        }



        //p.setColor(Color.BLUE);
        p.setTextSize(72);
        p.setStrokeWidth(12);


        ///////////////////////////////////////////


        canvas.drawBitmap(background, backgroundMatrix, null);
        //currentLvl.background.drawBackground(canvas, currentLvl.matrix,
           //   dpi, backgroundOffsetX, backgroundOffsetY);


        //canvas.drawBitmap(bitmap,playerMatrix, null);
        Joystick.drawJoystick(joystick, joystickMatrix, canvas);
        joystickRight.drawJoystick(joystick, joystickRightMatrix, canvas);
        //canvas.drawBitmap(joystick, joystickMatrix,null);
        //human.humanBitmap = bitmap;
        human.drawHuman(human.humanBitmap,human.getMatrix(), canvas);

        p.setColor(Color.WHITE);

        canvas.drawText("ВОЛНА "+ waves, 800, 200, p);
        canvas.drawText("box:"+backgroundOffsetX +" boy:"+backgroundOffsetY, 800, 300, p);

        tileX = (byte) (human.x/(currentLvl.background.titleSize*dpi));
        tileY = (byte) (human.y/(currentLvl.background.titleSize*dpi));


        canvas.drawText("X:"+(human.x) +" Y:"+(human.y), 800, 380, p);
        canvas.drawText("Tile X: "+ tileX +
                "Tile Y: "+ tileY, 800, 460, p);
        canvas.drawText("angleHorizontal"+ angleHorizontal, 800, 620, p);
        canvas.drawText("direction"+(joystickRight.direction+180), 800, 700, p);
        //canvas.drawText(""+playerX+backgroundOffsetX, 500, 200, p);
        //canvas.drawText("width"+background.getWidth()/dpi+", Height" + background.getHeight()/dpi+" dpi: "+ dpi
        //        , 500, 400, p);
        //canvas.drawText("JWidth"+joystick.getWidth()/dpi+", Height" + joystick.getHeight()/dpi+" dpi: "+ dpi
        //       , 600, 500, p);
        canvas.drawText("LeftWall" + leftWall, 500, 500, p);

        canvas.drawText(weapon.getName(), 100, 300, p);
        canvas.drawText(timeDiff+"", 100, 500, p);
        canvas.drawPoint(human.x + backgroundOffsetX + human.centerX*dpi, human.y + backgroundOffsetY + human.centerX*dpi, p);
        canvas.drawBitmap(exitButton , exitButtonMatrix , null);
        p.setColor(Color.RED);
        canvas.drawLine(human.x+backgroundOffsetX, human.y+backgroundOffsetY, human.x + human.hp*4+backgroundOffsetX, human.y+backgroundOffsetY, p);
        p.setColor(Color.BLUE);





        if(human.hp <1) {
            human.hp = 0;
            exitFlag = true;
            canvas.drawText("ВЫ ПОГИБЛИ, НАЖМИТЕ ДЛЯ ПРОДОЛЖЕНИЯ", 400, 500, p);
            Iterator<Enemy> i = enemy.iterator();
            while (i.hasNext()) {
                Enemy enemies = i.next();
                i.remove();
            }
        }

        bulletCounter++;
        if ((bulletCounter % 10 == 0) && (shooting||shooting2)) {
            shootFlag = true;
            //bulletMatrix.postRotate(45,playerX+bitmap.getWidth()/2, playerY+bitmap.getHeight()/2);
            bulletX = human.x + playerCenterX*dpi;
            bulletY = human.y + playerCenterY*dpi;
            bulletDirection = joystickRight.direction;
        }
        /*
        if(shootFlag)
        {
            //bulletMatrix.setRotate(joystickRight.direction, bulletX, bulletY);
            bulletX+= bulletSpeed*Math.cos(bulletDirection*3.14f/180);
            bulletY+= bulletSpeed*Math.sin(bulletDirection*3.14f/180);
            bulletMatrix.setTranslate(bulletX+backgroundOffsetX, bulletY+backgroundOffsetY);
            bulletMatrix.postRotate(bulletDirection+180, bulletX+backgroundOffsetX, bulletY+backgroundOffsetY);
            canvas.drawBitmap(bullet, bulletMatrix, null);
        }
         */
        human.weapon = weapon;
        //if( (shooting||shooting2) && upShootLine) {
            human.shoot(b, canvas, (ArrayList) ball, joystickFlag, shoot, joystickRight.isShooting(touchX2, touchY2));
       // }

       // Iterator<Bullet> j = ball.iterator();
       // human.weapon.shoot(j,b,canvas,(ArrayList) ball);
        Iterator<Bullet> jj = enemyBullets.iterator();

        //weapon.shoot(j,b,canvas, (ArrayList) ball);

        Iterator<Enemy> i = enemy.iterator();

        int enemyCount = 0;

        while (i.hasNext()) {
            Enemy e = i.next();

                e.onDraw(canvas);

                //e.weapon.prepareBullet((ArrayList) enemyBullets, (int) ((int) e.x+backgroundOffsetX+e.eCenterX*dpi),
                      //  (int) ((int) e.y+backgroundOffsetY+e.eCenterY*dpi), Math.atan2((e.y-human.y),(e.x-human.x))+3.1415926);
                e.shoot(b,canvas, (ArrayList) enemyBullets);


                canvas.drawPoint(backgroundOffsetX + e.x, backgroundOffsetY + e.y, p);
                canvas.drawPoint(backgroundOffsetX + e.x + e.bmp.getWidth() / 2, backgroundOffsetY + e.y + e.bmp.getHeight() / 2, p);
                canvas.drawPoint(backgroundOffsetX + e.x, backgroundOffsetY + e.y + e.bmp.getHeight() / 2, p);
                canvas.drawPoint(backgroundOffsetX + e.x + e.bmp.getWidth() / 2, backgroundOffsetY + e.y, p);



            p.setColor(Color.CYAN);
            canvas.drawText("X:"+(e.x) +" Y:"+(e.y), backgroundOffsetX + e.x, backgroundOffsetY + e.y-100, p);
            canvas.drawText("X:"+e.getTileX()+" Y:"+e.getTileY(), backgroundOffsetX + e.x, backgroundOffsetY + e.y-50, p);
            p.setColor(Color.RED);
                canvas.drawLine(backgroundOffsetX + e.x, backgroundOffsetY + e.y,
                        backgroundOffsetX + e.x, backgroundOffsetY + e.y +e.hp*30 , p);
               // p.setColor(Color.BLUE);

                enemyCount++;

                //canvas.drawText("   attack : " + e.attack, 400, 300 * enemyCount, p);

        }


    }

     public Bullet createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Bullet(this, bmp);
    }
    public Bullet createSprite(int resource, double angle) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Bullet(this, bmp, angle);
    }



    public boolean onTouchEvent(MotionEvent event) {
        //ball.add(new Bullet(bmp));
        // u = joystick.getDirection(touchX,touchY);
/*
        if(joystickRight.isJoystickTouched(touchX,touchY))
        {
            joystickRight.direction = joystickRight.getDirection(touchX, touchY);
        }
*/
        switch (event.getActionMasked()) {


            case MotionEvent.ACTION_MOVE:
                //s=" s: "+touchX+"  "+touchY;

                int pointerCount = event.getPointerCount();
                int pointerIndex;
                int pointerId;

                for (int i = 0; i < pointerCount; i++) {
                    pointerIndex = i;
                    pointerId = event.getPointerId(pointerIndex);

                    if (pointerId == 0)  // первое касание
                    {
                        //testX1 = event.getX(pointerIndex);

                        touchX = (int) event.getX(pointerIndex);
                        touchY = (int) event.getY(pointerIndex);

                        if (joystickFlag == 0) {
                            joystickRight.direction = joystickRight.getDirection(touchX, touchY);
                            joystickRight.degree = joystickRight.getDirection(touchX);
                            if(touchY<joystickRight.joystickY)
                            {
                                upShootLine = true;
                            } else {
                                upShootLine = false;
                            }
                                shoot = joystickRight.isShooting(touchX, touchY);
                        }

                        if (joystickFlag == 1) {
                            Joystick.direction = Joystick.getDirection(touchX, touchY);
                            u = Joystick.getDirection(touchX, touchY);
                            isPressed = true;
                        }
                        /*
                        if (Joystick.isJoystickTouched(touchX, touchY)) {
                            joystickFlag = 1;
                            Joystick.direction = Joystick.getDirection(touchX, touchY);
                            u = Joystick.getDirection(touchX, touchY);
                            isPressed = true;

                        }


                        if (joystickRight.isJoystickTouched(touchX, touchY)||shooting) {
                            joystickFlag = 0;
                            joystickRight.direction = joystickRight.getDirection(touchX, touchY);
                            shoot = joystickRight.isShooting(touchX, touchY);

                        }

                         */


                    }
                    if (pointerId == 1)  // второе касание
                    {
                        //testX2 = event.getX(pointerIndex);

                        touchX2 = (int) event.getX(pointerIndex);
                        touchY2 = (int) event.getY(pointerIndex);

                        if (joystickFlag == 0) {
                            Joystick.direction = Joystick.getDirection(touchX2, touchY2);
                            u = Joystick.getDirection(touchX2, touchY2);
                            isPressed = true;


                        }

                        if (joystickFlag == 1) {
                            if(joystickRight.isJoystickTouched(touchX2, touchY2))
                            shooting2 = true;
                            joystickRight.direction = joystickRight.getDirection(touchX2, touchY2);
                            joystickRight.degree = joystickRight.getDirection(touchX2);

                            if(touchY2<joystickRight.joystickY)
                            {
                                upShootLine = true;
                            } else {
                                upShootLine = false;
                            }

                            shoot = joystickRight.isShooting(touchX2, touchY2);
                        }

                        /*
                        if (joystickFlag == 1) {
                            shooting2 = true;
                            joystickRight.direction = joystickRight.getDirection(touchX2, touchY2);
                            shoot = joystickRight.isShooting(touchX2, touchY2);

                        }

                        if (joystickFlag == 0) {
                            Joystick.direction = Joystick.getDirection(touchX2, touchY2);
                            u = Joystick.getDirection(touchX2, touchY2);
                            isPressed = true;
                        }

                         */
                    }
                }
                break;

            case MotionEvent.ACTION_DOWN:

                touchX = (int) event.getX();
                touchY = (int) event.getY();

                if (joystickRight.isJoystickTouched(touchX, touchY)) {
                    joystickFlag = 0;
                }

                if (Joystick.isJoystickTouched(touchX, touchY)) {
                    joystickFlag = 1;
                }


                if (touchX < 150 && touchY < 150) {
                    exitButtonMatrix.setScale(2f, 2f);
                    exitFlag = true;
                }

                if (joystickRight.isJoystickTouched(touchX, touchY)) {
                    shooting = true;
                }


                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                touchX = (int) event.getX();
                touchY = (int) event.getY();
                if (joystickRight.isJoystickTouched(touchX, touchY)) {
                    shooting = true;
                }



                break;

            case MotionEvent.ACTION_UP:
                isPressed = false;
                shoot = false;
                upShootLine = false;


                if (exitFlag && enemy.size() == 0) {
                    ((Activity) menuContext).setContentView(R.layout.activity_main);
                } else {
                    exitButtonMatrix.setScale(3f, 3f);
                    exitFlag = false;
                }
                shooting = false;
                shooting2 = false;



                //angleHorizontal = 0;

                break;


            case MotionEvent.ACTION_POINTER_UP:
                //isPressed = false;
                shoot = false;
                shooting = false;
                shooting2 = false;

                if(event.getPointerId(0) == 1 )
                {
                    shooting2 = false;
                    shooting = false;
                }





        }
        return true;
    }



    public void calculation() {
        if (isPressed) {


        //    if(joystickRight.direction+180 < 90 || joystickRight.direction+180 > 270) {

                human.x += human.getSpeed() * Math.cos(u * 3.14f / 180);
                human.y += human.getSpeed() * Math.sin(u * 3.14f / 180);

          //  }
/*
            else
            {
                human.x -= human.getSpeed() * Math.cos(u * 3.14f / 180);
                human.y -= human.getSpeed() * Math.sin(u * 3.14f / 180);
            }

 */


           // backgroundOffsetX += human.getSpeed()  * Math.cos(u * 3.14f / 180);
            //backgroundOffsetY += human.getSpeed()  * Math.sin(u * 3.14f / 180);


        }

        //  ограничения, чтобы персонаж не выходил за игровое поле

        if(human.x < 5)
        {
            human.x = 5;
        }

        if(human.x > width-backgroundOffsetX-45)
        {
            human.x =  width-backgroundOffsetX-45;
        }

        if(human.y < 5)
        {
            human.y = 5;
        }

        if(human.y > height-backgroundOffsetY-45)
        {
            human.y =  height-backgroundOffsetY-45;
        }






        for(int i = 0;i<16;i++)
        {
            for(int j = 0;j<16;j++)
            {
                 byte a = collisionMatrix[j][i];
                 if(a == 1) {

                     // вроде вертикальные стены
                     if ((currentLvl.background.titleSize * dpi * i < human.x + human.centerX * dpi) && human.x + human.centerX * dpi < currentLvl.background.titleSize * dpi * (i + 1)) {
                         if (human.y + human.centerY * dpi > currentLvl.background.titleSize * j * dpi - 1 && human.y + human.centerY * dpi <
                                 currentLvl.background.titleSize * j * dpi + currentLvl.background.titleSize* dpi/2) {
                             human.y = currentLvl.background.titleSize * j *dpi - 2 - human.centerY * dpi;
                         }
                         if (human.y + human.centerY * dpi < currentLvl.background.titleSize * dpi * (j + 1) + 1 && human.y + human.centerY * dpi >
                                 currentLvl.background.titleSize * j * dpi + currentLvl.background.titleSize* dpi/2) {
                             human.y = currentLvl.background.titleSize * (j+1) *dpi + 2 - human.centerY * dpi;
                         }
                     }




                     // наверное горизонтальные
                     if ((currentLvl.background.titleSize * dpi * j < human.y + human.centerY * dpi) && human.y + human.centerY * dpi < currentLvl.background.titleSize * dpi * (j + 1)) {
                         if (human.x + human.centerX * dpi > currentLvl.background.titleSize * i *dpi - 10 && human.x + human.centerX * dpi <
                                 currentLvl.background.titleSize * i * dpi + currentLvl.background.titleSize* dpi/2) {
                             human.x = currentLvl.background.titleSize * i *dpi - 10 - human.centerX * dpi;
                         }
                         if (human.x + human.centerX * dpi < currentLvl.background.titleSize * dpi * (i + 1) + 10 && human.x + human.centerX * dpi >
                                 currentLvl.background.titleSize * i * dpi + currentLvl.background.titleSize* dpi/2) {
                             human.x = currentLvl.background.titleSize * (i+1) *dpi + 10 - human.centerX * dpi;
                         }
                     }



                 }
            }
        }




        human.setSpeedX((float) (human.getSpeed() * Math.cos(u * 3.14f / 180) * Math.cos((joystickRight.direction+180) * 3.14f / 180)));
        human.setSpeedY((float) (human.getSpeed() * Math.sin(u * 3.14f / 180)* Math.sin((joystickRight.direction+180) * 3.14f / 180)));

        if(shooting || shooting2)
        {
            angleHorizontal -= joystickRight.degree * 0.05;
        }

        human.matrix = new Matrix();
        human.matrix.postScale(0.5f, 0.5f);

        if(joystickRight.direction+180 < 90 || joystickRight.direction+180 > 270) {

        human.speed = 3.5f*dpi;

        }


        human.move(backgroundOffsetX, backgroundOffsetY);



        human.rotate(joystickRight.direction, dpi, backgroundOffsetX,backgroundOffsetY);


        human.setSpeedX(Math.abs(human.getSpeed() * (float) Math.cos(joystickRight.direction)));
        human.setSpeedY(Math.abs(human.getSpeed() * (float) Math.sin(joystickRight.direction)));



        if(joystickRight.direction+180 < 90 || joystickRight.direction+180 > 270) {

            if ((backgroundOffsetX + human.x) < 1000) {
                backgroundOffsetX += human.getSpeed() * Math.abs(Math.cos(u * 3.14f / 180));
                if (backgroundOffsetX > -10) {
                    backgroundOffsetX = -10;
                }
            }

            if ((backgroundOffsetX + human.x) > 1130) {
                backgroundOffsetX -= human.getSpeed() * Math.abs(Math.cos(u * 3.14f / 180));
                if (backgroundOffsetX < width - background.getWidth()) {
                    backgroundOffsetX = width - background.getWidth();
                }
            }


        }
        else
        {
            if ((backgroundOffsetX + human.x) < 1000) {
                backgroundOffsetX += human.getSpeed() * Math.abs(Math.cos(u * 3.14f / 180));
                if (backgroundOffsetX > -10) {
                    backgroundOffsetX = -10;
                }
            }

            if ((backgroundOffsetX + human.x) > 1130) {
                backgroundOffsetX -= human.getSpeed() * Math.abs(Math.cos(u * 3.14f / 180));
                if (backgroundOffsetX < width - background.getWidth()) {
                    backgroundOffsetX = width - background.getWidth();
                }
            }
        }



        if ((backgroundOffsetY + human.y) < 500) {  // при движении вверх

            if(joystickRight.direction+180 < 90 || joystickRight.direction+180 > 270) {

                backgroundOffsetY += human.getSpeed() * Math.abs(Math.sin(u * 3.14f / 180));
                if (backgroundOffsetY > -10) {
                    backgroundOffsetY = -10;
                }

            }
            else {

                backgroundOffsetY += human.getSpeed() * Math.abs(Math.sin(u * 3.14f / 180));
                if (backgroundOffsetY > -10) {
                    backgroundOffsetY = -10;
                }

            }
        }

        if ((+backgroundOffsetY + human.y) > 540) {   // при движении вниз
            backgroundOffsetY -= human.getSpeed()*Math.abs(Math.sin(u* 3.14f / 180));
            if (backgroundOffsetY < height-background.getHeight()) {
                backgroundOffsetY = height-background.getHeight();
            }
        }

        backgroundMatrix.setTranslate(backgroundOffsetX, backgroundOffsetY);   //X:-67  , Y:-1200

       // backgroundMatrix.preScale(1f, 1f);

        //backgroundMatrix.postRotate(joystickRight.direction+180, human.x + backgroundOffsetX + human.centerX*dpi, human.y + backgroundOffsetY + human.centerX*dpi);

        box2 = backgroundOffsetX;
        boy2 = backgroundOffsetY;



        px2 = human.x;
        py2 = human.y;

        if (enemy.size() == 0) {
            attack = false;
            //Activity a = (Activity) menuContext;
            //((Activity) menuContext).setContentView(R.layout.activity_main);
        }
    }


    /*Проверка на столкновения*/
    private void testCollision() {
        Iterator<Bullet> b = ball.iterator();
        while (b.hasNext()) {
            Bullet balls = b.next();
            Iterator<Enemy> i = enemy.iterator();
            while (i.hasNext()) {
                Enemy enemies = i.next();

                if ((((backgroundOffsetX +balls.x) <= (backgroundOffsetX + enemies.x + enemies.bmp.getWidth()/2) / 1)
                        && ((backgroundOffsetX +balls.x) >= (backgroundOffsetX + enemies.x)))
                        && (((backgroundOffsetY +balls.y) <= (backgroundOffsetY + enemies.y + enemies.bmp.getHeight()/2) / 1)
                        && ((backgroundOffsetY +balls.y) >= (backgroundOffsetY + enemies.y))))
                {
                    enemies.hp-=weapon.getDamage();
                    b.remove();
                    if(enemies.hp <= 0) {
                        i.remove();
                        Shop.dollars+=100;
                    }


                }
            }
        }






        Iterator<Bullet> enB = enemyBullets.iterator();
        while (enB.hasNext()) {
            Bullet enBalls = enB.next();

            if ((((backgroundOffsetX +enBalls.x) <= (backgroundOffsetX + human.x + bitmap.getWidth()/2) / 1)
                    && ((backgroundOffsetX +enBalls.x) >= (backgroundOffsetX + human.x)))
                    && (((backgroundOffsetY +enBalls.y) <= (backgroundOffsetY + human.y + bitmap.getHeight()/2) / 1)
                    && ((backgroundOffsetY +enBalls.y) >= (backgroundOffsetY + human.y))))
            {
                human.hp--;
                enB.remove();
            }


        }



        Iterator<Enemy> i = enemy.iterator();



/*
        Enemy en1;
        Enemy en2;
        for(int k = 0; k < enemy.size(); k++)
        {
            en1 = enemy.get(k);
            for(int kk = 0;kk < enemy.size(); kk++)
            {
               en2 = enemy.get(kk);
               if(Math.sqrt((en1.x-en2.x)*(en1.x-en2.x)+(en1.y-en2.y)*(en1.y-en2.y))<10)
               {
                   en2.setSpeed(0);
               }
               else
               {
                   en2.setSpeed((int) (2*dpi));
               }
            }
        }


 */




        while (i.hasNext())
        {
            Enemy enemies = i.next();

            if((Math.sqrt((human.x-enemies.x)*(human.x-enemies.x)
            +(human.y-enemies.y)*(human.y-enemies.y))<300))
            {
                //enemies.speed = 0;
                //enemies.attack = true;
                dmgCounter++;
                if(dmgCounter>50)
                {
                    dmgCounter = 0;
                    human.hp--;
                }
            }
            else{
                enemies.speed=(int) (2*dpi);
                enemies.attack = false;
            }
        }



    }
}
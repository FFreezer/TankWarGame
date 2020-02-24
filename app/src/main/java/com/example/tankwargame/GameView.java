package com.example.tankwargame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    private Context mContext;
    private Thread mGameThread = null;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private Canvas mCanvas;

    private volatile boolean mRunning;

    private long fps;

    private int mViewHeight, mViewWidth;
    private Bitmap mPlayerTankBitmap, mAITankBitmap;
    private Tank playerTank, aiTank;
    private Wall wall1,wall2,wall3;

    private GameControls mControls;
    //We are using two different ArrayLists so that we can reduce the amount of unnecessary calculations during collision detection
    private ArrayList<GameObject> gameObjects;
    private ArrayList<IMovable> movableGameObjects;

    //Constructor
    GameView(Context context, GameControls controls) {
        super(context);
        this.mContext = context;
        this.mControls = controls;
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mPaint.setColor(Color.DKGRAY);
        mPlayerTankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ptankup);
        mAITankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aitankdown);
        gameObjects = new ArrayList<>();
        movableGameObjects = new ArrayList<>();
        initialiseControls();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initialiseControls(){
        /**
        * Method Details
        * Attaches listener events to each of the buttons for moving the player tanks
        **/
        //Controls for left button
        mControls.mButtonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed down
                        playerTank.setBitmapFile(mContext,R.drawable.ptankleft);
                        playerTank.isMovingLeft = true;
                        Log.d("LEFT BUTTON", "PRESSED");
                        return true;
                    case MotionEvent.ACTION_UP:
                        // Released
                        playerTank.isMovingLeft = false;
                        Log.d("LEFT BUTTON", "UNPRESSED");
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        // Released - Dragged finger outside
                        playerTank.isMovingLeft = false;
                        return true;
                }
                return false;
            }
        });

        //Controls for right button
        mControls.mButtonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed down
                        playerTank.setBitmapFile(mContext, R.drawable.ptankright);
                        playerTank.isMovingRight = true;
                        Log.d("RIGHT BUTTON", "PRESSED");
                        return true;
                    case MotionEvent.ACTION_UP:
                        // Released
                        playerTank.isMovingRight = false;
                        Log.d("RIGHT BUTTON", "UNPRESSED");
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        // Released - Dragged finger outside
                        playerTank.isMovingRight = false;
                        return true;
                }
                return false;
            }
        });

        //Controls for up button
        mControls.mButtonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed down
                        playerTank.setBitmapFile(mContext, R.drawable.ptankup);
                        playerTank.isMovingUp = true;
                        Log.d("UP BUTTON", "PRESSED");
                        return true;
                    case MotionEvent.ACTION_UP:
                        // Released
                        playerTank.isMovingUp = false;
                        Log.d("UP BUTTON", "UNPRESSED");
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        // Released - Dragged finger outside
                        playerTank.isMovingUp = false;
                        return true;
                }
                return false;
            }
        });

        //Controls for down button
        mControls.mButtonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed down
                        playerTank.setBitmapFile(mContext,R.drawable.ptankdown);
                        playerTank.isMovingDown = true;
                        Log.d("DOWN BUTTON", "PRESSED");
                        return true;
                    case MotionEvent.ACTION_UP:
                        // Released
                        playerTank.isMovingDown = false;
                        Log.d("DOWN BUTTON", "UNPRESSED");
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        // Released - Dragged finger outside
                        playerTank.isMovingDown = false;
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /**
         * Method Details
         * Cannot currently get the width and height of the screen via onCreate so must get them as the view inflates.
         * This method is called when the view inflates
         * 1 Tanks are created
         * 2 Walls are created
         *
         **/
        super.onSizeChanged(w, h, oldw, oldh);
        this.mViewHeight = h;
        this.mViewWidth = w;
        //Instantiate game objects NOW as you need screen height and width to do so
        playerTank = new Tank(mContext, R.drawable.ptankup, ((w / 2) - (mPlayerTankBitmap.getWidth() / 2)), (h - (mPlayerTankBitmap.getHeight() * 2)), 'u');
        aiTank = new Tank(mContext, R.drawable.aitankdown, ((w / 2) - (mAITankBitmap.getWidth() / 2)), mAITankBitmap.getHeight(),'d');
        wall1 = new Wall(mContext, w, h);
        wall2 = new Wall(mContext, w, h);
        wall3 = new Wall(mContext, w, h);
        GameObject shellTest = new Shell(mContext, playerTank, 'd', 50, 50);

        movableGameObjects.add(playerTank);
        movableGameObjects.add(aiTank);
        movableGameObjects.add((IMovable) shellTest);
        gameObjects.add(playerTank);
        gameObjects.add(aiTank);
        gameObjects.add(wall1);
        gameObjects.add(wall2);
        gameObjects.add(wall3);
        gameObjects.add(shellTest);
    }

    //This is our main game loop
    @Override
    public void run() {
        while (mRunning) {
            long startFrameTime = System.currentTimeMillis();
            update();
            draw();
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                //The following 1000 comes from 1000ms in a second
                fps = 1000 / timeThisFrame;
            }
        }
    }

    private void draw() {
        /**
         * Method Details
         * Draw the newly updated scene and its on screen objects
         * 1. Check canvas is valid
         * 2. Save the current canvas so we can draw to it without affecting it
         * 3. Set background color for the map (or background image)
         * 4. Draw tanks
         * 5. Draw walls
         * 6. Restore canvas
         * 7. Unlock canvas and draw
         * */
        if (mSurfaceHolder.getSurface().isValid()) { //1
            this.mCanvas = mSurfaceHolder.lockCanvas(); //2
            this.mCanvas.save(); //2
            this.mCanvas.drawColor(getResources().getColor(R.color.game_background_color)); //3
            for(int iterator = 0; iterator < gameObjects.size(); iterator++){
                gameObjects.get(iterator).draw(mCanvas, mPaint);
            }
            this.mCanvas.restore();
            this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void update() {
        /**
         * Method Details
         * 1. Iterate through all game objects that are able to move as two 'immovable' objects cannot collide
         * 2. Attempt to move the object
         * 3. Check against all other game objects for collision using current direction
         * 4. If a collision is detected, move the object the opposite direction it was moving when the collision happened
         * 5. Method logic is complete
         * */
        for(int iterator = 0; iterator < movableGameObjects.size(); iterator++){
            IMovable currentMovableObject = movableGameObjects.get(iterator);
            movableUpdateHelper(currentMovableObject);
        }
    }

    private void movableUpdateHelper(IMovable gameobject){
        /**
         * Method Details
         * NOTE : This is the helper method ONLY for game objects that can move like tank shells and tanks
         *
        * */
        if(gameobject.getIsMovingLeft()){gameobject.moveLeft(fps);}
        if(gameobject.getIsMovingRight()){gameobject.moveRight(fps);}
        if(gameobject.getIsMovingUp()){gameobject.moveUp(fps);}
        if(gameobject.getIsMovingDown()){gameobject.moveDown(fps);}
//        Log.d("Position", "X " + gameobject.getPosX() + " Y : " + gameobject.getPosY() + " FPS " + fps);
//        Log.d("Movement Params", "" + gameobject.isMovingLeft);
    }

    //Called in main activity to restart thread
    public void resume() {
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    //Called from main activity to suspend threads when game is minimised or rotated
    public void pause() {
        mRunning = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.d("Error : ", "Attempted pause failed.");
            Log.d("Error Message : ", e.getCause().toString());
        }
    }
}


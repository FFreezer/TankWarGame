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
    private ArrayList<GameObject> gameObjects;

    //Constructor
    public GameView(Context context, GameControls controls) {
        super(context);
        this.mContext = context;
        this.mControls = controls;
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mPaint.setColor(Color.DKGRAY);
        mPlayerTankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ptankup);
        mAITankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aitankdown);
        gameObjects = new ArrayList<>();
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
        playerTank = new Tank(mContext, mPlayerTankBitmap, ((w / 2) - (mPlayerTankBitmap.getWidth() / 2)), (h - (mPlayerTankBitmap.getHeight() * 2)));
        aiTank = new Tank(mContext, mAITankBitmap, ((w / 2) - (mAITankBitmap.getWidth() / 2)), mAITankBitmap.getHeight());
        wall1 = new Wall(mContext, w, h);
        wall2 = new Wall(mContext, w, h);
        wall3 = new Wall(mContext, w, h);
        gameObjects.add(playerTank);
        gameObjects.add(aiTank);
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
            this.mCanvas.drawBitmap(playerTank.mBitmapFile, playerTank.getX(), playerTank.getY(), mPaint); //4
            this.mCanvas.drawBitmap(aiTank.mBitmapFile, aiTank.getX(), aiTank.getY(), mPaint); //4
            this.mCanvas.drawRect(wall1.getLeft(), wall1.getTop(), wall1.getRight(), wall1.getBottom(), mPaint);
            this.mCanvas.drawRect(wall2.getLeft(), wall2.getTop(), wall2.getRight(), wall2.getBottom(), mPaint);
            this.mCanvas.drawRect(wall3.getLeft(), wall3.getTop(), wall3.getRight(), wall3.getBottom(), mPaint);
            this.mCanvas.restore();
            this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void update() {
        for(int iterator = 0; iterator < gameObjects.size(); iterator++){
            GameObject currentObject = gameObjects.get(iterator);
            updateHelper(currentObject);
        }
    }

    private void updateHelper(GameObject gameobject){
        if(gameobject.isMovingLeft){gameobject.moveLeft(fps);}
        if(gameobject.isMovingRight){gameobject.moveRight(fps);}
        if(gameobject.isMovingUp){gameobject.moveUp(fps);}
        if(gameobject.isMovingDown){gameobject.moveDown(fps);}
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

//    private void initialiseControls() {
////        ASK RORY WHY THE FUCK THIS WONT WORK
//        Button buttons[] = {
//                mControls.mButtonLeft,
//                mControls.mButtonRight,
//                mControls.mButtonDown,
//                mControls.mButtonUp
//        };
//
//        boolean isMovingValues[] = {
//                playerTank.isMovingLeft,
//                playerTank.isMovingRight,
//                playerTank.isMovingDown,
//                playerTank.isMovingUp
//        };
//
//        for(int i = 0; i < buttons.length; i++){
//            buttons[i].setOnTouchListener(new View.OnTouchListener(){
//                @Override
//                public boolean onTouch(View v, MotionEvent event){
//                    switch(event.getAction()){
//                        case MotionEvent.ACTION_DOWN:
//                            // Pressed down
//                            isMovingValues[i] = true;
//                            Log.d("LEFT BUTTON", "PRESSED");
//                            return true;
//                        case MotionEvent.ACTION_UP:
//                            // Released
//                            isMovingValues[i] = false;
//                            Log.d("LEFT BUTTON", "UNPRESSED");
//                            return true;
//                        case MotionEvent.ACTION_CANCEL:
//                            // Released - Dragged finger outside
//                            isMovingValues[i] = false;
//                            return true;
//                    }
//                    return false;
//                }
//            });
//        }
//    }


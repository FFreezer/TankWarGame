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
import android.widget.Button;
import android.widget.ImageView;

import java.nio.file.Path;
import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    private Context mContext;
    private Thread mGameThread = null;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private Canvas mCanvas;

    private volatile boolean mRunning;

    private long timeThisFrame;
    private long fps;

    private int mViewHeight, mViewWidth;
    private Bitmap mPlayerTankBitmap, mAITankBitmap;
    private Tank playerTank, aiTank;

    private GameControls mControls;
    private ArrayList<GameObject> gameObjects;

    //Constructor
    public GameView(Context context, GameControls controls) {
        super(context);
        this.mContext = context;
        this.mControls = controls;
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mPlayerTankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ptankup);
        mAITankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aitankdown);
        gameObjects = new ArrayList<GameObject>();
        initialiseControls();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initialiseControls() {
        mControls.mButtonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed down
//                        playerTank.isMovingLeft = true;
                        playerTank.moveLeft(fps);
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
    }

    /**
     * Cannot currently get the width and height of the screen via onCreate so must get them as the view inflates.
     * This method is called when the view changes and attempts to move the tanks to an initial starting position
     **/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mViewHeight = h;
        this.mViewWidth = w;
        playerTank = new Tank(mContext, mPlayerTankBitmap,
                ((w / 2) - (mPlayerTankBitmap.getWidth() / 2)),
                (h - (mPlayerTankBitmap.getHeight() * 2)));
        aiTank = new Tank(mContext, mAITankBitmap,
                ((w / 2) - (mAITankBitmap.getWidth() / 2)),
                mAITankBitmap.getHeight());
        gameObjects.add(playerTank);
//        gameObjects.add(aiTank);
    }

    //This is our main game loop
    @Override
    public void run() {
        while (mRunning) {
            long startFrameTime = System.currentTimeMillis();
            update();
            draw();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                //The following 1000 comes from 1000ms in a second
                fps = 1000 / timeThisFrame;
            }
        }
    }

    /*
     * Draw the newly updated scene and its on screen objects
     * 1. Lock canvas so it is ready to be draw on
     * 2. Draw the background color to green for grass
     * 3. Choose the paint brush color for drawing player lives
     * 4. Change text size to make it a bit larger and legible
     * 5. Display fps on screen (Can be hidden this is just temporary)
     * 6. Draw player tank and ai tank
     * 6.5 Cycle through ADT that holds drawable objects and draw them
     * 7. Draw everything to screen by unlocking surface and posting canvas to it
     * */
    private void draw() {
        if (mSurfaceHolder.getSurface().isValid()) {
            this.mCanvas = mSurfaceHolder.lockCanvas();
            this.mCanvas.save();
            this.mCanvas.drawColor(getResources().getColor(R.color.game_background_color));
            this.mCanvas.drawBitmap(playerTank.mBitmapFile, playerTank.getX(), playerTank.getY(), mPaint);
            this.mCanvas.drawBitmap(aiTank.mBitmapFile, aiTank.getX(), aiTank.getY(), mPaint);
            this.mCanvas.restore();
            this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void update() {
        for(int iterator = 0; iterator < gameObjects.size(); iterator-=-1){ //lol
            GameObject currentObject = gameObjects.get(iterator);
            updateHelper(currentObject);
        }
    }

    private void updateHelper(GameObject gameobject){
        if(gameobject.isMovingLeft){gameobject.moveLeft(fps);}
        if(gameobject.isMovingRight){gameobject.moveRight(fps);}
        Log.d("Position", "X " + gameobject.getPosX() + " Y : " + gameobject.getPosY());
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



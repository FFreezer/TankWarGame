package com.example.tankwargame;

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
import android.widget.ImageView;

import java.nio.file.Path;

public class GameView extends SurfaceView implements Runnable {

    private Context mContext;
    private Thread mGameThread = null;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private volatile boolean mPlaying = false;
    //    private boolean mPaused = true;
    private boolean mRunning;

    private long fps;
    private long timeThisFrame;

    private int mViewHeight, mViewWidth;
    private ImageView mImageView1, mImageView2;
    private Bitmap mPlayerTankBitmap, mAITankBitmap;
    private Tank playerTank, aiTank;

    public GameView(Context context) {
        super(context);
        this.mContext = context;
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mPlayerTankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ptankup);
        mAITankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aitankdown);
    }

    /**
     * Cannot currently get the width and height of the screen via onCreate so must get them as the view inflates.
     * This method is called when the view changes and attempts to move the tanks to an initial starting position
     **/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
        playerTank = new Tank(mContext, mPlayerTankBitmap,
                                ((w / 2) -  (mPlayerTankBitmap.getWidth() / 2)),
                                (h - (mPlayerTankBitmap.getHeight() * 2)));
        aiTank = new Tank(mContext, mAITankBitmap,
                ((w / 2) -  (mAITankBitmap.getWidth() / 2)),
                mAITankBitmap.getHeight());

    }

    @Override
    public void run() {
        Canvas mCanvas;
        while (mRunning) {
            if (mSurfaceHolder.getSurface().isValid()) {
                mCanvas = mSurfaceHolder.lockCanvas();
                mCanvas.save();
                mCanvas.drawColor(getResources().getColor(R.color.game_background_color));
                mCanvas.drawBitmap(playerTank.mBitmapFile, playerTank.getX(), playerTank.getY(), mPaint);
                mCanvas.drawBitmap(aiTank.mBitmapFile, aiTank.getX(), aiTank.getY(), mPaint);
                mCanvas.restore();
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }

        mCanvas = mSurfaceHolder.lockCanvas();
    }

    private void initialiseGame() {

    }

    private void draw() {
    }

    public void update() {
    }

    public void resume() {
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

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

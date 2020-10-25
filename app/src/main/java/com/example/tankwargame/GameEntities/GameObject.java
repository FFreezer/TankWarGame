package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameObjectStorage;
import com.example.tankwargame.GameView;

public abstract class GameObject {

    protected long posX, posY;
    protected int mWidth, mHeight;
    protected Bitmap mBitmapFile = null;
    protected Context mContext;
    protected GameView mGameView;

    //Access Methods
    public long getX() {
        return this.posX;
    }

    public long getY() {
        return this.posY;
    }

    public int getmWidth(){ return this.mWidth; }

    public int getmHeight(){ return this.mHeight; }

    public Bitmap getBitmapFile(){ return this.mBitmapFile; }

    public Context getContext(){ return this.mContext; }

    //Transformers
    public void setBitmapFile(Context currentContext, int resourceID) {
        this.mBitmapFile = BitmapFactory.decodeResource(currentContext.getResources(), resourceID);
    }

    public void setPosX(long newX) { this.posX = newX; }

    public void setPosY(long newY) { this.posY = newY; }

    //Unique Methods
    public void draw(Canvas canvas, Paint paint) { canvas.drawBitmap(mBitmapFile, posX, posY, paint); }

    public void destroy(){
        GameObjectStorage.removeGameObject(this);
    }

}
package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameObjectStorage;

public abstract class GameObject {

    protected long posX, posY;
    protected int mWidth, mHeight;
    protected Bitmap mBitmapFile = null;
    protected boolean isMovingLeft,
            isMovingRight,
            isMovingUp,
            isMovingDown = false;
    protected Context mContext;
    protected MovingDirection mMovingDirection = null;

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

    public MovingDirection getMovingDirection(){ return mMovingDirection; }

    //Transformers
    public void setBitmapFile(Context currentContext, int resourceID) {
        this.mBitmapFile = BitmapFactory.decodeResource(currentContext.getResources(), resourceID);
    }

    public void setDirection(MovingDirection newMovingDirection){ this.mMovingDirection = newMovingDirection; }

    public void toggleIsMovingRight() { this.isMovingRight = !isMovingRight; }

    public void toggleIsMovingLeft() { this.isMovingLeft = !isMovingLeft; }

    public void toggleIsMovingUp() { this.isMovingUp = !isMovingUp; }

    public void toggleIsMovingDown() { this.isMovingDown = !isMovingDown;}

    public void setPosX(long newX) { this.posX = newX; }

    public void setPosY(long newY) { this.posY = newY; }

    //Unique Methods
    public void draw(Canvas canvas, Paint paint) { canvas.drawBitmap(mBitmapFile, posX, posY, paint); }

    public void destroy(){
        GameObjectStorage.removeGameObject(this);
    }

}
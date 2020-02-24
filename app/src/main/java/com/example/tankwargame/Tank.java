package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Tank extends GameObject implements IMovable {

    private Context mContext;
    boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown = false;
    private Character mDirection;
    private final long speed = 150;

    Tank(Context context, int bitmapResource, int x, int y, Character direction){
        this.mContext = context;
        this.mBitmapFile = BitmapFactory.decodeResource(context.getResources(), bitmapResource);
        this.posX = x;
        this.posY = y;
        this.mDirection = direction;
        this.width = this.getBitmapFile().getWidth();
        this.height = this.getBitmapFile().getHeight();
    }

    //get methods
    public Context getContext(){ return this.mContext; }
    public Bitmap getBitmapFile(){ return this.mBitmapFile; }

    @Override
    public boolean checkForCollision(GameObject potentialCollider) {
        return false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(this.mBitmapFile, posX, posY, paint);
    }

    public void fireShell(){

    }


    //Implement Methods
    public void moveLeft(long fps) {
        posX = posX - (speed / (fps + 1));
        mDirection = 'l';
    }
    public void moveRight(long fps) {
        posX = posX + (speed / (fps + 1));
        mDirection = 'r';
    }
    public void moveUp(long fps) {
        posY = posY - (speed / (fps + 1));
        mDirection = 'u';
    }
    public void moveDown(long fps) {
        posY = posY + (speed / (fps + 1));
        mDirection = 'd';
    }
    public boolean getIsMovingLeft(){ return isMovingLeft; }
    public boolean getIsMovingRight() { return isMovingRight; }
    public boolean getIsMovingDown() { return isMovingDown; }
    public boolean getIsMovingUp() { return isMovingUp; }
}

/*
*   TODO
*    1. Fix and implement the checkForCollision method
* */

package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;

public class Tank extends GameObject implements IMoveable{

    private Context mContext;
    private final long speed = 150;
    protected boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown = false;

    public Tank(Context context, Bitmap bitmapResource, int x, int y) {
        this.mContext = context;
        this.mBitmapFile = bitmapResource;
        this.posX = x;
        this.posY = y;
    }
    public Context getContext(){ return this.mContext; }

    public Bitmap getBitmapFile(){ return this.mBitmapFile; }

    //getters
    public boolean getIsMovingLeft(){return isMovingLeft;}
    @Override
    public boolean getIsMovingRight() { return isMovingRight; }
    @Override
    public boolean getIsMovingDown() { return isMovingDown; }

    @Override
    public boolean checkForCollision(GameObject potentialCollider) {
        return false;
    }

    @Override
    public boolean getIsMovingUp() { return isMovingUp; }

    //Implement Methods
    public void moveLeft(long fps) {
        posX = posX - (speed / (fps + 1));
    }
    public void moveRight(long fps) {
        posX = posX + (speed / (fps + 1));
    }
    public void moveUp(long fps) {
        posY = posY - (speed / (fps + 1));
    }
    public void moveDown(long fps) {
        posY = posY + (speed / (fps + 1));
    }

}

/*
*   TODO
*    1. Fix and implement the checkForCollision method
* */

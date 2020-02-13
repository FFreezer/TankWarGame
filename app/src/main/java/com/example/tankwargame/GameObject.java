package com.example.tankwargame;

import android.graphics.Bitmap;

public abstract class GameObject {
    protected int posX;
    protected int posY;
    protected Bitmap mBitmapFile;
    private int mSpeed = 5;

    public int getPosX() {
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public Bitmap getBitmapFile(){ return this.mBitmapFile; }

    public void moveLeft(int amount){ posX = posX - amount; }
    public void moveRight(int amount){ posX = posX + amount; }
    public void moveUp(int amount){ posY = posY + amount; }
    public void moveDown(int amount){ posY = posY - amount; }
}

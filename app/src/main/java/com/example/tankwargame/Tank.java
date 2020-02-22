package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;

public class Tank extends GameObject {

    private Context mContext;

    public Tank(Context context, Bitmap bitmapResource, int x, int y) {
        this.mContext = context;
        this.mBitmapFile = bitmapResource;
        this.posX = x;
        this.posY = y;
    }
    public Context getContext(){ return this.mContext; }
    public long getX(){ return this.posX; }
    public long getY(){ return this.posY; }

    public Bitmap getBitmapFile(){ return this.mBitmapFile; }
    public Bitmap ChangeBitmap(){
        return null;
    }


}

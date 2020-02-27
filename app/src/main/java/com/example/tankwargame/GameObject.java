package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {

    long posX, posY;
    int width, height;
    Bitmap mBitmapFile = null;
    boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown = false;
    Context mContext;


    //Access Methods
    public long getX() {
        return this.posX;
    }

    public long getY() {
        return this.posY;
    }

    public int getWidth(){ return this.width ; }

    public int getHeight(){ return this.height; }

    public Bitmap getBitmapFile(){ return this.mBitmapFile; }

    public Context getContext(){ return this.mContext; }

    //Transformers
    void setBitmapFile(Context currentContext, int resourceID) {
        this.mBitmapFile = BitmapFactory.decodeResource(currentContext.getResources(), resourceID);
    }

    //Unique Methods
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapFile, posX, posY, paint);
    }

    public void destroy(){
        GameObjectStorage.removeGameObject(this);
    }

}
/*TODO
 *
 * */
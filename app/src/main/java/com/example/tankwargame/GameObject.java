package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
    //Store coordinates
    long posX, posY;
    int width, height;
    //Visual style for object
    Bitmap mBitmapFile;
    protected Canvas mCanvas;
    private Context mContext;

    //getters
    public long getX() {
        return this.posX;
    }

    public long getY() {
        return this.posY;
    }

    public int getWidth() {
        return this.mBitmapFile.getWidth();
    }

    public int getHeight() {
        return this.mBitmapFile.getHeight();
    }

    public Context getContext() {
        return mContext;
    }

    //setters
    public void setBitmapFile(Context currentContext, int resourceID) {
        this.mBitmapFile = BitmapFactory.decodeResource(currentContext.getResources(), resourceID);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapFile, posX, posY, paint);
    }

    public void destroy(){
        GameObjectStorage.gameObjects.remove(this);
    }

}
/*TODO
 * 1. Add width and height parameters
 * 2. Modify the creation of any currently instantiated game objects to include those parameters as they will
 *       be used for collision detection
 * 3. Modify move methods to allow for the amount to be divided by the games frame rate to movement is calculated accordingly
 *       and if not messed up by the CPU using inconsistent frame cycle times
 *
 * */
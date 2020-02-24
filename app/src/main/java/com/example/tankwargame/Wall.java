package com.example.tankwargame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Wall extends GameObject {

    private Context mContext;
    private float top, right, bottom, left;
    private final int area = 60_000;

    Wall(Context context, int screenWidth, int screenHeight){
        mContext = context;
        Random randomGenerator = new Random();
        top = randomGenerator.nextInt(screenHeight);
        left = randomGenerator.nextInt(screenWidth);
        right = left + (screenWidth / 4);
        bottom = top + 100;
        this.height = (int) (bottom - top);
        this.width = (int) (right - left);
        this.posX = (long) left;
        this.posY = (long) top;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        canvas.drawRect(left, top, right, bottom, paint);
    }

    //getter methods
    public Context getContext(){return mContext;}
    @Override
    public int getWidth(){return this.width;}
    @Override
    public int getHeight(){return this.height;}
    public float getTop(){return top;}
    public float getBottom(){return bottom;}
    public float getLeft(){return left;}
    public float getRight(){return right;}

}

/*TODO
 * Check area method and make sure size is appropriate and check to see if it needs to be changed
 *   on current screen size, which may vary from device to device
 **/
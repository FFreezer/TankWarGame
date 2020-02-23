package com.example.tankwargame;

import android.content.Context;

import java.util.Random;

public class Wall extends GameObject {

    private Context mContext;
    private int width, height;
    private float top, right, bottom, left;

    Wall(Context context, int screenWidth, int screenHeight){
        /**
        * Constructor Details
        * 1. Retrieve context for painting
        * 2. Calculate random number for further use, multiply by 100_000
        * 3. Modulo random number by 1/4th of screen size to make max width 1/4 of screen width
        * 4.
        * */
        mContext = context;
        Random randomGenerator = new Random();
        top = randomGenerator.nextInt(screenHeight);
        left = randomGenerator.nextInt(screenWidth);
        this.width = (randomGenerator.nextInt(screenWidth) + 1) / 2;
        this.height = (randomGenerator.nextInt(screenHeight) + 1) / 10;
        while(this.width < (screenWidth / 4)){
            this.width = (randomGenerator.nextInt(screenWidth) + 1) / 2;
        }
        while(this.height < (screenHeight / 15)){
            this.height = (randomGenerator.nextInt(screenHeight) + 1) / 10;
        }
        bottom = top + this.height;
        right = left + this.width;

        //imp 1
//        this.mContext = context;
//        int area = 100_000;
//        double rand = Math.random() * 100_000;
//        this.width = (int) ((rand % screenWidth) / 4);
//        this.height = (int) (((area / this.width) % screenHeight) / 6);
//        top = (int) (rand % screenHeight);
//        left = (int) (rand % screenWidth);
//        right = left + this.width;
//        bottom = top + this.height;

    }

    //getter methods
    public Context getContext(){return mContext;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public float getTop(){return top;}
    public float getBottom(){return bottom;}
    public float getLeft(){return left;}
    public float getRight(){return right;}

}

/*TODO
 * Check area method and make sure size is appropriate and check to see if it needs to be changed
 *   on current screen size, which may vary from device to device
 **/
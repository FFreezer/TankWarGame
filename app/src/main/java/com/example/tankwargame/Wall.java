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
         * 1. Get context
         * 2. Create a random number generator
         * 3. Use random number generator to randomly calculate top left corner of wall
         * 4. Generate rectangles using random number
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
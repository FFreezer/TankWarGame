package com.example.tankwargame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class Wall extends GameObject {

    private float top, right, bottom, left;

    Wall(Context context, int screenWidth, int screenHeight){
        mContext = context;
        //The two tanks in this ArrayList MUST be the two tanks
        GameObject[] tanks = {
                (GameObject) GameObjectStorage.movableGameObjects.get(0),
                (GameObject) GameObjectStorage.movableGameObjects.get(1),
        };
        GameObject[] tanks2 = {
                GameObjectStorage.gameObjects.get(0),
                GameObjectStorage.gameObjects.get(1)
        };
        do{
            Random randomGenerator = new Random();
            top = randomGenerator.nextInt(screenHeight);
            left = randomGenerator.nextInt(screenWidth);
            right = left + (screenWidth / 4);
            bottom = top + 100;
            this.height = (int) (bottom - top);
            this.width = (int) (right - left);
            this.posX = (long) left;
            this.posY = (long) top;
        }while(CollisionDetector.checkForCollision(tanks2[0], this) || CollisionDetector.checkForCollision(tanks2[1], this));

    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawRect(left, top, right, bottom, paint);
    }

    //Access Methods
    public float getTop(){return top;}
    public float getBottom(){return bottom;}
    public float getLeft(){return left;}
    public float getRight(){return right;}

}

/*TODO
 * Fix the constructor to cycle through the movable game objects and check that the object is an instanceof Tank so that if the
 *  tanks are not at position 0 or 1 that the walls can still spawn correctly on creation
 **/
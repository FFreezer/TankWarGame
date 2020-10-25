package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tankwargame.CollisionDetector;
import com.example.tankwargame.GameObjectStorage;

import java.util.Random;

public class Wall extends GameObject {

    protected float top, right, bottom, left;
    private static boolean rotation = false;

    public Wall(Context context, int screenWidth, int screenHeight){
        mContext = context;
        //Tanks are always created at object index 0 and 1
        GameObject[] tanks = {
                GameObjectStorage.getGameObject(0),
                GameObjectStorage.getGameObject(1)
//                GameObjectStorage.gameObjects.get(0),
//                GameObjectStorage.gameObjects.get(1)
        };
        do{
            Random randomGenerator = new Random();
            top = randomGenerator.nextInt(screenHeight);
            left = randomGenerator.nextInt(screenWidth);
            if(rotation)
            {
                right = left + (screenWidth / 5);
                bottom = top + 100;
            }
            else
            {
                right = left + 100;
                bottom = top + (screenWidth / 5);
            }
//            right = left + (screenWidth / 5);
//            bottom = top + 100;
            this.mHeight = (int) (bottom - top);
            this.mWidth = (int) (right - left);
            this.posX = (long) left;
            this.posY = (long) top;
            toggleRotation();
        }while(CollisionDetector.checkForCollision(tanks[0], this) || CollisionDetector.checkForCollision(tanks[1], this));
    }

    @Override
    public void draw(Canvas canvas, Paint paint){ canvas.drawRect(left, top, right, bottom, paint); }

    private void toggleRotation(){
        rotation = !rotation;
    }

    //Access Methods
    public float getTop() { return top; }
    public float getBottom() { return bottom; }
    public float getLeft() { return left; }
    public float getRight() { return right; }

    //Set Methods
    public void setTop(float newTop) { this.top = newTop; }
    public void setBottom(float newBottom) { this.bottom = newBottom; }
    public void setRight(float newRight) { this.right = newRight; }
    public void setLeft(float newLeft) { this. left = newLeft; }
}

/*TODO
 * Fix the constructor to cycle through the movable game objects and check that the object is an instanceof Tank so that if the
 *  tanks are not at position 0 or 1 that the walls can still spawn correctly on creation
 **/
package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class Tank extends GameObject implements IMovable {

    private Context mContext;
    boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown = false;
    private Character mDirection;
    private final long speed = 150;

    Tank(Context context, int bitmapResource, int x, int y, Character direction){
        this.mContext = context;
        this.mBitmapFile = BitmapFactory.decodeResource(context.getResources(), bitmapResource);
        this.posX = x;
        this.posY = y;
        this.mDirection = direction;
        this.width = this.getBitmapFile().getWidth();
        this.height = this.getBitmapFile().getHeight();
    }

    //get methods
    private Bitmap getBitmapFile(){ return this.mBitmapFile; }
    @Override
    public int getHeight(){ return this.height ;}
    @Override
    public int getWidth(){ return this.width ;}


    //Implement Methods
    /**
     * move____() methods NOTE :
     *  Calls a helper method to check if the object has collided with another object and if it has then move it back the way it came
     * */
    public void moveLeft(long fps, ArrayList<GameObject> listOfPotentialColliders) {
        posX = posX - (speed / (fps + 1));
        mDirection = 'l';
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
//                Log.d("Player Tank Coords: ", "(X,Y) -> " + this.posX + "," + this.posY);
//                Log.d("Player Tank Dims :", "(W,H) -> " + this.width + "," + this.height);
                if(CollisionDetector.checkForTankCollision(this, listOfPotentialColliders.get(iterator))){
                    posX = posX + (speed / (fps + 1));
                }
            }
        }
    }
    public void moveRight(long fps, ArrayList<GameObject> listOfPotentialColliders) {
        posX = posX + (speed / (fps + 1));
        mDirection = 'r';
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForTankCollision(this, listOfPotentialColliders.get(iterator))){
                    posX = posX - (speed / (fps + 1));
                }
            }
        }
    }
    public void moveUp(long fps, ArrayList<GameObject> listOfPotentialColliders) {
        posY = posY - (speed / (fps + 1));
        mDirection = 'u';
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForTankCollision(this, listOfPotentialColliders.get(iterator))){
                    Log.d("XY", this.posX + "," + this.posY);
                    Log.d("Collision Detected W/: ", listOfPotentialColliders.get(iterator).toString());
                    posY = posY + (speed / (fps + 1));
                }
            }
        }
    }
    public void moveDown(long fps, ArrayList<GameObject> listOfPotentialColliders) {
        posY = posY + (speed / (fps + 1));
        mDirection = 'd';
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForTankCollision(this, listOfPotentialColliders.get(iterator))){
                    posY = posY - (speed / (fps + 1));
                }
            }
        }
    }
    public boolean getIsMovingLeft(){ return isMovingLeft; }
    public boolean getIsMovingRight() { return isMovingRight; }
    public boolean getIsMovingDown() { return isMovingDown; }
    public boolean getIsMovingUp() { return isMovingUp; }

    //Class Methods
    @Override
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(this.mBitmapFile, posX, posY, paint);
    }

    public void fireShell(){
        int spawnLocationX,spawnLocationY;
        spawnLocationX = spawnLocationY = 0;
        switch(mDirection){
            case 'u': //up
                spawnLocationX = (int)(posX + (width / 2));
                spawnLocationY = (int)posY;
                break;
            case 'r': //right
                spawnLocationX = (int)(posX + width);
                spawnLocationY = (int)(posY + (height / 2));
                break;
            case 'd':
                spawnLocationX = (int)(posX + (width / 2));
                spawnLocationY = (int)(posY + getHeight());
                //down
                break;
            case 'l': //left
                spawnLocationX = (int)posX;
                spawnLocationY = (int)(posY + (height / 2));
                break;
        }
        IMovable shell = new Shell(mContext, this, mDirection, spawnLocationX, spawnLocationY);
    }


}

/*
*   TODO
*    1. Fix and implement the checkForCollision method
*    2. Wall3 seems to be the problem
* */

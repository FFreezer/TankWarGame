package com.example.tankwargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

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
    public Context getContext(){ return this.mContext; }
    public Bitmap getBitmapFile(){ return this.mBitmapFile; }


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

    }

    private boolean checkForCollisionHelper(ArrayList<GameObject> listOfPotentialColliders) {
        return false;
    }

}

/*
*   TODO
*    1. Fix and implement the checkForCollision method
* */

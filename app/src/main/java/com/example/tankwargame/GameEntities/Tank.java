package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tankwargame.CollisionDetector;
import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameObjectStorage;
import com.example.tankwargame.GameView;
import com.example.tankwargame.Interfaces.IMovable;

import java.util.ArrayList;

import static com.example.tankwargame.Enums.MovingDirection.UP;

public class Tank extends GameObject implements IMovable {

    private Character mDirection;
    private final long speed = 250;
    private boolean canFire = true;
    private long centerX;
    private long centerY;

    //Constructor
    public Tank(Context context, int bitmapResource, int x, int y, MovingDirection direction){
        this.mContext = context;
        this.mBitmapFile = BitmapFactory.decodeResource(context.getResources(), bitmapResource);
        this.posX = x;
        this.posY = y;
        this.mMovingDirection = direction;
        this.mWidth = this.getBitmapFile().getWidth();
        this.mHeight = this.getBitmapFile().getHeight();
        this.centerX = (this.posX + (this.mWidth / 2));
        this.centerY = (this.posY + (this.mHeight / 2));
    }

    //Implemented Methods
    /**
     * move____() methods NOTE :
     *  Calls a helper method to check if the object has collided with another object and if it has then move it back the way it came
     * */
    public void moveLeft() {
        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.gameObjects;
        long fps = GameView.getFps();
        posX = posX - (speed / (fps + 1));
        mMovingDirection = MovingDirection.LEFT;
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForCollision(this, listOfPotentialColliders.get(iterator))){
                    posX = posX + (speed / (fps + 1));
                }
            }
        }
        updateCenters();
    }
    public void moveRight() {
        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.gameObjects;
        long fps = GameView.getFps();
        posX = posX + (speed / (fps + 1));
//        mDirection = 'r';
        mMovingDirection = MovingDirection.RIGHT;
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForCollision(this, listOfPotentialColliders.get(iterator))){
                    posX = posX - (speed / (fps + 1));
                }
            }
        }
        updateCenters();
    }
    public void moveUp() {
        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.gameObjects;
        long fps = GameView.getFps();
        posY = posY - (speed / (fps + 1));
//        mDirection = 'u';
        mMovingDirection = UP;
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForCollision(this, listOfPotentialColliders.get(iterator))){
                    posY = posY + (speed / (fps + 1));
                }
            }
        }
        updateCenters();
    }
    public void moveDown() {
        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.gameObjects;
        long fps = GameView.getFps();
        posY = posY + (speed / (fps + 1));
//        mDirection = 'd';
        mMovingDirection = MovingDirection.DOWN;
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForCollision(this, listOfPotentialColliders.get(iterator))){
                    posY = posY - (speed / (fps + 1));
                }
            }
        }
        updateCenters();
    }
    public boolean getIsMovingLeft(){ return isMovingLeft; }
    public boolean getIsMovingRight() { return isMovingRight; }
    public boolean getIsMovingDown() { return isMovingDown; }
    public boolean getIsMovingUp() { return isMovingUp; }

    //Access Methods
    public long getCenterX(){
        return this.centerX;
    }

    public long getCenterY(){
        return this.centerY;
    }

    //Transformers

    //Class Methods
    public void updateCenters(){
        centerX = posX + (mWidth / 2);
        centerY = posY + (mHeight / 2);
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(this.mBitmapFile, posX, posY, paint);
    }

    public void fireShell(){
        if(canFire) {
            int spawnLocationX, spawnLocationY;
            spawnLocationX = spawnLocationY = 0;
            switch (mMovingDirection) {
                case UP : //up
                    spawnLocationX = (int) (posX + (mWidth / 2) - 15);
                    spawnLocationY = (int) posY;
                    break;
                case RIGHT: //right
                    spawnLocationX = (int) (posX + mWidth);
                    spawnLocationY = (int) (posY + (mHeight / 2) - 15);
                    break;
                case DOWN:
                    spawnLocationX = (int) (posX + (mWidth / 2) - 15);
                    spawnLocationY = (int) (posY + getmHeight());
                    //down
                    break;
                case LEFT: //left
                    spawnLocationX = (int) (posX);
                    spawnLocationY = (int) (posY + (mHeight / 2) - 15);
                    break;
            }
//            GameObject shell = new Shell(mContext, this, mDirection, spawnLocationX, spawnLocationY);
            GameObject shell = new Shell(mContext, this, mMovingDirection, spawnLocationX, spawnLocationY);
            GameObjectStorage.addGameObject(shell);
            GameObjectStorage.addMovableObject((IMovable) shell);
            this.toggleCanFire();
        }
    }

    public void toggleCanFire(){
        canFire = (canFire == true) ? false : true;
    }
}

/*
*   TODO
*    1. Fix and implement the checkForCollision method
*    2. Wall3 seems to be the problem
* */

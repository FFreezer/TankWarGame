package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.tankwargame.CollisionDetector;
import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameObjectStorage;
import com.example.tankwargame.Interfaces.IMovable;

import java.util.ArrayList;

import static com.example.tankwargame.Enums.MovingDirection.UP;

public class Tank extends GameObject implements IMovable {

    private Character mDirection;
    private final long speed = 250;
    private boolean canFire = true;

    //Constructor
    public Tank(Context context, int bitmapResource, int x, int y, MovingDirection direction){
        this.mContext = context;
        this.mBitmapFile = BitmapFactory.decodeResource(context.getResources(), bitmapResource);
        this.posX = x;
        this.posY = y;
        this.mMovingDirection = direction;
        this.width = this.getBitmapFile().getWidth();
        this.height = this.getBitmapFile().getHeight();
    }

    //Implement Methods
    /**
     * move____() methods NOTE :
     *  Calls a helper method to check if the object has collided with another object and if it has then move it back the way it came
     * */
    public void moveLeft(long fps, ArrayList<GameObject> listOfPotentialColliders) {
        posX = posX - (speed / (fps + 1));
//        mDirection = 'l';
        mMovingDirection = MovingDirection.LEFT;
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForCollision(this, listOfPotentialColliders.get(iterator))){
                    posX = posX + (speed / (fps + 1));
                }
            }
        }
    }
    public void moveRight(long fps, ArrayList<GameObject> listOfPotentialColliders) {
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
    }
    public void moveUp(long fps, ArrayList<GameObject> listOfPotentialColliders) {
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
    }
    public void moveDown(long fps, ArrayList<GameObject> listOfPotentialColliders) {
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
    }
    public boolean getIsMovingLeft(){ return isMovingLeft; }
    public boolean getIsMovingRight() { return isMovingRight; }
    public boolean getIsMovingDown() { return isMovingDown; }
    public boolean getIsMovingUp() { return isMovingUp; }

    //Class Methods
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(this.mBitmapFile, posX, posY, paint);
    }

    public void fireShell(){
        if(canFire) {
            int spawnLocationX, spawnLocationY;
            spawnLocationX = spawnLocationY = 0;
            switch (mMovingDirection) {
                case UP : //up
                    spawnLocationX = (int) (posX + (width / 2) - 15);
                    spawnLocationY = (int) posY;
                    break;
                case RIGHT: //right
                    spawnLocationX = (int) (posX + width);
                    spawnLocationY = (int) (posY + (height / 2) - 15);
                    break;
                case DOWN:
                    spawnLocationX = (int) (posX + (width / 2) - 15);
                    spawnLocationY = (int) (posY + getHeight());
                    //down
                    break;
                case LEFT: //left
                    spawnLocationX = (int) (posX);
                    spawnLocationY = (int) (posY + (height / 2) - 15);
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

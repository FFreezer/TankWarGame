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
import com.example.tankwargame.R;

import java.util.ArrayList;

public class Shell extends MovableObject implements IMovable {

    private Tank mShellOwner;
    private final long speed = 450;

    Shell(Context context, Tank owner, MovingDirection direction, int x, int y) {
        this.mContext = context;
        this.mShellOwner = owner;
        this.posX = x;
        this.posY = y;
        this.mBitmapFile = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tank_shell);
        this.mWidth = this.mBitmapFile.getWidth();
        this.mHeight = this.mBitmapFile.getHeight();
        this.mMovingDirection = direction;
        switch(mMovingDirection){
            case UP:
                isMovingUp = true;
                break;
            case DOWN:
                isMovingDown = true;
                break;
            case LEFT:
                isMovingLeft = true;
                break;
            case RIGHT:
                isMovingRight = true;
                break;
            default:
                isMovingRight = true;
                break;
        }
    }

    //Unique Methods
    public void draw(Canvas canvas, Paint paint){
//        canvas.drawBitmap(mBitmapFile, posX, posY, paint);
        canvas.drawBitmap(getBitmapFile(), getX(), getY(), paint);
    }

    public void destroy(){
        GameObjectStorage.removeGameObject(this);
        GameObjectStorage.removeMovableObject(this);
        mShellOwner.toggleCanFire();
    }

    //Access Methods
    public Tank getShellOwner(){ return mShellOwner; }

    //Implement Methods
    public boolean getIsMovingLeft() {
        return isMovingLeft;
    }
    public boolean getIsMovingRight() {
        return isMovingRight;
    }
    public boolean getIsMovingDown() {
        return isMovingDown;
    }
    public boolean getIsMovingUp() {
        return isMovingUp;
    }

    //Override Methods
    @Override
    public void translatePosition(MovingDirection direction) {
        super.translatePosition(direction);
        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.gameObjects;
        for(int iterator = 0; iterator < GameObjectStorage.getGameObjectsSize(); iterator++){
            GameObject currentObject = listOfPotentialColliders.get(iterator);
            translatePositionHelper(currentObject);
        }
    }

    //Helper Methods
    private void translatePositionHelper(GameObject currentItem){
        if(!this.equals(currentItem)){
            if(CollisionDetector.checkForCollision(this, currentItem)){
                if(!(currentItem instanceof Wall || currentItem.equals(this.mShellOwner))){
                    this.destroy();
                    currentItem.destroy();
                }else if(currentItem instanceof Wall){
                    this.destroy();
                }
            }
        }
    }

}

/*
* TODO
*  BUG FIX >>> There seems to be a collision detection bug for the shells where if you fire several at once and ONE collides
*   with something then it will destroy a bunch of them. LOOK INTO
*
* */
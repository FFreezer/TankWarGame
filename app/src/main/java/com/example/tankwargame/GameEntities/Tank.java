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

public class Tank extends MovableObject implements IMovable {

    protected boolean canFire = true;
    protected long centerX;
    protected long centerY;

    //Constructor
    public Tank(GameView gameView, Context context, int bitmapResource, int x, int y, MovingDirection direction){
        mGameView = gameView;
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

    //Override Methods
    @Override
    public void translatePosition(MovingDirection direction) {
        super.translatePosition(direction);
        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.getAllGameObjects();
//        ArrayList<GameObject> listOfPotentialColliders = GameObjectStorage.gameObjects;
        for(int iterator = 0; iterator < listOfPotentialColliders.size(); iterator++){
            if(!this.equals(listOfPotentialColliders.get(iterator))){
                if(CollisionDetector.checkForCollision(this, listOfPotentialColliders.get(iterator))){
                    undoMovementDueToCollision(direction);
                }
            }
        }
        updateCenters();
    }

    private void undoMovementDueToCollision(MovingDirection direction){
        switch(direction){
            case UP:
                super.translatePosition(MovingDirection.DOWN);
                this.mMovingDirection = direction;
                this.setBitmapFile(mContext, R.drawable.ptankup);
                break;
            case DOWN:
                super.translatePosition(MovingDirection.UP);
                this.mMovingDirection = direction;
                this.setBitmapFile(mContext, R.drawable.ptankdown);
                break;
            case LEFT:
                super.translatePosition(MovingDirection.RIGHT);
                this.mMovingDirection = direction;
                this.setBitmapFile(mContext, R.drawable.ptankleft);
                break;
            case RIGHT:
                super.translatePosition(MovingDirection.LEFT);
                this.mMovingDirection = direction;
                this.setBitmapFile(mContext, R.drawable.ptankright);
                break;
        }
    }

    //Access Methods
    public long getCenterX(){
        return this.centerX;
    }

    public long getCenterY(){
        return this.centerY;
    }

    public boolean isAbleToFire(){ return this.canFire; }


    //Transformers

    //Class Methods
    private void updateCenters(){
        centerX = posX + (mWidth / 2);
        centerY = posY + (mHeight / 2);
    }

    @Override
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
            GameObject shell = new Shell(mGameView, mContext, this, mMovingDirection, spawnLocationX, spawnLocationY);
            GameObjectStorage.addGameObject(shell);
            GameObjectStorage.addMovableObject((IMovable) shell);
            this.toggleCanFire();
        }
    }

    public void toggleCanFire(){
        canFire = !canFire;
    }

    @Override
    public void destroy() {
        super.destroy();
        GameObjectStorage.removeMovableObject(this);
        mGameView.destroyTank(this);
    }
}

/*
*   TODO
*    1. Fix and implement the checkForCollision method
*    2. Wall3 seems to be the problem
* */

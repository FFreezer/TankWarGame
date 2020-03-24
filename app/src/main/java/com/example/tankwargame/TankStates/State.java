package com.example.tankwargame.TankStates;

import android.util.Log;

import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.GameEntities.Tank;
import com.example.tankwargame.Interfaces.IState;

public abstract class State implements IState {

    protected EnemyTank mAITank;
    protected Tank mPlayerTank;
    protected static long comparedToTime = System.currentTimeMillis();
    //Cant think of appropriate name. This value will change every second and a half so that the movement isn't bobbing side to side constantly.
    //IE it forces the movement to only do one thing at a time, left, right, up or down.
    protected boolean movementState = false;

    State(EnemyTank mAITank, Tank mPlayerTank) {
        this.mAITank = mAITank;
        this.mPlayerTank = mPlayerTank;
    }

    /**
     * @return Returns true if AI Tank is right of player. Returns false if left of.
     */
    protected boolean isRightOfPlayer(){
        if(mAITank.getCenterX() >= mPlayerTank.getCenterX()){
            return true;
        }
        return false;
    }

    /**
     * @return Returns true if AI Tank is above player. Returns false if below player.
     */
    protected boolean isAbovePlayer(){
        if(mAITank.getCenterY() <= mPlayerTank.getCenterY()){
            return true;
        }
        return false;
    }

    protected boolean isInYRange(){
        if(Math.abs((mAITank.getX() - mPlayerTank.getX())) < mPlayerTank.getmWidth() * 1.5){
            return true;
        }
        return false;
    }

    protected boolean isInXRange(){
        if(Math.abs((mAITank.getY() - mPlayerTank.getY())) < mPlayerTank.getmHeight() * 1.5){
            return true;
        }
        return false;
    }

    protected float getYDistance(){
        float distance = Math.abs(mAITank.getY() - mPlayerTank.getY());
//        Log.d("Y Distance : ", distance + "");
        return distance;
    }

    protected float getXDistance(){
        float distance = Math.abs(mAITank.getX() - mPlayerTank.getX());
//        Log.d("X Distance : ", distance + "");
        return distance;
    }

    protected int getStraightLineDistance(){
        float xDistance = Math.abs(mAITank.getCenterX() - mPlayerTank.getCenterX());
        float yDistance= Math.abs(mAITank.getCenterY() - mPlayerTank.getCenterY());
        float xDistanceSqr = xDistance * xDistance;
        float yDistanceSqr = yDistance * yDistance;
        int answer = (int) Math.sqrt(xDistanceSqr + yDistanceSqr);
//        Log.d("Straight Line D : ", answer + "");
        return answer;
    }

    protected void toggleMovementDirection(){
        movementState = !movementState;
    }

    //Transformers
    public void updateComparedToTime(){
        comparedToTime = System.currentTimeMillis();
    }
}

package com.example.tankwargame.TankStates;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.GameEntities.Tank;
import com.example.tankwargame.Interfaces.IState;

public class HuntingState extends State implements IState {

    public HuntingState(EnemyTank mAITank, Tank mPlayerTank) {
        super(mAITank, mPlayerTank);
    }

    /*
    * Note :
    * This state is designed to try and get the tank to hunt down the player tank
    * If it comes within a certain distance of the tank it will switch to the evade state and attempt
    * to run away.
    *
    * */
    @Override
    public void Execute() {
        int distance = getStraightLineDistance();
        long changeInTime = System.currentTimeMillis() - State.comparedToTime;
        int tankWidth = mAITank.getmWidth() / 4;
        int tankHeight = mAITank.getmHeight() / 4;

        if(movementState)
        {
            if(isRightOfPlayer())
            {
                if(getXDistance() > tankWidth) //Stops tank from wobbling side to side
                {
                    mAITank.translatePosition(MovingDirection.LEFT);
                }
            }
            else
            {
                if(getXDistance() > tankWidth) //Stops tank from wobbling side to side
                {
                    mAITank.translatePosition(MovingDirection.RIGHT);
                }
            }
            if((mAITank.isAbleToFire() && isInXRange()) && (changeInTime / 2 > 375))
            {
                mAITank.fireShell();
            }
        }
        else
        {
            if(isAbovePlayer())
            {
                if(getYDistance() > tankHeight) //Stops tank from wobbling up and down
                {
                    mAITank.translatePosition(MovingDirection.DOWN);
                }
            }
            else
            {
                if(getYDistance() > tankHeight) //Stops tank from wobbling up and down
                {
                    mAITank.translatePosition(MovingDirection.UP);
                }
            }
            if((mAITank.isAbleToFire() && isInYRange()) && (changeInTime / 2 > 375))
            {
                mAITank.fireShell();
            }
        }
        if(changeInTime > 750){
            updateComparedToTime();
            toggleMovementDirection();
        }
    }

    @Override
    public void OnStateEnter() {

    }

    @Override
    public void OnStateExit() {

    }



}

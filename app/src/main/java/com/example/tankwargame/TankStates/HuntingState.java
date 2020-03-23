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
        if (movementState) {
            //Do horizontal movement
            if(isRightOfPlayer()){
                if(distance > 150){
                    mAITank.translatePosition(MovingDirection.LEFT);
                }else{
                    mAITank.changeState(mAITank.getEvadeState());
                }
            }else{
                if(distance > 150){
                    mAITank.translatePosition(MovingDirection.RIGHT);
                }else{
                    mAITank.changeState(mAITank.getEvadeState());
                }
            }
            if((mAITank.isAbleToFire() && isInXRange()) && (changeInTime / 2 > 375)){
                mAITank.fireShell();
            }
        }

        else { //movementState == false
            //Do vertical movement
            if(isAbovePlayer()){
                if(distance > 500){
                    mAITank.translatePosition(MovingDirection.DOWN);
                }else{
                    mAITank.changeState(mAITank.getEvadeState());
                }
            }
            else{
                if(distance > 500){
                    mAITank.translatePosition(MovingDirection.UP);
                }else{
                    mAITank.changeState(mAITank.getEvadeState());
                }
            }
            if((mAITank.isAbleToFire() && isInYRange()) && (changeInTime / 2 > 375)){
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

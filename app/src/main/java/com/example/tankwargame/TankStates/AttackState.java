package com.example.tankwargame.TankStates;

import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.Interfaces.IState;
import com.example.tankwargame.GameEntities.Tank;

public class AttackState extends State implements IState {

    public AttackState(EnemyTank mAITank, Tank mPlayerTank) {
        super(mAITank, mPlayerTank);
    }

    @Override
    public void Execute() {
        long changeInTime = System.currentTimeMillis() - State.comparedToTime;
        if (movementState) {
            //Do horizontal movement
            if(isRightOfPlayer()){
                mAITank.moveLeft();
                if((mAITank.isAbleToFire() && isInXRange()) && (changeInTime / 2 > 375)){
                    mAITank.fireShell();
                }
            }else{
                mAITank.moveRight();
                if((mAITank.isAbleToFire() && isInXRange()) && (changeInTime / 2 > 375)){
                    mAITank.fireShell();
                }
            }
        }

        else { //movementState == false
            //Do vertical movement
            if(isAbovePlayer()){
                mAITank.moveDown();
                if((mAITank.isAbleToFire() && isInYRange()) && (changeInTime / 2 > 375)){
                    mAITank.fireShell();
                }
            }
            else{
                mAITank.moveUp();
                if((mAITank.isAbleToFire() && isInYRange()) && (changeInTime / 2 > 375)){
                    mAITank.fireShell();
                }
            }
        }

        if(changeInTime > 750){
            updateComparedToTime();
            toggleMovementDirection();
        }
    }

    @Override
    public void OnStateEnter() {
        //Define enter behaviour here
    }

    @Override
    public void OnStateExit() {
        //Define exit behaviour here
    }

}

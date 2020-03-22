package com.example.tankwargame.TankStates;

import android.util.Log;

import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.GameView;
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
            }else{
                mAITank.moveRight();
            }
        } else { //movementState == false
            //Do vertical movement
            if(isAbovePlayer()){
                mAITank.moveDown();
            }
            else{
                mAITank.moveUp();
            }
        }
        if(changeInTime > 750){
            updateComparedToTime();
            toggleMovementState();
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

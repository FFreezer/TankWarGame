package com.example.tankwargame.TankStates;

import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.GameEntities.Tank;
import com.example.tankwargame.Interfaces.IState;

public class IdleState extends State implements IState {

    public IdleState(EnemyTank mAITank, Tank mPlayerTank) {
        super(mAITank, mPlayerTank);
    }

    @Override
    public void Execute() {

    }

    @Override
    public void OnStateEnter() {

    }

    @Override
    public void OnStateExit() {

    }



}

package com.example.tankwargame.TankStates;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.Interfaces.IState;
import com.example.tankwargame.GameEntities.Tank;

public class AttackState extends State {

    public AttackState(EnemyTank mAITank, Tank mPlayerTank) {
        super(mAITank, mPlayerTank);
    }

    @Override
    public void Execute() {

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

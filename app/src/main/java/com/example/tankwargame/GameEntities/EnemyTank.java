package com.example.tankwargame.GameEntities;

import android.content.Context;

import com.example.tankwargame.TankStates.AttackState;
import com.example.tankwargame.Interfaces.IState;
import com.example.tankwargame.TankStates.IdleState;

public class EnemyTank extends Tank {

    private AttackState attackState = new AttackState();
    private IdleState idleState = new IdleState();
    private IState currentState = idleState;

    public EnemyTank(Context context, int bitmapResource, int x, int y, Character direction) {
        super(context, bitmapResource, x, y, direction);
    }

    void changeState(IState nextState){
        currentState.OnStateExit();
        nextState.OnStateEnter();
        currentState = nextState;
    }

}

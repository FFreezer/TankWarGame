package com.example.tankwargame;

import android.content.Context;

public class EnemyTank extends Tank {

    private AttackState attackState = new AttackState();
    private IdleState idleState = new IdleState();
    IState currentState = idleState;

    public EnemyTank(Context context, int bitmapResource, int x, int y, Character direction) {
        super(context, bitmapResource, x, y, direction);
    }

    void changeState(IState nextState){
        currentState.OnStateExit();
        nextState.OnStateEnter();
        currentState = nextState;
    }

}

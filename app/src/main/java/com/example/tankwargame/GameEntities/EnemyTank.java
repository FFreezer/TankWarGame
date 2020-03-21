package com.example.tankwargame.GameEntities;

import android.content.Context;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.TankStates.AttackState;
import com.example.tankwargame.Interfaces.IState;
import com.example.tankwargame.TankStates.IdleState;

public class EnemyTank extends Tank {

    private AttackState attackState;
    private IdleState idleState;
    private IState currentState;

    public EnemyTank(Context context, int bitmapResource, int x, int y, MovingDirection direction) {
        super(context, bitmapResource, x, y, direction);
        attackState = new AttackState();
        idleState = new IdleState();
        currentState = idleState;
    }

    void changeState(IState nextState){
        currentState.OnStateExit();
        nextState.OnStateEnter();
        currentState = nextState;
        currentState.Execute();
    }

}

package com.example.tankwargame.GameEntities;

import android.content.Context;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.R;
import com.example.tankwargame.TankStates.AttackState;
import com.example.tankwargame.Interfaces.IState;
import com.example.tankwargame.TankStates.EvadeState;
import com.example.tankwargame.TankStates.IdleState;
import com.example.tankwargame.TankStates.State;

public class EnemyTank extends Tank {

    private State attackState, idleState, evadeState;
    private IState currentState;
    private Tank targetTank;

    public EnemyTank(Context context, int bitmapResource, int x, int y, MovingDirection direction, Tank rivalTank) {
        super(context, bitmapResource, x, y, direction);
        attackState = new AttackState(this, rivalTank);
        idleState = new IdleState(this, rivalTank);
        evadeState = new EvadeState(this, rivalTank);
        currentState = attackState;
        targetTank = rivalTank;
    }

    public void changeState(IState nextState){
        currentState.OnStateExit();
        nextState.OnStateEnter();
        currentState = nextState;
    }

    public void executeStateLogic(){
        currentState.Execute();
    }

    //Override Methods
    @Override
    public void translatePosition(MovingDirection direction) {
        super.translatePosition(direction);
        translatePositionHelper(direction);
    }

    private void translatePositionHelper(MovingDirection direction) {
        switch(direction){
            case UP:
                this.setBitmapFile(mContext, R.drawable.aitankup);
                break;
            case DOWN:
                this.setBitmapFile(mContext, R.drawable.aitankdown);
                break;
            case LEFT:
                this.setBitmapFile(mContext, R.drawable.aitankleft);
                break;
            case RIGHT:
                this.setBitmapFile(mContext, R.drawable.aitankright);
                break;
        }
    }

}

package com.example.tankwargame.Interfaces;

public interface IState {

   public abstract void Execute();

   public abstract void OnStateEnter();

   public abstract void OnStateExit();

}

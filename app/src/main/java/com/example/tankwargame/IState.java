package com.example.tankwargame;

public interface IState {
   public abstract void Run();
   public abstract void OnStateEnter();
   public abstract void OnStateExit();
}

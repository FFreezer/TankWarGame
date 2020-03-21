package com.example.tankwargame.Interfaces;

public interface IState {
   public abstract void Run();
   public abstract void OnStateEnter();
   public abstract void OnStateExit();
}

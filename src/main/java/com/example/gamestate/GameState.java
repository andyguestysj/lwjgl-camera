package com.example.gamestate;

import com.example.inputHandler;
import com.example.Main;

public class GameState {

  public static enum GameStateType{
    GAME, MENU;
  }
 
  protected GameStateType state;

  public GameState(){
    
  }  

  public void initialise(){

  }

  public void update(inputHandler inputHandler){
  }  

  public void render(Main main){
  }

  public GameStateType getGameStateType(){
    return state;
  }

}

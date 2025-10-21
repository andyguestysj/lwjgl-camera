package com.example.commands;
import com.example.*;
import com.example.gamestate.*;

public class ExitMenuCommand implements Command {

  private Main main;

  public ExitMenuCommand(Main main) {
    this.main = main;  
  }

  @Override
  public void execute(){  
    if (main.currentGameState.getGameStateType() != GameState.GameStateType.GAME){
      main.currentGameState = main.game;
    }

  }

  public Command getCommand(){
    return this;
  }
}

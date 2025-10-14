package com.example.commands;

import com.example.Main;

public class MoveCubeCommand implements Command {
  
  private boolean direction;
  public int axis;
  private Main main;




  public MoveCubeCommand(Main main, int axis, boolean direction){
    this.main = main;
    this.axis = axis;
    this.direction = direction;
  }

  @Override
  public void execute() {
    if (axis==X) {
      if (direction)
        main.meshObjects.getFirst().setPosX(0.01f);
      else
        main.meshObjects.getFirst().setPosX(-0.01f);
    }
    else if (axis==Y) {
      if (direction)
        main.meshObjects.getFirst().setPosY(0.01f);
      else
        main.meshObjects.getFirst().setPosY(-0.01f);
    }
    else if (axis==Z) {
      if (direction)
        main.meshObjects.getFirst().setPosZ(0.01f);
      else
        main.meshObjects.getFirst().setPosZ(-0.01f);
      
    }
  }

  public Command getCommand() { return this;  };

}
      
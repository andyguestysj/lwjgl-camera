package com.example.commands;

import com.example.Main;

public class MoveWorldCommand implements Command {
  
  private boolean direction;
  public int axis;
  private Main main;




  public MoveWorldCommand(Main main, int axis, boolean direction){
    this.main = main;
    this.axis = axis;
    this.direction = direction;
  }

  @Override
  public void execute() {
    if (axis==X) {
      if (direction)
        main.offset.x += 0.01f;
      else
        main.offset.x -= 0.01f;
    }
    else if (axis==Y) {
      if (direction)
        main.offset.y += 0.01f;
      else
        main.offset.y -= 0.01f;
    }
    else if (axis==Z) {
      if (direction)
        main.offset.z += 0.01f;
      else
        main.offset.z -= 0.01f;
      
    }
  }

  public Command getCommand() { return this;  };
}
      
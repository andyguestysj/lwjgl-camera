package com.example.commands;

import org.joml.Vector3f;

import com.example.Object;

public class MoveCubeCommand implements Command {
  
  private boolean direction;
  public int axis;  
  private Object cube;




  public MoveCubeCommand(Object cube, int axis, boolean direction){    
    this.axis = axis;
    this.direction = direction;
    this.cube = cube;
  }

  @Override
  public void execute() {

    Vector3f translation = cube.getTranslation();
    if (axis==X) {
      if (direction)
        translation.x +=0.01f;        
      else
        translation.x -=0.01f;      
    }
    else if (axis==Y) {
      if (direction)
        translation.y +=0.01f;
      else
        translation.y -=0.01f;
    }
    else if (axis==Z) {
      if (direction)
        translation.z +=0.01f;
      else
        translation.z -=0.01f;
    }
    cube.setTranslation(translation);

  }

  public Command getCommand() { return this;  };

}
      
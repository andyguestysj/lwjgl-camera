package com.example.commands;

import com.example.World;
import org.joml.Vector3f;

public class MoveWorldCommand implements Command {
  
  private boolean direction;
  public int axis;
  private World world;




  public MoveWorldCommand(World world, int axis, boolean direction){
    this.world = world;
    this.axis = axis;
    this.direction = direction;
  }

  @Override
  public void execute() {
    Vector3f translation = world.getTranslation();
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
    world.setTranslation(translation);
  }

  public Command getCommand() { return this;  };
}
      
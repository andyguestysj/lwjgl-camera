package com.example.commands;

import com.example.World;
import org.joml.Vector3f;

public class RotateWorldCommand implements Command {
  
  private boolean clockwise;
  public int direction;
  private World world;

  public static int X = 0;
  public static int Y = 1;
  public static int Z = 2;



  public RotateWorldCommand(World world, int direction, boolean cw){
    this.world = world;
    clockwise = cw;
    this.direction = direction;
  }

  @Override
  public void execute() {
    Vector3f rotation = world.getRotation();
    if (direction==X){
      if (clockwise)         
        rotation.x += 1f;
      else 
        rotation.x -= 1f;
    }
    else if (direction==Y){
      if (clockwise)         
        rotation.y += 1f;
      else 
        rotation.y -= 1f;
    }
    world.setRotation(rotation);
  }
  public Command getCommand() { return this;  };
}
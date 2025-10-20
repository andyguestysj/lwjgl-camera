package com.example.commands;


import com.example.Main;
import com.example.Object;

import java.util.Vector;

import org.joml.Vector3f;

public class RotateCubeCommand implements Command {
  
  private boolean clockwise;
  public int direction;
  private Main main;
  private Object cube;

  public static int X = 0;
  public static int Y = 1;
  public static int Z = 2;



  public RotateCubeCommand(Main main, Object cube, int direction, boolean cw){    
    clockwise = cw;
    this.direction = direction;
    this.cube = cube;
    this.main = main;
  }

  @Override
  public void execute() {
    Vector3f rotation = cube.getRotation();
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
    cube.setRotation(rotation);
  }
  public Command getCommand() { return this;  };
}
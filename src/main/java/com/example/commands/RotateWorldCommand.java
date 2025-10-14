package com.example.commands;

import com.example.Main;

public class RotateWorldCommand implements Command {
  
  private boolean clockwise;
  public int direction;
  private Main main;

  public static int X = 0;
  public static int Y = 1;
  public static int Z = 2;



  public RotateWorldCommand(Main main, int direction, boolean cw){
    this.main = main;
    clockwise = cw;
    this.direction = direction;
  }

  @Override
  public void execute() {
    if (direction==X){
      if (clockwise) {
        main.rotation.x += 1f;
        if (main.rotation.x>360f) main.rotation.x -= 360f;      
      }
      else {
        main.rotation.x -= 1f;
        if (main.rotation.x<0f) main.rotation.x += 360f;
      }
    }
    else if (direction==Y){
      if (clockwise) {
        main.rotation.y += 1f;
        if (main.rotation.y>360f) main.rotation.y -= 360f;      
      }
      else {
        main.rotation.y -= 1f;
        if (main.rotation.y<0f) main.rotation.y += 360f;
      }
    }
  }

}
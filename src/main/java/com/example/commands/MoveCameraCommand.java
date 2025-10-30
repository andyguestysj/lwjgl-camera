package com.example.commands;

import com.example.Main;
import org.joml.Vector3f;

public class MoveCameraCommand implements Command {
  
  private boolean direction;
  public int axis;
  private Main main;

  public MoveCameraCommand(Main main, int axis, boolean direction){
    this.main = main;
    this.axis = axis;
    this.direction = direction;
  }

  @Override
  public void execute() {
    float speed = 0.05f;
    Vector3f forward = new Vector3f(
      (float) java.lang.Math.sin(java.lang.Math.toRadians(main.camera.getYaw())), 0, 
      (float) -java.lang.Math.cos(java.lang.Math.toRadians(main.camera.getYaw())));
    Vector3f right = new Vector3f(forward).cross(new Vector3f(0, 1, 0)).normalize();   

    if ((axis==Z) && (direction))
      main.camera.move(new Vector3f(forward).mul(speed));
    if ((axis==Z) && (!direction))
      main.camera.move(new Vector3f(forward).mul(-speed));
    if ((axis==X) && (direction))
      main.camera.move(new Vector3f(right).mul(speed));
    if ((axis==X) && (!direction))
      main.camera.move(new Vector3f(right).mul(-speed));
  }

  /* 
  public void execute() {
    Vector3f position = main.camera.getPosition();
    Vector3f target = main.camera.getTarget();
    if (axis==X) {
      if (direction)
      {
        position.x +=0.01f;
        target.x += 0.01f;        
      }
      else
      {
        position.x -=0.01f;      
        target.x -= 0.01f;
      }
    }
    else if (axis==Y) {
      if (direction)
      {
        position.y +=0.01f;
        target.y += 0.01f;
      }
      else
      {
        position.y -=0.01f;
        target.y -= 0.01f;
      }
    }
    else if (axis==Z) {
      if (direction)
      {
        position.z +=0.01f;
        target.z += 0.01f;
      }
      else
      {
        position.z -=0.01f;
        target.z -= 0.01f;
      }
    }
    main.camera.setPosition(position);
    main.camera.setTarget(target);
  }*/

  public Command getCommand() { return this;  };
}
      
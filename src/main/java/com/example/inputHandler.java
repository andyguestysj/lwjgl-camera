package com.example;

import static org.lwjgl.glfw.GLFW.*;

import com.example.commands.*;

public class inputHandler {
    
  private Command buttonW_ = null;
	private Command buttonS_ = null;
  private Command buttonA_ = null;
	private Command buttonD_ = null;
	private Command buttonUp_ = null;
	private Command buttonDown_ = null;
	private Command buttonLeft_ = null;
	private Command buttonRight_ = null;

	private Command[] commands = new Command[350];


	private boolean[] keyState = new boolean[350]; // Array to hold button states

  public inputHandler(Main main){
		// rotate world

		commands[GLFW_KEY_W] = setButton(new RotateWorldCommand(main, Command.X, true));
		commands[GLFW_KEY_S] = setButton(new RotateWorldCommand(main, Command.X, false));
		commands[GLFW_KEY_A] = setButton(new RotateWorldCommand(main, Command.Y, true));
		commands[GLFW_KEY_D] = setButton(new RotateWorldCommand(main, Command.Y, false));
		
		commands[GLFW_KEY_UP] = setButton(new MoveWorldCommand(main, Command.Z, true));
		commands[GLFW_KEY_DOWN] = setButton(new MoveWorldCommand(main, Command.Z, false));
		commands[GLFW_KEY_LEFT] = setButton(new MoveWorldCommand(main, Command.X, false));
		commands[GLFW_KEY_RIGHT] = setButton(new MoveWorldCommand(main, Command.X, true));

		// move cube
		commands[GLFW_KEY_I] = setButton(new MoveCubeCommand(main, Command.Z, true));
		commands[GLFW_KEY_K] = setButton(new MoveCubeCommand(main, Command.Z, false));
		commands[GLFW_KEY_J] = setButton(new MoveCubeCommand(main, Command.X, true));
		commands[GLFW_KEY_L] = setButton(new MoveCubeCommand(main, Command.X, false));						}

	public void handleInput(){

		for (int i=0; i<commands.length; i++){
			if (commands[i] != null && isPressed(i)){
				commands[i].execute();
			}
		}
	}

  // Methods to bind commands...

  public Command setButton(Command aCommand){   
      return(aCommand);
  }

	public boolean isPressed(int button){
		return keyState[button];
	}

	public void pressKey(int button){
		keyState[button] = true;
	}

	public void releaseKey(int button){
		keyState[button] = false;
	}


}

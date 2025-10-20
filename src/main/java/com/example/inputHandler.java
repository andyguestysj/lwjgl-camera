package com.example;

import static org.lwjgl.glfw.GLFW.*;

import com.example.commands.*;

public class inputHandler {
  
	private CommandQueue commandQueue = new CommandQueue();
  private Command[] commands = new Command[350];


	private boolean[] keyState = new boolean[350]; // Array to hold button states

  public inputHandler(Main main, World world){
		// rotate world

		commands[GLFW_KEY_W] = setButton(new RotateWorldCommand(world, Command.X, true));
		commands[GLFW_KEY_S] = setButton(new RotateWorldCommand(world, Command.X, false));
		commands[GLFW_KEY_A] = setButton(new RotateWorldCommand(world, Command.Y, true));
		commands[GLFW_KEY_D] = setButton(new RotateWorldCommand(world, Command.Y, false));
		
		commands[GLFW_KEY_UP] = setButton(new MoveWorldCommand(world, Command.Z, true));
		commands[GLFW_KEY_DOWN] = setButton(new MoveWorldCommand(world, Command.Z, false));
		commands[GLFW_KEY_LEFT] = setButton(new MoveWorldCommand(world, Command.X, false));
		commands[GLFW_KEY_RIGHT] = setButton(new MoveWorldCommand(world, Command.X, true));

		// move cube
		Object cube = world.getObject("Cube1");		
		commands[GLFW_KEY_I] = setButton(new MoveCubeCommand(main, cube, Command.Z, false));
		commands[GLFW_KEY_K] = setButton(new MoveCubeCommand(main, cube, Command.Z, true));
		commands[GLFW_KEY_J] = setButton(new MoveCubeCommand(main, cube, Command.X, false));
		commands[GLFW_KEY_L] = setButton(new MoveCubeCommand(main, cube, Command.X, true));						
		
		commands[GLFW_KEY_T] = setButton(new RotateCubeCommand(main, cube, Command.X, true));
		commands[GLFW_KEY_G] = setButton(new RotateCubeCommand(main, cube, Command.X, false));
		commands[GLFW_KEY_F] = setButton(new RotateCubeCommand(main, cube, Command.Y, true));
		commands[GLFW_KEY_H] = setButton(new RotateCubeCommand(main, cube, Command.Y, false));
	}

	public void processInput(){

		for (int i=0; i<commands.length; i++){
			if (commands[i] != null && isPressed(i)){
				commandQueue.addCommand(commands[i]);
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

	public void executeCommands() {
		commandQueue.executeCommands();
	}


}

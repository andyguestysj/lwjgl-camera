package com.example.gamestate;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import org.joml.Matrix4f;

import com.example.Mesh;
import com.example.inputHandler;
import com.example.World;
import com.example.Main;
import com.example.Object;

public class GameStateGame extends GameState {

  public GameStateType state;

  public GameStateGame(){
    state = GameStateType.GAME;    
  }  

  public void initialise(){
    
  }

  public void update(inputHandler inputHandler){
    // read input
    inputHandler.processInput();
    // update
    inputHandler.executeCommands();
  }  

  public void render(Main main){
    Matrix4f localMatrix;		

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		glUseProgram(main.programID);
		if (glGetProgrami(main.programID, GL_LINK_STATUS) == 0) {			
			throw new RuntimeException("Error linking Shader code: " + glGetProgramInfoLog(main.programID, 1024));
		}
		main.setUniform("projectionMatrix", main.projectionMatrix);

		for (Object anObject : main.world.objects){	
			
			main.worldMatrix = main.world.getWorldMatrix();	
			localMatrix = anObject.getTransforms();

			main.worldMatrix.mul(localMatrix);
			main.setUniform("worldMatrix", main.worldMatrix);			
			Mesh aMesh = anObject.getMesh();
			glBindVertexArray(aMesh.getMeshID());
			glDrawElements(GL_TRIANGLES, anObject.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);	
		}

		glBindVertexArray(0);
		glUseProgram(0);
  }

}

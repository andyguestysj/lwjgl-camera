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

public class GameStateMenu extends GameState {

  public GameStateType state;

  public GameStateMenu(){
    state = GameStateType.MENU;    
  }  

  public void initialise(){
    
  }

  public void update(inputHandler inputHandler){

  }  

  public void render(Main main){
    Matrix4f localMatrix;		

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		glUseProgram(main.programID);
		if (glGetProgrami(main.programID, GL_LINK_STATUS) == 0) {			
			throw new RuntimeException("Error linking Shader code: " + glGetProgramInfoLog(main.programID, 1024));
		}
		main.setUniform("projectionMatrix", main.projectionMatrix);



		glBindVertexArray(0);
		glUseProgram(0);
  }

}

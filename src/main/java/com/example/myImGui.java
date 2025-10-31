package com.example;

import org.lwjgl.glfw.GLFW;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

import com.example.gamestate.GameState;

public class myImGui {
	public ImGuiIO io;	
	public ImGuiImplGlfw imGuiGlfw;
	public ImGuiImplGl3 imGuiGl3;

	public static boolean FlipRotation=false;
	public String flipLabel = "Rotate first";
	public boolean check = true;
	public char charVar = 'a';
	public float[] colour1= {0.5f,0.0f,0.0f};

  public Main main;

  public int scale=3;

  public myImGui(Main main, long window) {
    this.main = main;
    setUp(window);
  }

  public void setUp(long window) {

        ImGui.createContext();
        io = ImGui.getIO();
        io.setIniFilename(null);
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        
        

        imGuiGlfw = new ImGuiImplGlfw();
        imGuiGlfw.init(window, true);
        imGuiGl3 = new ImGuiImplGl3();
        imGuiGl3.init("#version 150");

	}

  private  void process(inputHandler inputHandler) {
	// https://pthom.github.io/imgui_manual_online/manual/imgui_manual.html
	// https://pixtur.github.io/mkdocs-for-imgui/site/api-imgui/ImGui--Dear-ImGui-end-user/
  	
    
    ImGui.begin("Debug Window");
      ImGui.setWindowFontScale(scale);

      ImGui.separatorText("Instructions");
      ImGui.text("Move Camera [WASDZX]");
      ImGui.text("Move Cube [IJKL] : Rotate Cube [TFGH]");

      ImGui.separatorText("Data");
      ImGui.text("Mouse Position");
      ImGui.sameLine(scale*150); ImGui.text("x=" + inputHandler.getMouseX());
      ImGui.sameLine(scale*250); ImGui.text("y=" + inputHandler.getMouseY());

      ImGui.text("Camera Position");
      ImGui.sameLine(scale*150); ImGui.text("x=" + main.camera.getPosition().x);
      ImGui.sameLine(scale*250); ImGui.text("y=" + main.camera.getPosition().y);
      ImGui.sameLine(scale*350); ImGui.text("z=" + main.camera.getPosition().z);

      ImGui.separatorText("Actions");
      ImGui.text("Change large cube transforms");
      if (ImGui.button(flipLabel)) {
      FlipRotation = !FlipRotation;
      if (flipLabel.equals("Rotate first"))
        flipLabel = "Translate first";
      else
        flipLabel = "Rotate first";	
      }
      
          
      ImGui.text("Game State");
      if (ImGui.button("Game")) {
        if (main.currentGameState.getGameStateType() != GameState.GameStateType.GAME){
          System.out.println("Switching to GAME state");
          main.currentGameState = main.game;
        }
      }
      ImGui.sameLine();
      if (ImGui.button("Menu")) {    
        if (main.currentGameState.getGameStateType() != GameState.GameStateType.MENU){
          System.out.println("Switching to MENU state");
          main.currentGameState = main.menu;
        }
      }
      
    ImGui.end();
}

  public void update(inputHandler inputHandler) {
    imGuiGl3.newFrame();
    imGuiGlfw.newFrame();						
    ImGui.newFrame();			

    process(inputHandler);
    
    ImGui.render();
    imGuiGl3.renderDrawData(ImGui.getDrawData());
    
    if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
      final long backupWindowPtr = org.lwjgl.glfw.GLFW.glfwGetCurrentContext();
      ImGui.updatePlatformWindows();
      ImGui.renderPlatformWindowsDefault();
      GLFW.glfwMakeContextCurrent(backupWindowPtr);
    }
  
}

  public void cleanup() {
    ImGui.logFinish();
    ImGui.destroyContext();
  }

  public static boolean getFlipRotation() {
    return FlipRotation;
  }

  public void HelpMarker(String desc) {
    ImGui.textDisabled("(?)");
    if (ImGui.isItemHovered()) {
      ImGui.beginTooltip();
      ImGui.pushTextWrapPos(ImGui.getFontSize() * 35);
      ImGui.textUnformatted(desc);
      ImGui.popTextWrapPos();
      ImGui.endTooltip();
    }
  }

  public void writeLog(String text){
    ImGui.logText(text);
  }
}

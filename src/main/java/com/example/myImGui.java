package com.example;

import org.lwjgl.glfw.GLFW;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiTabBarFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImString;
import imgui.type.ImInt;

import com.example.Main;
import com.example.gamestate.GameState;

public class myImGui {
	public ImGuiIO io;	
	public ImGuiImplGlfw imGuiGlfw;
	public ImGuiImplGl3 imGuiGl3;

	public static boolean FlipRotation=false;
	public String flipLabel = "Rotate first";
	public boolean check = true;
	public char charVar = 'a';
	private static final ImString STR = new ImString();
	public float[] colour1= {0.5f,0.0f,0.0f};

  public Main main;

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
  	
    ImGui.separatorText("Instructions");
    ImGui.text("Move World [Cursor Keys]");
    ImGui.text("Rotate World [WASD]");
    ImGui.text("Move Cube [IJKL]");
    ImGui.text("Rotate Cube [TFGH]");

    ImGui.text("Mouse Position");
    ImGui.sameLine(150); ImGui.text("x=" + inputHandler.getMouseX());
    ImGui.sameLine(300); ImGui.text("y=" + inputHandler.getMouseY());


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

    ImGui.separatorText("GUI Demos");    		
    if (ImGui.collapsingHeader("Chuckles")){
      ImGui.bulletText("1. Ahahahaha!");
      ImGui.bulletText("2. Hehehehe!");
    }              
    ImGui.checkbox("checkbox", check);              
    
    ImGui.inputText("Key for ..", STR);     
    ImGui.sameLine();
    HelpMarker("USER:\n" +
      "Hold SHIFT or use mouse to select text.\n" +
      "CTRL+Left/Right to word jump.\n" +
      "CTRL+A or Double-Click to select all.\n" +
      "CTRL+X,CTRL+C,CTRL+V for clipboard.\n" +
      "CTRL+Z to undo, CTRL+Y/CTRL+SHIFT+Z to redo.\n" +
      "ESCAPE to revert.\n\n" +
      "PROGRAMMER:\n" +
      "You can use the ImGuiInputTextFlags_CallbackResize facility if you need to wire InputText() " +
      "to a dynamic string type. See misc/cpp/imgui_stdlib.h for an example (this is not demonstrated " +
      "in imgui_demo.cpp).");

    ImGui.colorEdit3("Colour 1", colour1);

    ImGui.logButtons();
    ImGui.logText("message to log");
    //ImGui.logFinish();
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

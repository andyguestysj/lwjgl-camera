package com.example.commands;
import java.util.ArrayList;

public class CommandQueue {

  ArrayList<Command> list = new ArrayList<Command>();

  public CommandQueue() {

  }

  public void addCommand(Command command) {
    list.add(command);
  } 

  public void executeCommands() {
    for (Command command : list) {
      command.execute();
    }
    list.clear(); // Clear the list after executing all commands
  }

}

package com.company.Commands;

import java.util.*;

public class CommandGroup {

    private final Map<String,Command> commands = new HashMap<>();
    private final List<String> commandOrder = new ArrayList<>();

    public void addCommand(String command,Command method){
        commands.put(command,method);
        commandOrder.add(command);
    }
    public void printCommands(){
        for(int i = 0; i < commandOrder.size(); i++){
            String command = commandOrder.get(i);
            System.out.println(i+1 + ". " + command.substring(0,1).toUpperCase() + command.substring(1));
        }
    }
    public void printCommandsInline(){
        StringBuilder commandString = new StringBuilder();
        commandString.append("| ");
        for(int i = 0; i < commandOrder.size(); i++){
            String command = commandOrder.get(i);
            commandString.append(i+1);
            commandString.append(". ");
            commandString.append(command.substring(0,1).toUpperCase());
            commandString.append(command.substring(1));
            commandString.append(" | ");
        }
        String endString = commandString.toString().trim();
        for(int x = 0; x < endString.length(); x++){System.out.print("_");}
        System.out.print("\n");
        System.out.println(endString);
    }
    public Command getCommand(String s){
        return commands.get(s);
    }
    public String getCommandString(int i){
        return commandOrder.get(i);
    }
    public Set<String> getCommands(){
        return commands.keySet();
    }
    public void printAllCommands(){
        System.out.println("Usable commands:");
        for (String c:commands.keySet()) {
            System.out.println("\t" + c);
        }
        System.out.println("______________________________");
    }
}

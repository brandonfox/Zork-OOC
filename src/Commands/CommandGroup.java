package Commands;

import java.util.*;

public class CommandGroup {

    private final Map<String,Command> menuCommands = new HashMap<>();
    private final List<String> commandOrder = new ArrayList<>();

    public void addCommand(String command,Command method){
        menuCommands.put(command,method);
        commandOrder.add(command);
    }
    public void printCommands(){
        for(int i = 0; i < commandOrder.size(); i++){
            String command = commandOrder.get(i);
            System.out.println(i+1 + ". " + command.substring(0,1).toUpperCase() + command.substring(1));
        }
    }
    public void printCommandsInline(){
        System.out.print("| ");
        for(int i = 0; i < commandOrder.size(); i++){
            String command = commandOrder.get(i);
            System.out.print(i+1 + ". " + command.substring(0,1).toUpperCase() + command.substring(1) + " | ");
        }
        System.out.print("\n");
    }
    public Command getCommand(String s){
        return menuCommands.get(s);
    }
    public String getCommandString(int i){
        return commandOrder.get(i);
    }
    public Set<String> getCommands(){
        return menuCommands.keySet();
    }
}

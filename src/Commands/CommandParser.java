package Commands;

import java.io.IOException;
import java.util.Set;

public abstract class CommandParser {

    public static CommandPair parseCommand(String command, CommandGroup commandGroup) throws IOException {
        Set<String> validCommands = commandGroup.getCommands();
        String normalisedCommand = command.toLowerCase();
        //Check integer implementation
        for(int i = 1; i <= validCommands.size(); i++){
            String index = Integer.toString(i);
            if(normalisedCommand.contains(index)) {
                String commandName = commandGroup.getCommandString(i-1);
                String param = getParam(normalisedCommand,normalisedCommand.indexOf(index)+1);
                return new CommandPair(commandName,param);
            }
        }
        //Check string implementation
        for (String s: validCommands) {
            if(normalisedCommand.contains(s)) {
                String param = getParam(normalisedCommand,normalisedCommand.indexOf(s) + s.length());
                return new CommandPair(s,param);
            }
        }
        throw new IOException("Command " + command + " not recognised");
    }

    private static String getParam(String string, int paramIndex){
//        System.out.println("Param for string: " + string + " starts at " + paramIndex); // For debug purposes
        if(paramIndex >= string.length()-1){
            return "";
        }
        return string.substring(paramIndex);
    }
}

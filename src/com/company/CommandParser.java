package com.company;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public abstract class CommandParser {

    public static String parseCommand(String fullString){
        //TODO implement parser
        return "";
    }
    public static String parseMenuCommand(String command, List<String> orderedCommands) throws IOException {
        Set<String> validCommands = GameLauncher.getValidCommands();
        String normalisedCommand = command.toLowerCase();
        //Check integer implementation
        for(int i = 1; i <= validCommands.size(); i++){
            String index = Integer.toString(i);
            if(normalisedCommand.contains(index))
                return orderedCommands.get(i-1);
        }
        //Check string implementation
        for (String s: validCommands) {
            if(normalisedCommand.contains(s))
                return s;
        }
        throw new IOException("Command " + command + " not recognised");
    }
}

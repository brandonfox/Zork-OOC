package Commands;

public class CommandPair {

    private String command;
    private String param;

    public CommandPair(String command, String param){
        this.command = command;
        this.param = param;
    }

    public String getCommand(){return command;}
    public String getParam(){return param;}

}

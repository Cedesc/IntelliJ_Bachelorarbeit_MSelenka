package supportClasses;

public class CommandListColumn {
    // support class for visualization of the used commands in a table view

    private String command;

    // constructor
    public CommandListColumn(String command){
        this.setCommand(command);
    }

    // get command
    public String getCommand(){
        return this.command;
    }

    // set command
    public void setCommand(String command) {
        this.command = command;
    }
}

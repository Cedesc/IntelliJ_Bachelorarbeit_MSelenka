package commands;

public abstract class Command {

    // CommandSring to visualize the current command in the visualization
    protected String commandString;

    // function will be executed, when modell iterates over all commands in the visualization and next step should be executed
    public abstract void exeCommand() throws InterruptedException;

    // function will be executed, when modell iterates over all commands in the visualization and previous step should be inverted
    public abstract void backCommand() throws InterruptedException;

    // returns the description String of the command
    public String getCommandString(){
        return this.commandString;
    }

    // sets the CommandString of an command
    public void setCommandString(String commandString){
        this.commandString = commandString;
    }

}

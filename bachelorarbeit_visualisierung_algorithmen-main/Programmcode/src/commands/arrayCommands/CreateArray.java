package commands.arrayCommands;

import datastructures.InfoArray;

public class CreateArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private final InfoArray createdArray;
    private final int length;

    // constructor
    public CreateArray(InfoArray infoArray, int length){
        this.createdArray = infoArray;
        this.length = length;
        setCommandString("Create array");
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.createdArray.createArray(length);
    }

    // invert command during visualization
    public void backCommand() throws InterruptedException {
        this.createdArray.deleteArray();
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.createdArray;
    }
}

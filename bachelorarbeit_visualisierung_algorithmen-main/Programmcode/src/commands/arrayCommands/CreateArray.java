package commands.arrayCommands;

import datastructures.InfoArray;
import supportClasses.types;

public class CreateArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray createdArray;
    private types type;
    private int length;

    // constructor
    public CreateArray(InfoArray infoArray, types type, int length){
        this.createdArray = infoArray;
        this.type = type;
        this.length = length;
        setCommandString("Create Array");
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

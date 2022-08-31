package commands.arrayCommands;

import datastructures.InfoArray;
import supportClasses.types;

public class DeleteArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int length;
    private Object[] values;
    private types type;

    // constructor
    public DeleteArray(InfoArray infoArray) throws InterruptedException {
        this.length = infoArray.getSize();
        infoArray.deleteArray();
        this.infoArray = infoArray;
        this.type = infoArray.getType();
        setCommandString("Delete Array");
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.values = infoArray.getValueList();
        this.infoArray.deleteArray();
    }

    // invert command during visualization
    public void backCommand() throws InterruptedException {
        infoArray.createArrayWithValues(this.type, this.length, this.values);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }
}

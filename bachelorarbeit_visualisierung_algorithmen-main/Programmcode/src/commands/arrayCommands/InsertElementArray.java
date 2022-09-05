package commands.arrayCommands;

import datastructures.InfoArray;

public class InsertElementArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int index;
    private Object value;

    // constructor
    public InsertElementArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.value = value;
        setCommandString("Insert array element:  index = "+index+"  value = "+value);
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.infoArray.insertElement(index, value);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.infoArray.deleteElementByIndex(index);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }

}

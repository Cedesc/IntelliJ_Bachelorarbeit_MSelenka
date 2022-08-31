package commands.arrayCommands;

import datastructures.InfoArray;

public class DeleteElementByValueArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int index;
    private Object value;

    // constructor
    public DeleteElementByValueArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.value = value;
        setCommandString("Delete Arrayelement by Value: index = "+index+" value = "+ value);
    }

    // executes command during visualization
    public void exeCommand() throws InterruptedException {
        this.infoArray.deleteElementByIndex(index);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.infoArray.insertElement(index, value);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }
}

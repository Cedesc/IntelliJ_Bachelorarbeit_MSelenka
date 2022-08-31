package commands.arrayCommands;

import datastructures.InfoArray;

public class DeleteElementByIndexArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int index;
    private Object value;

    // constructor
    public DeleteElementByIndexArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.value = value;
        setCommandString("Delete Arrayelement by Index: index = "+index+" value = "+ value);
    }

    // execute command during viusalization
    public void exeCommand() throws InterruptedException {
        this.infoArray.deleteElementByIndex(index);
    }

    // invert command during visualization
    public void backCommand() throws InterruptedException {
        this.infoArray.insertElement(index, value);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }
}

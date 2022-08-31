package commands.arrayCommands;

import datastructures.InfoArray;

public class SetElementArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int index;
    private Object newValue;
    private Object oldValue;

    // constructor
    public SetElementArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.newValue = value;
        this.oldValue = infoArray.getElementByIndex(index);
        setCommandString("Set Arrayelement by Index: index = "+index+" old value = "+ oldValue + " new value = " + newValue);
    }

    // execute command during visualzation
    public void exeCommand() throws InterruptedException {
        this.infoArray.setElement(index, newValue);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.infoArray.setElement(index, oldValue);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }

}

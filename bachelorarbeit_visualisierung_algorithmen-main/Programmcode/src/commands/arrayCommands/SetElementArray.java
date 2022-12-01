package commands.arrayCommands;

import datastructures.InfoArray;

public class SetElementArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private final InfoArray infoArray;
    private final int index;
    private final Object newValue;
    private final Object oldValue;

    // constructor
    public SetElementArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.newValue = value;
        this.oldValue = infoArray.getElementByIndex(index);
        setCommandString("Set array element by index:  index = "+index+"  old value = "+oldValue+"  new value = "+newValue);
    }

    // execute command during visualization
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

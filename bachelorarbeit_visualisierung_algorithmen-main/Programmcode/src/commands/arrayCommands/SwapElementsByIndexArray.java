package commands.arrayCommands;

import datastructures.InfoArray;

public class SwapElementsByIndexArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private final InfoArray infoArray;
    private final int index1;
    private final int index2;
    private final Object value1;
    private final Object value2;

    // constructor
    public SwapElementsByIndexArray(InfoArray infoArray, int index1, int index2, Object value1, Object value2){
        this.infoArray = infoArray;
        this.index1 = index1;
        this.index2 = index2;
        this.value1 = value1;
        this.value2 = value2;
        setCommandString("Swap array elements by index:  index1 = "+index1+"  value1 = "+value1+"  index2 = "+index2+"  value2 = "+value2);
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.infoArray.swapElementsByIndex(index1, index2, value1, value2);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.infoArray.swapElementsByIndex(index2, index1, value2, value1);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }

}

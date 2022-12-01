package commands.arrayCommands;

import datastructures.InfoArray;

public class GetValueByIndexArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private final InfoArray infoArray;
    private final int index;

    // constructor
    public GetValueByIndexArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        setCommandString("Get array element value by index:  index = "+index+"  value = "+ value);
    }

    // execute command during visualization
    public void exeCommand(){
        this.infoArray.getElementByIndex(index);
    }

    // inverts command during visualization
    public void backCommand(){
        this.infoArray.getElementByIndex(index);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }

}

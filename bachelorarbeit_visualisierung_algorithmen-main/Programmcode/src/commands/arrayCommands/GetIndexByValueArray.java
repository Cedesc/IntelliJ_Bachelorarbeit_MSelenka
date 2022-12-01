package commands.arrayCommands;

import datastructures.InfoArray;

public class GetIndexByValueArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private final InfoArray infoArray;
    private final Object value;

    // constructor
    public GetIndexByValueArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.value = value;
        setCommandString("Get array element index by value:  index = "+index+"  value = "+ value);
    }

    // execute command during visualization
    public void exeCommand(){
        this.infoArray.getIndexByValue(value);
    }

    // invert command during visualization
    public void backCommand(){
        this.infoArray.getIndexByValue(value);
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }
}

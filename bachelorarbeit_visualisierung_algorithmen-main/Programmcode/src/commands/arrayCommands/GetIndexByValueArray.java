package commands.arrayCommands;

import datastructures.InfoArray;

public class GetIndexByValueArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int index;
    private Object value;

    // constructor
    public GetIndexByValueArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.value = value;
        setCommandString("Get array element index by value:  index = "+index+"  value = "+ value);
    }

    // execute command during visualization
    public void exeCommand(){
    }

    // invert command during visualization
    public void backCommand(){
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }
}

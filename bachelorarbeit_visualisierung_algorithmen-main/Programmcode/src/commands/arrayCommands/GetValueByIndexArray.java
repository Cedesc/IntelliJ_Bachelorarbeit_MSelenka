package commands.arrayCommands;

import datastructures.InfoArray;

public class GetValueByIndexArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int index;
    private Object value;

    // constructor
    public GetValueByIndexArray(InfoArray infoArray, int index, Object value){
        this.infoArray = infoArray;
        this.index = index;
        this.value = value;
        setCommandString("Get array element value by index:  index = "+index+"  value = "+ value);
    }

    // execute command during visualization
    public void exeCommand(){
    }

    // inverts command during visualization
    public void backCommand(){
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }

}

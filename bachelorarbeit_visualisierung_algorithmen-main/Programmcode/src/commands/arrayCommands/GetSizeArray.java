package commands.arrayCommands;

import datastructures.InfoArray;

public class GetSizeArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int size;

    // construcotr
    public GetSizeArray(InfoArray infoArray, int size){
        this.infoArray = infoArray;
        this.size = size;
        setCommandString("Get Array size: size = "+size);
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

package commands.arrayCommands;

import datastructures.InfoArray;

public class GetSizeArray extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private InfoArray infoArray;
    private int size;

    // constructor
    public GetSizeArray(InfoArray infoArray, int size){
        this.infoArray = infoArray;
        this.size = size;
        setCommandString("Get array size:  size = "+size);
    }

    // execute command during visualization
    public void exeCommand(){
        this.infoArray.getSize();
    }

    // inverts command during visualization
    public void backCommand(){
        this.infoArray.getSize();
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.infoArray;
    }

}

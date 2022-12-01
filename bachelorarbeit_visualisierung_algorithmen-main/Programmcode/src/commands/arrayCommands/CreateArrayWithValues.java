package commands.arrayCommands;

import datastructures.InfoArray;
import supportClasses.types;

import java.util.Arrays;

public class CreateArrayWithValues extends ArrayCommand {

    // saves all relevant data of the command by initialization
    private final InfoArray createdArray;
    private final types type;
    private final int length;
    private final Object[] values;

    // constructor
    public CreateArrayWithValues(InfoArray infoArray, types type, int length, Object[] values){
        this.createdArray = infoArray;
        this.type = type;
        this.length = length;
        this.values = values;
        setCommandString("Create array with  values :"+ Arrays.toString(this.values) +"  length: "+this.length+
                "  type : "+this.type);
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.createdArray.createArrayWithValues(type, length, values);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.createdArray.deleteArray();
    }

    // returns infoArray
    public InfoArray getArray(){
        return this.createdArray;
    }
}

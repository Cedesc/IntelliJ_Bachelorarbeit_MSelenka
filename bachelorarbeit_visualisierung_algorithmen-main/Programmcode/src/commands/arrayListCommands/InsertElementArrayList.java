package commands.arrayListCommands;

import datastructures.InfoArrayList;

public class InsertElementArrayList extends ArrayListCommand {

    // saves all relevant data of the command by initialization
    private final InfoArrayList infoArrayList;
    private final int index;
    private final Object value;

    // constructor
    public InsertElementArrayList(InfoArrayList infoArrayList, int index, Object value){
        this.infoArrayList = infoArrayList;
        this.index = index;
        this.value = value;
        setCommandString("Insert ArrayList element:  index = "+index+"  value = "+value);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoArrayList.insertElement(index, value);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        this.infoArrayList.deleteElementByIndex(index);
    }

    // returns infoArray
    @Override
    public InfoArrayList getArrayList(){
        return this.infoArrayList;
    }

}

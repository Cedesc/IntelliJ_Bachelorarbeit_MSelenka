package commands.arrayListCommands;

import datastructures.InfoArrayList;
import supportClasses.types;

public class DeleteArrayList extends ArrayListCommand {

    // saves all relevant data of the command by initialization
    private InfoArrayList infoArrayList;
    private int length;
    private Object[] values;
    private types type;

    // constructor
    public DeleteArrayList(InfoArrayList infoArrayList) throws InterruptedException {
        this.length = infoArrayList.getSize();
        infoArrayList.deleteArrayList();
        this.infoArrayList = infoArrayList;
        this.type = infoArrayList.getType();
        setCommandString("Delete array");
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.values = infoArrayList.getValueList();
        this.infoArrayList.deleteArrayList();
    }

    // invert command during visualization
    public void backCommand() throws InterruptedException {
        // TODO: 22.09.2022 isn't correct, should create the structure with the values, but at this time it doesn't
        //  restore the values
        infoArrayList.createArrayList(this.length);
    }

    @Override
    public InfoArrayList getArrayList() {
        return this.infoArrayList;
    }
}

package commands.arrayListCommands;

import datastructures.InfoArrayList;
import supportClasses.types;

public class CreateArrayList extends ArrayListCommand {

    private InfoArrayList createdArrayList;
    private types type;
    private int length;

    public CreateArrayList(InfoArrayList infoArrayList, types type, int length){
        this.createdArrayList = infoArrayList;
        this.type = type;
        this.length = length;
        setCommandString("Create ArrayList");
    }

    @Override
    public void exeCommand() throws InterruptedException {
        this.createdArrayList.createArrayList(length);
    }

    @Override
    public void backCommand() throws InterruptedException {
        this.createdArrayList.deleteArrayList();
    }

    @Override
    public InfoArrayList getArrayList() {
        return this.createdArrayList;
    }
}

package commands.arrayListCommands;

import datastructures.InfoArrayList;

public class CreateArrayList extends ArrayListCommand {

    private final InfoArrayList createdArrayList;
    private final int length;

    public CreateArrayList(InfoArrayList infoArrayList, int length){
        this.createdArrayList = infoArrayList;
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

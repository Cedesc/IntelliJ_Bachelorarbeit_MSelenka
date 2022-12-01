package commands.listCommands;

import datastructures.InfoList;

public class CreateList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList oldList;
    private final InfoList newList;

    // constructor
    public CreateList(InfoList list){
        this.newList = list;
        this.oldList = list;
        this.oldList.deleteList();
        setCommandString("Create list");
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.newList.createList();
    }

    // inverts command during visualization
    public void backCommand(){
        this.oldList.deleteList();
    }

    // returns infoList
    public InfoList getList() {
        return this.oldList;
    }
}

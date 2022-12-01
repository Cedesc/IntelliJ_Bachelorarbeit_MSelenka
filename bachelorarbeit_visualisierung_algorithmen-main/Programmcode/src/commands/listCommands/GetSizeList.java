package commands.listCommands;

import datastructures.InfoList;

public class GetSizeList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList infoList;

    // constructor
    public GetSizeList(InfoList infoList, int size){
        this.infoList = infoList;
        setCommandString("Get list size: "+ size);
    }

    // executes command during visualization
    public void exeCommand(){
    }

    // inverts command during visualization
    public void backCommand(){
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }

}

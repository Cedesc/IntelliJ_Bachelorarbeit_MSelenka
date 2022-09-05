package commands.listCommands;

import datastructures.InfoList;

public class GetSizeList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList infoList;
    private int size;

    // constructor
    public GetSizeList(InfoList infoList, int size){
        this.infoList = infoList;
        this.size = size;
        setCommandString("Get list size: "+this.size);
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

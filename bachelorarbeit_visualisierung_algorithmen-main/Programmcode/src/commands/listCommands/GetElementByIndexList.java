package commands.listCommands;

import datastructures.InfoList;

public class GetElementByIndexList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList infoList;

    // constructor
    public GetElementByIndexList(InfoList infoList, Object value, int index){
        this.infoList = infoList;
        setCommandString("Get list element by index:  value = "+value+"  index = "+index);
    }

    // executes command during visualization
    public void exeCommand(){
    }

    // inverse command during visualization
    public void backCommand(){
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }
}

package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class GetIndexByElementList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList infoList;

    // constructor
    public GetIndexByElementList(InfoList infoList, Object value, int index){
        this.infoList = infoList;
        setCommandString("Get list index by element:  value = "+value+"  index = "+index);
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

package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class GetElementByValueList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList infoList;

    // constructor
    public GetElementByValueList(InfoList infoList, Object value, int index){
        this.infoList = infoList;
        setCommandString("Delete list element by value:  value = "+value+"  index = "+index);
    }

    // execute command during visualization
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

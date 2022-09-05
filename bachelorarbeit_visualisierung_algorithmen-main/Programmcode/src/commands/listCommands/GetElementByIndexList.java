package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class GetElementByIndexList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList infoList;
    private Variable variable;
    private Object value;
    private int index;

    // constructor
    public GetElementByIndexList(InfoList infoList, Variable variable, Object value, int index){
        this.infoList = infoList;
        this.variable = variable;
        this.value = value;
        this.index = index;
        setCommandString("Get list element by index:  value = "+value+"  index = "+index);
    }

    // executes command during visualization
    public void exeCommand(){
    }

    // inverst command during visualization
    public void backCommand(){
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }
}

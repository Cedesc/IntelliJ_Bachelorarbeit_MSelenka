package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class GetIndexByElementList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList infoList;
    private Variable variable;
    private Object value;
    private int index;

    // constructor
    public GetIndexByElementList(InfoList infoList, Variable variable, Object value, int index){
        this.infoList = infoList;
        this.variable = variable;
        this.value = value;
        this.index = index;
        setCommandString("get Listindex by Element: value = " + value + " index = " + index);
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

package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class InsertElementList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList infoList;
    private Variable variable;
    private Object value;
    private int index;

    // constructor
    public InsertElementList(InfoList infoList, Variable variable, Object value, int index){
        this.infoList = infoList;
        this.variable = variable;
        this.value = value;
        this.index = index;
        setCommandString("Insert list element by index:  value = "+value+"  index = "+index);
    }

    // executes command during visualization
    public void exeCommand() throws InterruptedException {
        infoList.insertElement(variable, index);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        infoList.deleteElementByIndex(index);
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }
}

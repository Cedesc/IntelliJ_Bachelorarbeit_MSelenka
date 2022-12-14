package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class DeleteElementList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList infoList;
    private final Variable variable;
    private final int index;

    // constructor
    public DeleteElementList(InfoList infoList, Variable variable, Object value, int index){
        this.infoList = infoList;
        this.variable = variable;
        this.index = index;
        setCommandString("Delete list element by variable:  value = "+value+"  index = "+index);
    }

    // executes command during visualization
    public void exeCommand() throws InterruptedException {
        infoList.deleteElement(variable);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        infoList.insertElement(variable, index);
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }
}

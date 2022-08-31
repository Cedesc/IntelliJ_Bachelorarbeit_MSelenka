package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class DeleteElementByValueList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList infoList;
    private Variable variable;
    private Object value;
    private int index;

    // constructor
    public DeleteElementByValueList(InfoList infoList, Variable variable, Object value, int index){
        this.infoList = infoList;
        this.variable = variable;
        this.value = value;
        this.index = index;
        setCommandString("delete Listelement by Value: value = " + value + " index = " + index);
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.infoList.deleteElementByIndex(index);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.infoList.insertElement(variable, index);
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }
}

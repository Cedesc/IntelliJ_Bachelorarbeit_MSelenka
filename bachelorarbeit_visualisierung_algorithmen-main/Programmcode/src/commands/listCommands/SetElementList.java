package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

public class SetElementList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList infoList;
    private Variable variable;
    private Object oldValue;
    private Object newValue;
    private int index;

    // constructor
    public SetElementList(InfoList infoList, Variable variable, Object value, int index){
        this.infoList = infoList;
        this.variable = variable;
        this.newValue = value;
        this.index = index;
        this.oldValue = infoList.getElement(index).getValue();
        setCommandString("set Listelement by Variable: old value = " + value + " new value = " +newValue+" index = " + index);
    }

    // executes command during visualization
    public void exeCommand() throws InterruptedException {
        this.infoList.setElement(this.variable, newValue);
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.infoList.setElement(this.variable, oldValue);
    }

    // returns infoList
    public InfoList getList() {
        return this.infoList;
    }
}

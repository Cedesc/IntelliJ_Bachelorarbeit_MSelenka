package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

import java.util.ArrayList;

public class DeleteList extends ListCommand {

    // saves all relevant data of the command by initialization
    private final InfoList list;
    private final ArrayList<Variable> values;

    // constructor
    public DeleteList(InfoList list){
        this.list = list;
        this.values = list.getListValues();
        setCommandString("Delete list");
    }

    // executes command during visualization
    public void exeCommand(){
        this.list.deleteList();
    }

    // inverts command during visualization
    public void backCommand() throws InterruptedException {
        this.list.createListWithValues(values);
    }

    // returns infoList
    public InfoList getList() {
        return this.list;
    }
}

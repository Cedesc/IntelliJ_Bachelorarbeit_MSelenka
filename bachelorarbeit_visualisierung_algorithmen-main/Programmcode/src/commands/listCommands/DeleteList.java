package commands.listCommands;

import datastructures.InfoList;
import datastructures.Variable;

import java.util.ArrayList;

public class DeleteList extends ListCommand {

    // saves all relevant data of the command by initialization
    private InfoList list;
    private ArrayList<Variable> values = new ArrayList<Variable>();

    // constructor
    public DeleteList(InfoList list){
        this.list = list;
        this.values = list.getListValues();
        setCommandString("delete List");
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

package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class AddLeaf extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int parentIndex;
    private final MyNode newLeaf;

    // constructor
    public AddLeaf(InfoTree infoTree, int parentIndex, MyNode newLeaf) {
        this.infoTree = infoTree;
        this.parentIndex = parentIndex;
        this.newLeaf = newLeaf;
        setCommandString("Add leaf:  parent index = " + parentIndex + "  leaf index = " + newLeaf.getIndexAsString());
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.addLeaf(parentIndex, newLeaf.getValue());
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        infoTree.undoAddLeaf(newLeaf.getIndex());
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}

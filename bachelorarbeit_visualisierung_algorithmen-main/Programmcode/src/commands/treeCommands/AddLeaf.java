package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class AddLeaf extends TreeCommand {

    // saves all relevant data of the command by initialization
    private InfoTree infoTree;
    private MyNode parent;
    private MyNode newLeaf;

    // constructor
    public AddLeaf(InfoTree infoTree, MyNode parent, MyNode newLeaf) {
        this.infoTree = infoTree;
        this.parent = parent;
        this.newLeaf = newLeaf;
        setCommandString("Add leaf:  parent index = "+parent.index+"  leaf index = "+ newLeaf.index);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.addLeaf(parent, newLeaf);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        // TODO: 06.11.2022 Implementation
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}

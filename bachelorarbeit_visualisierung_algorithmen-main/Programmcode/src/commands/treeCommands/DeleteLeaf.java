package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class DeleteLeaf extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final MyNode leaf;

    // constructor
    public DeleteLeaf(InfoTree infoTree, MyNode leaf) {
        this.infoTree = infoTree;
        this.leaf = leaf;
        setCommandString("Delete leaf:  index = " + leaf.index);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.deleteLeaf(leaf);
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

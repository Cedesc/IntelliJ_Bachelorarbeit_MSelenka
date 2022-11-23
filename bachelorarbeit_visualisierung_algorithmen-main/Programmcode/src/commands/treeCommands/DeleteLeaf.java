package commands.treeCommands;

import datastructures.InfoTree;

public class DeleteLeaf extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int leafIndex;

    // constructor
    public DeleteLeaf(InfoTree infoTree, int leafIndex) {
        this.infoTree = infoTree;
        this.leafIndex = leafIndex;
        setCommandString("Delete leaf:  index = " + leafIndex);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.deleteLeaf(leafIndex);
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

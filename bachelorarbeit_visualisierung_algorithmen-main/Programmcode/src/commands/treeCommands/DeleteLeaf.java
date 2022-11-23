package commands.treeCommands;

import datastructures.InfoTree;

public class DeleteLeaf extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int leafIndex;
    private final int oldParentIndex;
    private final Object oldNodeValue;

    // constructor
    public DeleteLeaf(InfoTree infoTree, int leafIndex) {
        this.infoTree = infoTree;
        this.leafIndex = leafIndex;
        // calculate the current parent index and node value
        this.oldParentIndex = infoTree.getNodeByIndex(leafIndex).getParent().getIndex();
        this.oldNodeValue = infoTree.getNodeByIndex(leafIndex).getValue();
        setCommandString("Delete leaf:  index = " + leafIndex);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.deleteLeaf(leafIndex);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        infoTree.undoDeleteLeaf(leafIndex, oldParentIndex, oldNodeValue);
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}

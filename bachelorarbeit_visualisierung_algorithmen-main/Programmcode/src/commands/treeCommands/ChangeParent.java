package commands.treeCommands;

import datastructures.InfoTree;

public class ChangeParent extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int childIndex;
    private final int newParentIndex;
    private final int oldParentIndex;

    // constructor
    public ChangeParent(InfoTree infoTree, int childIndex, int newParentIndex) {
        this.infoTree = infoTree;
        this.childIndex = childIndex;
        this.newParentIndex = newParentIndex;
        // calculate the current parent index
        this.oldParentIndex = infoTree.getNodeByIndex(childIndex).getParent().getIndex();
        setCommandString("Change parent:  child index = " + childIndex + "  new parent index = " + newParentIndex);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.changeParent(childIndex, newParentIndex);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        infoTree.changeParent(childIndex, oldParentIndex);
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}

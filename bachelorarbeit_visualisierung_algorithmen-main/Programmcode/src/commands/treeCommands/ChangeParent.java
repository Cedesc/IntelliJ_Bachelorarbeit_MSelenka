package commands.treeCommands;

import datastructures.InfoTree;

public class ChangeParent extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int childIndex;
    private final int newParentIndex;

    // constructor
    public ChangeParent(InfoTree infoTree, int childIndex, int newParentIndex) {
        this.infoTree = infoTree;
        this.childIndex = childIndex;
        this.newParentIndex = newParentIndex;
        setCommandString("Change parent:  child index = " + childIndex + "  new parent index = " + newParentIndex);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.changeParent(childIndex, newParentIndex);
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

package commands.treeCommands;

import datastructures.InfoTree;

public class ChangeValue extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int nodeIndex;
    private final Object newValue;
    private final Object oldValue;

    // constructor
    public ChangeValue(InfoTree infoTree, int nodeIndex, Object newValue) {
        this.infoTree = infoTree;
        this.nodeIndex = nodeIndex;
        this.newValue = newValue;
        // calculate the current node value
        this.oldValue = infoTree.getNodeByIndex(nodeIndex).getValue();
        setCommandString("Change value:  node index = " + nodeIndex + "  new value = " + newValue);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.changeValue(nodeIndex, newValue);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        infoTree.changeValue(nodeIndex, oldValue);
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}

package commands.treeCommands;

import datastructures.InfoTree;

public class ChangeValue extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int nodeIndex;
    private final int newValue;

    // constructor
    public ChangeValue(InfoTree infoTree, int nodeIndex, int newValue) {
        this.infoTree = infoTree;
        this.nodeIndex = nodeIndex;
        this.newValue = newValue;
        setCommandString("Change value:  node index = " + nodeIndex + "  new value = " + newValue);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.changeValue(nodeIndex, newValue);
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

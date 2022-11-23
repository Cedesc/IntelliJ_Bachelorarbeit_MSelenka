package commands.treeCommands;

import datastructures.InfoTree;

public class GetIndexByValueTree extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final Object value;

    // constructor
    public GetIndexByValueTree(InfoTree infoTree, int index, Object value) {
        this.infoTree = infoTree;
        this.value = value;
        setCommandString("Get index by value:  index = " + index + "  value = " + value);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.getIndexByValue(value);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        this.infoTree.getIndexByValue(value);
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}

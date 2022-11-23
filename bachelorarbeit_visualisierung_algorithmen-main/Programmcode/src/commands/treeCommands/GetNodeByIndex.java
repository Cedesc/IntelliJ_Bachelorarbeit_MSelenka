package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class GetNodeByIndex extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final MyNode searchedNode;
    private final int index;

    // constructor
    public GetNodeByIndex(InfoTree infoTree, MyNode searchedNode, int index) {
        this.infoTree = infoTree;
        this.searchedNode = searchedNode;
        this.index = index;
        setCommandString("Get node by index:  index = " + index + "  node value = " + searchedNode.getValueAsString());
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.getNodeByIndex(index);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        this.infoTree.getNodeByIndex(index);
    }

    // returns infoTree
    @Override
    public InfoTree getTree(){
        return this.infoTree;
    }

}


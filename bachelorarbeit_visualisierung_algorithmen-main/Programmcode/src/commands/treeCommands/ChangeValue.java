package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class ChangeValue extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final MyNode node;
    private final int newValue;

    // constructor
    public ChangeValue(InfoTree infoTree, MyNode node, int newValue) {
        this.infoTree = infoTree;
        this.node = node;
        this.newValue = newValue;
        setCommandString("Change value:  node index = " + this.node.index + "  new value = " + this.newValue);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.changeValue(node, newValue);
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

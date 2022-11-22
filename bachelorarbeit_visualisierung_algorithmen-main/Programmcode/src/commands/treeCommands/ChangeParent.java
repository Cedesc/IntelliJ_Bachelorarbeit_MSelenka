package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class ChangeParent extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final MyNode childNode;
    private final MyNode newParent;

    // constructor
    public ChangeParent(InfoTree infoTree, MyNode childNode, MyNode newParent) {
        this.infoTree = infoTree;
        this.childNode = childNode;
        this.newParent = newParent;
        setCommandString("Change parent:  child index = " + childNode.getIndex() + "  new parent index = " +
                newParent.getIndex());
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoTree.changeParent(childNode, newParent);
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

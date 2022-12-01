package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;

public class CreateTree extends TreeCommand {

    private final InfoTree infoTree;
    private final MyNode root;
    private final Object rootValue;

    public CreateTree(InfoTree infoTree, MyNode root){
        this.infoTree = infoTree;
        this.root = root;
        // save the initial value
        this.rootValue = root.getValue();
        setCommandString("Create tree");
    }

    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.createTree(root, rootValue);
    }

    @Override
    public void backCommand() throws InterruptedException {
        infoTree.deleteTree();
    }

    @Override
    public InfoTree getTree() {
        return this.infoTree;
    }
}

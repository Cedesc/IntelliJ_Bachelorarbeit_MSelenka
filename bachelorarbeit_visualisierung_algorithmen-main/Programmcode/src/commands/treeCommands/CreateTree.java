package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class CreateTree extends TreeCommand {

    private final InfoTree infoTree;
    private final types type; // TODO: 23.11.2022 remove?
    private final MyNode root;

    public CreateTree(InfoTree infoTree, types type, MyNode root){
        this.infoTree = infoTree;
        this.type = type;
        this.root = root;
        setCommandString("Create tree");
    }

    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.createTree(root);
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

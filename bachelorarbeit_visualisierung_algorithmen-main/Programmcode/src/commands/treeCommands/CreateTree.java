package commands.treeCommands;

import datastructures.InfoTree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class CreateTree extends TreeCommand {

    private InfoTree createdTree;
    private types type;
    private MyNode root;

    public CreateTree(InfoTree infoTree, types type, MyNode root){
        this.createdTree = infoTree;
        this.type = type;
        this.root = root;
        setCommandString("Create tree");
    }

    @Override
    public void exeCommand() throws InterruptedException {
        this.createdTree.createTree(root);
    }

    @Override
    public void backCommand() throws InterruptedException {
        // TODO: 04.11.2022 Implementation
    }

    @Override
    public InfoTree getTree() {
        return this.createdTree;
    }
}

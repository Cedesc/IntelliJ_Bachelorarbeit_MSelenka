package commands.treeCommands;

import datastructures.InfoTree;
import datastructures.InfoVariable;

public class ChangeValueToVariableValue extends TreeCommand {

    // saves all relevant data of the command by initialization
    private final InfoTree infoTree;
    private final int nodeIndex;
    private final InfoVariable infoVariable;
    private final Object newValue;
    private final Object oldValue;

    // constructor
    public ChangeValueToVariableValue(InfoTree infoTree, int nodeIndex, InfoVariable infoVariable) {
        this.infoTree = infoTree;
        this.nodeIndex = nodeIndex;
        this.infoVariable = infoVariable;
        // calculate the current node value
        this.oldValue = infoTree.getNodeByIndex(nodeIndex).getValue();
        // calculate the variable value
        this.newValue = infoVariable.getValueWithoutVisualization();
        setCommandString("Change value:  node index = " + nodeIndex + "  new value = " + newValue);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        infoTree.changeValueToVariableValue(nodeIndex, infoVariable);
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

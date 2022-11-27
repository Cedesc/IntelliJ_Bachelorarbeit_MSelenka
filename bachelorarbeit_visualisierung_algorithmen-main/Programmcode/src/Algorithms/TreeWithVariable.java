package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Experiment;
import datastructures.Tree;
import datastructures.Variable;
import supportClasses.types;

public class TreeWithVariable extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        Experiment experiment = create_Experiment(types.NUMBER, 5);

        // create variable
        Variable variable = create_Variable(types.NUMBER);
        variable.setValue(5);


        // create tree
        Tree tree = create_Tree(types.NUMBER, 10);

        // add leafs to tree on level 1
        tree.addLeaf(0, 1);
        tree.addLeaf(0);
        tree.addLeaf(0);

        // change values
        tree.changeValue(2, 222);

        // change value using the variable
        tree.changeValueToVariableValue(2, variable);

        // get indices by values
        int a = tree.getIndexByValue(1);

    }

}

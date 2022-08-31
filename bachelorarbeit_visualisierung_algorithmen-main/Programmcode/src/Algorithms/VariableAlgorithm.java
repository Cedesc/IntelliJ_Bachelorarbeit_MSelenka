package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Variable;
import supportClasses.types;

public class VariableAlgorithm extends AbstractAlgorithm {
    @Override
    public void executeAlgorithm() throws InterruptedException {
        Variable var = create_Variable(types.NUMBER);
        Variable var2 = create_Variable(types.BOOLEAN);
        var.setValue(2);
        var2.setValue(true);
        var.setValue(120);
        var2.setValue(false);
        var.deleteVariable();

    }
}

package Algorithms.oldAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Variable;
import supportClasses.types;

public class VariableAlgorithm extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {
        Variable var1 = create_Variable(types.NUMBER);
        Variable var2 = create_Variable(types.BOOLEAN);

        var1.setValue(2);
        var2.setValue(true);
        var1.setValue(120);
        var2.setValue(false);
        var1.deleteVariable();

    }

}

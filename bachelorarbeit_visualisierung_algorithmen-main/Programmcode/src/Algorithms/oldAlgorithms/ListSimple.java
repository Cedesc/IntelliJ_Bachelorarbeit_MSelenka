package Algorithms.oldAlgorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.List;
import datastructures.Variable;
import supportClasses.types;

public class ListSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {
        List list = create_List();

        Variable variable = create_Variable(types.NUMBER);
        variable.setValue(3);

        list.insertElementAtStart(variable);
        list.insertElementAtStart(variable);

    }

}

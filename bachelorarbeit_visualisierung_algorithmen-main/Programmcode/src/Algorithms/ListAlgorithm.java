package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.List;
import datastructures.Variable;
import supportClasses.types;

public class ListAlgorithm extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {
        List list = create_List();
        List list2 = create_List();
        Variable variable = create_Variable(types.NUMBER);
        variable.setValue(3);
        Variable variable2 = create_Variable(types.NUMBER);
        variable2.setValue(5);
        Variable variable3 = create_Variable(types.NUMBER);
        variable3.setValue(10);
        list.insertElementAtStart(variable);
        list.insertElementAtEnd(variable2);
        list.insertELement(variable3, 0);

    }

}

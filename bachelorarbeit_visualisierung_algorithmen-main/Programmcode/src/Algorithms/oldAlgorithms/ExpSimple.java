package Algorithms.oldAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Experiment;
import supportClasses.types;

// OWN TEST STUFF
public class ExpSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        Experiment experiment1 = create_Experiment(types.NUMBER, 12);
        Experiment experiment2 = create_Experiment(types.NUMBER, 5);

        experiment1.insertElementAtEnd(1);
        experiment1.insertElementAtEnd(2);
        experiment1.insertElementAtEnd(3);

        experiment1.swapElements(0, 2);

        experiment1.insertElementAtEnd(4);

        experiment1.swapElements(2, 1);

        experiment1.insertElementAtEnd(5);
        experiment1.insertElementAtEnd(6);


    }

}

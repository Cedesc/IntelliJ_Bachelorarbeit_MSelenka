package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Experiment;
import supportClasses.types;

// OWN TEST STUFF
public class ExperimentSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        Experiment experiment1 = create_Experiment(types.NUMBER, 12);
        Experiment experiment2 = create_Experiment(types.NUMBER, 5);

        experiment1.insertElementAtEnd(5);
        experiment1.insertElementAtEnd(7);
        experiment1.insertElementAtEnd(12);

        experiment1.swapElements(0, 2);


    }

}

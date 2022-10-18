package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Experiment;
import supportClasses.types;

// OWN TEST STUFF
public class ExpComplex extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        Experiment experiment1 = create_Experiment(types.NUMBER, 12);
        Experiment experiment2 = create_Experiment(types.NUMBER, 5);

        experiment1.insertElementAtEnd(11);
        experiment1.insertElementAtEnd(12);

        experiment2.insertElementAtEnd(21);

        Experiment experiment3 = create_Experiment(types.NUMBER, 3);

        experiment2.insertElementAtEnd(22);

        experiment3.insertElementAtEnd(31);

        experiment2.insertElementAtEnd(23);

        experiment1.insertElementAtEnd(13);

        experiment2.swapElements(1, 0);
        experiment2.swapElements(2, 0);
        experiment2.swapElements(1, 2);

        experiment1.swapElements(0, 2);


    }

}

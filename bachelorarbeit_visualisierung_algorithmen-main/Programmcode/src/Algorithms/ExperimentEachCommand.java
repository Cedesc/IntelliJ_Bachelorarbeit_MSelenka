package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Experiment;
import supportClasses.types;

// OWN TEST STUFF
public class ExperimentEachCommand extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        Experiment experiment1 = create_Experiment(types.NUMBER, 12);
        Experiment experiment2 = create_Experiment(types.NUMBER, 5);

        experiment1.insertElementAtEnd(11);
        experiment1.insertElementAtEnd(12);

        experiment1.insertElementAtStart(10);
        experiment1.insertElementAtStart(9);

        experiment1.insertElement(1, 18);
        experiment1.insertElement(3, 19);

        experiment1.swapElements(0, 2);
        experiment1.swapElements(2, 1);

        experiment1.deleteExperiment();

        // here an exception is thrown because it can't handle the actions on the second array after deleting the first

        experiment2.insertElementAtEnd(21);

    }

}

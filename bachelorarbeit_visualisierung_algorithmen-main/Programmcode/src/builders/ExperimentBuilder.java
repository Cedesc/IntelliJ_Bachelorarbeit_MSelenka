package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Experiment;
import datastructures.InfoExperiment;
import supportClasses.types;

public class ExperimentBuilder {

    public Experiment createExperiment(AbstractAlgorithm AbstractAlgorithm, InfoExperiment infoExperiment, types typ, int length){
        Experiment experiment = new Experiment(AbstractAlgorithm, infoExperiment, typ, length);
        return experiment;
    }

    public InfoExperiment createInfoExperiment(AbstractAlgorithm abstractAlgorithm, types type, int length){
        InfoExperiment infoExperiment = new InfoExperiment(abstractAlgorithm, type, length);
        return infoExperiment;
    }

}

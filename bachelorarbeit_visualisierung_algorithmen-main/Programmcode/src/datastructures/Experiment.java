package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import commands.tableCommands.CreateExperiment;
import supportClasses.types;

public class Experiment extends AbstractDatastructure {

    private AbstractAlgorithm algorithm;
    public InfoExperiment infoExperiment;
    private types typ;
    private int length;

    public Experiment(AbstractAlgorithm AbstractAlgorithm, InfoExperiment infoExperiment, types typ, int length){
        this.algorithm = AbstractAlgorithm;
        this.infoExperiment = infoExperiment;
        this.typ = typ;
        this.length = length;
        CreateExperiment createExperiment = new CreateExperiment(this.infoExperiment, typ, length);
        this.algorithm.appendCommandOrder(createExperiment);
    }
}

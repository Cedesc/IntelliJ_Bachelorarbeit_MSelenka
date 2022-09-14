package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import supportClasses.types;

import visualization.ExperimentVisualization;

import java.util.ArrayList;

public class InfoExperiment extends AbstractDatastructure {

    private ArrayList<Object> experimentContent;
    private types type;
    private int length;
    private ExperimentVisualization experimentVisualization;
    private AbstractAlgorithm algorithm;

    public InfoExperiment(AbstractAlgorithm abstractAlgorithm, types type, int length) {
        this.experimentContent = new ArrayList<Object>();
        this.type = type;
        this.length = length;
        this.algorithm = abstractAlgorithm;
    }

    public void createExperiment(int length) throws InterruptedException {
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        this.length = length;
        this.experimentContent = new ArrayList<Object>();
        if (this.experimentVisualization != null){
            this.experimentVisualization.createExperiment(this, length);
        }
    }

    public ExperimentVisualization getExperimentVisualization() {
        return this.experimentVisualization;
    }

    public void setExperimentVisualization(ExperimentVisualization experimentVisualization) {
        this.experimentVisualization = experimentVisualization;
    }

}

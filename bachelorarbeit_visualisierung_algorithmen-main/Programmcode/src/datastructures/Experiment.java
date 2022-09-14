package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import commands.arrayCommands.SwapElementsByIndexArray;
import commands.experimentCommands.CreateExperiment;
import commands.experimentCommands.InsertElementExperiment;
import commands.experimentCommands.SwapElementsByIndexExperiment;
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

    // for inserting a new element to the array at the end of it
    public void insertElementAtEnd(Object value) throws InterruptedException {
        int size = this.infoExperiment.getSize();
        InsertElementExperiment insertElementExperiment = new InsertElementExperiment(this.infoExperiment, size, value);
        this.infoExperiment.insertElement(size, value);
        this.algorithm.appendCommandOrder(insertElementExperiment);
    }

    // for swapping 2 elements of the array by their given indices
    public void swapElements(int index1, int index2) throws InterruptedException {
        Object value1 = this.infoExperiment.getElementByIndex(index1);
        Object value2 = this.infoExperiment.getElementByIndex(index2);
        SwapElementsByIndexExperiment swapElementsByIndexExperiment = new SwapElementsByIndexExperiment(this.infoExperiment, index1, index2, value1, value2);
        this.algorithm.appendCommandOrder(swapElementsByIndexExperiment);
        this.infoExperiment.swapElementsByIndex(index1, index2, value1, value2);
    }

}

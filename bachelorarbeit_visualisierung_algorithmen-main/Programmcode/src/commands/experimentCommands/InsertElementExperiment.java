package commands.experimentCommands;

import datastructures.InfoExperiment;

public class InsertElementExperiment extends ExperimentCommand {

    // saves all relevant data of the command by initialization
    private InfoExperiment infoExperiment;
    private int index;
    private Object value;

    // constructor
    public InsertElementExperiment(InfoExperiment infoExperiment, int index, Object value){
        this.infoExperiment = infoExperiment;
        this.index = index;
        this.value = value;
        setCommandString("Insert experiment element:  index = "+index+"  value = "+value);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoExperiment.insertElement(index, value);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        this.infoExperiment.deleteElementByIndex(index);
    }

    // returns infoArray
    @Override
    public InfoExperiment getExperiment(){
        return this.infoExperiment;
    }

}

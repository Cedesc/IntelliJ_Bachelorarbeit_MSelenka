package commands.experimentCommands;

import datastructures.InfoExperiment;
import supportClasses.types;

public class DeleteExperiment extends ExperimentCommand {

    // saves all relevant data of the command by initialization
    private InfoExperiment infoExperiment;
    private int length;
    private Object[] values;
    private types type;

    // constructor
    public DeleteExperiment(InfoExperiment infoExperiment) throws InterruptedException {
        this.length = infoExperiment.getSize();
        infoExperiment.deleteExperiment();
        this.infoExperiment = infoExperiment;
        this.type = infoExperiment.getType();
        setCommandString("Delete array");
    }

    // execute command during visualization
    public void exeCommand() throws InterruptedException {
        this.values = infoExperiment.getValueList();
        this.infoExperiment.deleteExperiment();
    }

    // invert command during visualization
    public void backCommand() throws InterruptedException {
        // TODO: 22.09.2022 isn't correct, should create the structure with the values, but at this time it doesn't
        //  restore the values
        infoExperiment.createExperiment(this.length);
    }

    @Override
    public InfoExperiment getExperiment() {
        return this.infoExperiment;
    }
}

package commands.experimentCommands;

import datastructures.InfoExperiment;
import supportClasses.types;

public class CreateExperiment extends ExperimentCommand {

    private InfoExperiment createdExperiment;
    private types type;
    private int length;

    public CreateExperiment(InfoExperiment infoExperiment, types type, int length){
        this.createdExperiment = infoExperiment;
        this.type = type;
        this.length = length;
        setCommandString("Create experiment");
    }

    @Override
    public void exeCommand() throws InterruptedException {
        this.createdExperiment.createExperiment(length);
    }

    @Override
    public void backCommand() throws InterruptedException {
        this.createdExperiment.deleteExperiment();
    }

    @Override
    public InfoExperiment getExperiment() {
        return this.createdExperiment;
    }
}

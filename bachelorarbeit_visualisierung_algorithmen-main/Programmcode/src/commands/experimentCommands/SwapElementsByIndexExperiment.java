package commands.experimentCommands;

import datastructures.InfoArray;
import datastructures.InfoExperiment;

public class SwapElementsByIndexExperiment extends ExperimentCommand {

    // saves all relevant data of the command by initialization
    private InfoExperiment infoExperiment;
    private int index1;
    private int index2;
    private Object value1;
    private Object value2;

    // constructor
    public SwapElementsByIndexExperiment(InfoExperiment infoExperiment, int index1, int index2, Object value1, Object value2){
        this.infoExperiment = infoExperiment;
        this.index1 = index1;
        this.index2 = index2;
        this.value1 = value1;
        this.value2 = value2;
        setCommandString("Swap experiment elements by index:  index1 = "+index1+"  value1 = "+value1+"  index2 = "
                +index2+"  value2 = "+value2);
    }

    // execute command during visualization
    @Override
    public void exeCommand() throws InterruptedException {
        this.infoExperiment.swapElementsByIndex(index1, index2, value1, value2);
    }

    // inverts command during visualization
    @Override
    public void backCommand() throws InterruptedException {
        this.infoExperiment.swapElementsByIndex(index2, index1, value2, value1);
    }

    @Override
    public InfoExperiment getExperiment() {
        return this.infoExperiment;
    }
}

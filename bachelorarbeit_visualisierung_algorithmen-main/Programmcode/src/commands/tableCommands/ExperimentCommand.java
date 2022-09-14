package commands.tableCommands;

import commands.Command;
import datastructures.InfoExperiment;

public abstract class ExperimentCommand extends Command {

    public abstract InfoExperiment getExperiment();
}

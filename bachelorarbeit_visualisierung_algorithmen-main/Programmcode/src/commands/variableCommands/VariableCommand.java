package commands.variableCommands;

import commands.Command;
import datastructures.InfoVariable;

public abstract class VariableCommand extends Command {
    // class exists so the model can differentiates between the datastructures the commands are operating on


    // returns the InfoVariable of the command
    abstract public InfoVariable getVariable();

}

package commands.listCommands;

import commands.Command;
import datastructures.InfoList;

public abstract class ListCommand extends Command {
    // exists so the model can differentiate between the data structures the command is operating on


    // returns the infoList the command is operating on
    public abstract InfoList getList();
}

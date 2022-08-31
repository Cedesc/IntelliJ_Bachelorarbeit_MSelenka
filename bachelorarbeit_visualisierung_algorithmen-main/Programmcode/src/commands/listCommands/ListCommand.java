package commands.listCommands;

import commands.Command;
import datastructures.InfoList;

public abstract class ListCommand extends Command {
    // exists ao the model can differtiate between the datastructures the command is operating on


    // returns the infoList the command is operating on
    public abstract InfoList getList();
}

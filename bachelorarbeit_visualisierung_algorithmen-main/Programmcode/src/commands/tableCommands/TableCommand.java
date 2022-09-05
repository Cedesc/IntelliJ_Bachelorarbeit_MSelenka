package commands.tableCommands;

import commands.Command;
import datastructures.InfoTable;

public abstract class TableCommand extends Command {

    public abstract InfoTable getTable();
}

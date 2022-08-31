package commands.arrayCommands;

import commands.Command;
import datastructures.InfoArray;

public abstract class ArrayCommand extends Command {
    // class exists so the model can differentiate between the datastructures the command is operating on

    // returns the infoArray of the command it is operating on
    public abstract InfoArray getArray();
}

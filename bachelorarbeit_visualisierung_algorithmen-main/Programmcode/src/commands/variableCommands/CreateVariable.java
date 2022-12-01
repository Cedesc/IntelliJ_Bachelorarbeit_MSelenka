package commands.variableCommands;

import datastructures.InfoVariable;
import supportClasses.types;

public class CreateVariable extends VariableCommand {

    // saves all relevant data of the command by initialization
    private final InfoVariable variable;
    private final Object initialValue;

    // constructor
    public CreateVariable(InfoVariable variable, types typ, Object value){
        this.variable = variable;
        this.initialValue = value;
        setCommandString("Create variable:  value = "+value+"  type = "+ typ);
    }

    public void exeCommand() throws InterruptedException {
        this.variable.setValue(this.initialValue);
    }

    public void backCommand() throws InterruptedException {
        variable.deleteVariable();
    }


    @Override
    public InfoVariable getVariable() {
        return variable;
    }
}

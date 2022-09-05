package commands.variableCommands;

import datastructures.InfoVariable;

public class SetValueVariable extends VariableCommand {

    // saves all relevant data of the command by initialization
    private Object newValue;
    private Object oldValue;
    private InfoVariable variable;

    //constructor
    public SetValueVariable(InfoVariable var, Object newValue, Object oldValue){
        this.newValue = newValue;
        this.oldValue = var.getValue();
        this.variable = var;
        setCommandString("Set Variable:  old value = "+oldValue.toString()+" new value = "+newValue.toString());
    }

    public void exeCommand() throws InterruptedException {
        this.variable.setValue(newValue);
    }

    public void backCommand() throws InterruptedException {
        this.variable.setValue(oldValue);
    }

    @Override
    public InfoVariable getVariable() {
        return variable;
    }
}

package commands.variableCommands;

import datastructures.InfoVariable;

public class DeleteVariable extends VariableCommand {

    // saves all relevant data of the command by initialization
    public InfoVariable variable;
    public Object oldValue;

    // constructor
    public DeleteVariable(InfoVariable var){
        if (var.getValue() == null){
            System.out.println("Variable already deleted");
        }
        this.variable = var;
        this.oldValue = var.getValue();
        setCommandString("Variable deleted");
    }

    public void exeCommand() throws InterruptedException {
        this.variable.deleteVariable();
    }


    public void backCommand() throws InterruptedException {
        this.variable.setValue(oldValue);
    }

    @Override
    public InfoVariable getVariable() {
        return variable;
    }
}

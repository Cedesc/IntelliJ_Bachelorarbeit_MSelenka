package commands.variableCommands;

import datastructures.InfoVariable;

public class GetValueVariable extends VariableCommand {

    // saves all relevant data of the command by initialization
    private InfoVariable variable;
    private Object value;

    // constructor
    public GetValueVariable(InfoVariable var){
        if (var.getValue() == null){
            System.out.println("Variable : " + var + " deleted");
        }
        else {
            this.variable = var;
            this.value = var.getValue();
            setCommandString("Get Variable value : " + var.getValue());
        }
    }

    public void exeCommand() {
    }

    public void backCommand(){
    }

    @Override
    public InfoVariable getVariable() {
        return variable;
    }
}

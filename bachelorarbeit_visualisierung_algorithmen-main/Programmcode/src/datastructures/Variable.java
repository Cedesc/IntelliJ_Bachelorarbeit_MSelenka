package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.variableCommands.CreateVariable;
import commands.variableCommands.DeleteVariable;
import commands.variableCommands.GetValueVariable;
import commands.variableCommands.SetValueVariable;
import supportClasses.types;

public class Variable extends AbstractDatastructure {
    // class for the user to access
    // creates commands and organizes the access to the actual variable

    // saves all relevant information
    private InfoVariable infoVariable;
    private AbstractAlgorithm algorithm;
    private types variableType;

    // constructor
    public Variable(InfoVariable infoVariable, AbstractAlgorithm algorithm, types typ) {
        super();
        this.infoVariable = infoVariable;
        this.algorithm = algorithm;
        this.variableType = typ;
        CreateVariable newVariable = new CreateVariable(this.infoVariable, typ, this.infoVariable.getValue());
        this.algorithm.appendCommandOrder(newVariable);
    }

    // for deleting the value of the variable
    // creates "deleteVariable" command
    public void deleteVariable() throws InterruptedException {
        DeleteVariable DeleteVariable = new DeleteVariable(this.infoVariable);
        this.algorithm.appendCommandOrder(DeleteVariable);
        this.infoVariable.deleteVariable();
    }

    // for setting a new value of the variable
    // creates "setValueVariable" command
    public void setValue(Object value) throws InterruptedException {
        SetValueVariable SetValueVariable = new SetValueVariable(this.infoVariable, value, this.infoVariable.getValue());
        this.algorithm.appendCommandOrder(SetValueVariable);
        this.infoVariable.setValue(value);
    }

    // returns the value of the variable
    // creates "getValueVariable" command
    public Object getValue(){
        GetValueVariable GetValueVariable = new GetValueVariable(this.infoVariable);
        this.algorithm.appendCommandOrder(GetValueVariable);
        return this.infoVariable.getValue();
    }

    // returns the variable type (from enum "types" in "supportClasses"
    public types getVariableType(){
        return this.variableType;
    }

    public InfoVariable getInfoVariable() {
        return infoVariable;
    }
}

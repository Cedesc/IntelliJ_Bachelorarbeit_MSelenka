package datastructures;

import visualization.VariableVisualization;
import abstractAlgorithm.AbstractAlgorithm;
import supportClasses.types;

public class InfoVariable extends AbstractDatastructure {
    // actual variable with the value and type
    // if an error occur by the user: all functions are able to set the error string of the abstract algorithm class if the string is not set yet
    // all functions will cast the corresponding variable visualization function if the variable visualization is set

    // saves all relevant information
    private types variableTyp;
    private Object value;
    private AbstractAlgorithm algorithm;
    private VariableVisualization variableVisualization;

    // constructor
    public InfoVariable(AbstractAlgorithm algorithm, types typ) {
        super();
        this.algorithm = algorithm;
        this.variableTyp = typ;
        switch (typ){
            case NUMBER:
                this.value = (double) 0;
            case BOOLEAN:
                this.value = false;
            case STRING:
                this.value = "";
            default:
                break;
        }
    }

    // deletes the value of the variable but not the instance
    public void deleteVariable() throws InterruptedException {
        this.value = null;
        if (variableVisualization != null){
            variableVisualization.deleteVariable(this);
        }
    }

    // sets a new value
    public void setValue(Object value) throws InterruptedException {
        if (variableVisualization != null){
            variableVisualization.setVariable(this, value);
        }
        if (sameType(value)){
            this.value = value;
        }
        else {
            if (this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'setValue' :\nValue not same Type as Variable");
            }

        }
    }

    // returns the variable value
    public Object getValue() {

        // visualize the command
        if (this.variableVisualization != null) {
            this.variableVisualization.getValue(this, this.value);
        }

        return value;
    }

    public Object getValueWithoutVisualization() {
        return value;
    }

    // returns the variable typ
    public types getVariableTyp(){
        return this.variableTyp;
    }

    // tests if the given object is the same as the variable
    public boolean sameType(Object value){
        String simpleTypeName  = value.getClass().getSimpleName();
        if (variableTyp.equals(types.NUMBER)){
            return simpleTypeName.equals("Integer") || simpleTypeName.equals("Double") || simpleTypeName.equals("Float");
        }
        if (variableTyp.equals(types.STRING)){
            return simpleTypeName.equals("String") || simpleTypeName.equals("Character");
        }
        if (variableTyp.equals(types.BOOLEAN)){
            return simpleTypeName.equals("Boolean");
        }
        return false;
    }

    // sets the variable visualization by the parent model
    public void setVariableVisualization(VariableVisualization variableVisualization){
        this.variableVisualization = variableVisualization;
    }

    // returns the variable visualization
    public VariableVisualization getVariableVisualization(){
        return this.variableVisualization;
    }

}

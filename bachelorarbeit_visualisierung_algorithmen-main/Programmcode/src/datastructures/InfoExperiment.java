package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import supportClasses.types;

import visualization.ExperimentVisualization;

import java.util.ArrayList;

public class InfoExperiment extends AbstractDatastructure {

    private ArrayList<Object> experimentContent;
    private types type;
    private int length;
    private ExperimentVisualization experimentVisualization;
    private AbstractAlgorithm algorithm;

    public InfoExperiment(AbstractAlgorithm abstractAlgorithm, types type, int length) {
        this.experimentContent = new ArrayList<Object>();
        this.type = type;
        this.length = length;
        this.algorithm = abstractAlgorithm;
    }

    public void createExperiment(int length) throws InterruptedException {
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        this.length = length;
        this.experimentContent = new ArrayList<Object>();
        if (this.experimentVisualization != null){
            this.experimentVisualization.createExperiment(this, length);
        }
    }

    // returns the value of the array by a given index
    public Object getElementByIndex(int index) {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'getElementByIndex' of an experiment :\nIndex out of bound.");
        }
        return this.experimentContent.get(index);
    }

    // inserts a new value with a given index to the array
    public void insertElement(int index, Object value) throws InterruptedException {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'insertElement' of an experiment :\nIndex out of bound.");
        }
        if (sameType(value)){
            this.experimentContent.add(index, value);
            if (this.experimentVisualization != null){
                this.experimentVisualization.insertElement(this, index, value);
            }
        }
        else {
            if (this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'insertElement' of an array :\nValue not same Type as array elements.");
            }

        }
    }

    // deletes the element of the array by a given index
    public void deleteElementByIndex(int index) throws InterruptedException {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElementByIndex' of an experiment :\nIndex out of bound.");
        }
        this.experimentContent.remove(index);
        if (this.experimentVisualization != null){
            this.experimentVisualization.deleteElement(this, index);
        }
    }

    public void swapElementsByIndex(int index1, int index2, Object value1, Object value2) throws InterruptedException {
        if ((index1 < 0 || index1 >= this.length)  && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'swapElementsByIndex' of an experiment :\nIndex1 out of bound.");
        }
        if ((index2 < 0 || index2 >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'swapElementsByIndex' of an experiment :\nIndex2 out of bound.");
        }
        this.experimentContent.set(index1, value2);
        this.experimentContent.set(index2, value1);
        if (this.experimentVisualization != null){
            this.experimentVisualization.swapElements(this, index1, index2);
        }
    }

    // tests if the given value is the same as the array type
    // returns true if they are the same, if not returns false
    public boolean sameType(Object value){
        String simpleTypeName  = value.getClass().getSimpleName();
        if (type.equals(types.NUMBER)){
            return simpleTypeName.equals("Integer") || simpleTypeName.equals("Double") || simpleTypeName.equals("Float");
        }
        else if (type.equals(types.STRING)){
            return simpleTypeName.equals("String") || simpleTypeName.equals("Character");
        }
        else if (type.equals(types.BOOLEAN)){
            return simpleTypeName.equals("Boolean");
        }
        else {
            System.out.println("Warning! Unknown Type!");
            return false;
        }
    }

    // returns the length of the array
    public int getSize(){
        return this.experimentContent.size();
    }

    public ExperimentVisualization getExperimentVisualization() {
        return this.experimentVisualization;
    }

    public void setExperimentVisualization(ExperimentVisualization experimentVisualization) {
        this.experimentVisualization = experimentVisualization;
    }

}

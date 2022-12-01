package datastructures;

import visualization.ArrayVisualization;
import abstractAlgorithm.AbstractAlgorithm;
import supportClasses.types;
import java.util.ArrayList;
import java.util.Arrays;

public class InfoArray extends AbstractDataStructure {
    // actual array with alle elements
    // if an error occur by the user: all functions are able to set the error string of the abstract algorithm class if the string is not set yet
    // all functions will cast the corresponding array visualization function if the array visualization is set

    // saves all relevant information
    private ArrayList<Object> array;
    private types type;
    private int length;
    private ArrayVisualization arrayVisualization;
    private AbstractAlgorithm algorithm;

    // constructor without initial values
    public InfoArray(AbstractAlgorithm abstractAlgorithm, types type, int length){
        this.array = new ArrayList<Object>();
        this.type = type;
        this.length = length;
        this.algorithm = abstractAlgorithm;
    }

    // constructor with initial values
    public InfoArray(AbstractAlgorithm abstractAlgorithm, types type, int length, Object[] values){
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        this.array = new ArrayList<Object>();
        this.type = type;
        this.length = length;
        this.algorithm = abstractAlgorithm;
        for (int i = 0; i < values.length; i++){
            if(!sameType(values[i]) && this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'createArrayWithValues' :\nvalues["+i+"] not type of "+this.type.toString());
            }
        }
        this.array.addAll(Arrays.asList(values));
    }

    // recreates the array with a given length
    // called from "createArray" command during visualization process
    public void createArray(int length) throws InterruptedException {
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        this.length = length;
        this.array = new ArrayList<Object>();
        if (this.arrayVisualization != null){
            this.arrayVisualization.createArray(this, length);
        }
    }

    // creates the array with given values
    // not called directly from user
    // called from Array class if initial values should be added by initialization, from "deleteArray" and "createArrayWithValues" command
    public void createArrayWithValues(types type, int length, Object[] values) throws InterruptedException {
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        if (this.array == null){
            this.array = new ArrayList<Object>();
        }
        this.array.addAll(Arrays.asList(values));

        if (this.arrayVisualization != null){
            this.arrayVisualization.createArrayWithValues(this, length, values);
        }
    }

    // inserts a new value with a given index to the array
    public void insertElement(int index, Object value) throws InterruptedException {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'insertElement' of an array :\nIndex out of bound.");
        }
        if (sameType(value)){
            this.array.add(index, value);
            if (this.arrayVisualization != null){
                this.arrayVisualization.insertElement(this, index, value);
            }
        }
        else {
            if (this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'insertElement' of an array :\nValue not same Type as array elements.");
            }

        }
    }

    // returns the value of the array by a given index
    public Object getElementByIndex(int index) {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'getElementByIndex' of an array :\nIndex out of bound.");
        }

        if (this.arrayVisualization != null){
            this.arrayVisualization.getIndexByValue();
        }

        return this.array.get(index);
    }

    // returns the index by a given value
    public int getIndexByValue(Object value){
        if (!sameType(value) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'getIndexByValue' of an array :\nValue not same type as array elements.");
        }
        if (!this.array.contains(value) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'getIndexByValue' of an array :\nArray doesn't contain value.");
        }
        int index = -1;
        if (sameType(value)){
            for (int i = 0; i < this.array.size(); i++){
                if (this.array.get(i).equals(value)){
                    index = i;
                    break;
                }
            }
        }

        if (this.arrayVisualization != null){
            this.arrayVisualization.getValueByIndex();
        }

        return index;
    }

    // returns the length of the array
    public int getSize(){
        if (this.arrayVisualization != null){
            this.arrayVisualization.getSize();
        }
        return this.array.size();
    }

    // sets a new value to the element of a given index
    public void setElement(int index, Object newValue) throws InterruptedException {
        if (sameType(newValue)){
            this.array.set(index, newValue);
            if (this.arrayVisualization != null){
                this.arrayVisualization.setElement(this, index, newValue);
            }
        }
        else {
            if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'setElement' of an array :\nIndex out of bound.");
            }
            else {
                if (this.algorithm.getErrorString().equals("")){
                    this.algorithm.setErrorString("Error function 'setElement' of an array :\nValue not same type as array elements.");
                }
            }

        }
    }

    // swaps the value of 2 given indices of the array
    public void swapElementsByIndex(int index1, int index2, Object value1, Object value2) throws InterruptedException {
        if ((index1 < 0 || index1 >= this.length)  && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'swapElementsByIndex' of an array :\nIndex1 out of bound.");
        }
        if ((index2 < 0 || index2 >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'swapElementsByIndex' of an array :\nIndex2 out of bound.");
        }
        this.array.set(index1, value2);
        this.array.set(index2, value1);
        if (this.arrayVisualization != null){
            this.arrayVisualization.swapElements(this, index1, index2);
        }
    }

    // deletes the array list, but not the instance of the class
    public void deleteArray() throws InterruptedException {
        this.array = null;
        if (this.arrayVisualization != null){
            this.arrayVisualization.deleteArray(this);
        }
    }

    // deletes the element of the array by a given index
    public void deleteElementByIndex(int index) throws InterruptedException {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElementByIndex' of an array :\nIndex out of bound.");
        }
        this.array.remove(index);
        if (this.arrayVisualization != null){
            this.arrayVisualization.deleteElement(this, index);
        }
    }

    // deletes an element of the array by a given value
    public void deleteElementByValue(Object value) throws InterruptedException {
        if (!this.array.contains(value) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElementByValue' of an array :\nArray doesn't contain value.");
        }
        if (!sameType(value) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElementByValue' of an array :\nValue not same Type as array elements.");
        }
        if (this.arrayVisualization != null){
            int index = this.array.indexOf(value);
            this.arrayVisualization.deleteElement(this, index);
        }
        this.array.remove(value);
    }

    // sets the array visualization
    public void setArrayVisualization(ArrayVisualization arrayVisualization) {
        this.arrayVisualization = arrayVisualization;
    }

    // returns the array visualization
    public ArrayVisualization getArrayVisualization() {
        return this.arrayVisualization;
    }



    // returns the value list of the array
    public Object[] getValueList(){
        Object[] values = new Object[getSize()];
        for (int i = 0; i < getSize(); i++){
            values[i] = this.array.get(i);
        }
        return values;
    }

    // returns the type of the array
    public types getType() {
        return this.type;
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

}

package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import supportClasses.types;

import visualization.ArrayListVisualization;

import java.util.ArrayList;

public class InfoArrayList extends AbstractDataStructure {

    private ArrayList<Object> arrayListContent;
    private final types type;
    private int length;
    private ArrayListVisualization arrayListVisualization;
    private final AbstractAlgorithm algorithm;

    public InfoArrayList(AbstractAlgorithm abstractAlgorithm, types type, int length) {
        this.arrayListContent = new ArrayList<>();
        this.type = type;
        this.length = length;
        this.algorithm = abstractAlgorithm;
    }

    public void createArrayList(int length) {
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        this.length = length;
        this.arrayListContent = new ArrayList<>();
        if (this.arrayListVisualization != null){
            this.arrayListVisualization.createArrayList(this, length);
        }
    }

    // deletes the array list, but not the instance of the class
    public void deleteArrayList() {
        this.arrayListContent = null;
        if (this.arrayListVisualization != null){
            this.arrayListVisualization.deleteArrayList(this);
        }
    }

    // returns the value of the array by a given index
    public Object getElementByIndex(int index) {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'getElementByIndex' of an ArrayList :\nIndex out of bound.");
        }
        return this.arrayListContent.get(index);
    }

    // inserts a new value with a given index to the array
    public void insertElement(int index, Object value) throws InterruptedException {
        if ((index < 0 || index >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'insertElement' of an ArrayList :\nIndex out of bound.");
        }
        if (sameType(value)){
            this.arrayListContent.add(index, value);
            if (this.arrayListVisualization != null){
                this.arrayListVisualization.insertElement(this, index, value);
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
            this.algorithm.setErrorString("Error function 'deleteElementByIndex' of an ArrayList :\nIndex out of bound.");
        }
        this.arrayListContent.remove(index);
        if (this.arrayListVisualization != null){
            this.arrayListVisualization.deleteElement(this, index);
        }
    }

    public void swapElementsByIndex(int index1, int index2, Object value1, Object value2) throws InterruptedException {
        if ((index1 < 0 || index1 >= this.length)  && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'swapElementsByIndex' of an ArrayList :\nIndex1 out of bound.");
        }
        if ((index2 < 0 || index2 >= this.length) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'swapElementsByIndex' of an ArrayList :\nIndex2 out of bound.");
        }
        this.arrayListContent.set(index1, value2);
        this.arrayListContent.set(index2, value1);
        if (this.arrayListVisualization != null){
            this.arrayListVisualization.swapElements(this, index1, index2);
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
        return this.arrayListContent.size();
    }

    public ArrayListVisualization getArrayListVisualization() {
        return this.arrayListVisualization;
    }

    public void setArrayListVisualization(ArrayListVisualization arrayListVisualization) {
        this.arrayListVisualization = arrayListVisualization;
    }

    // returns the value list of the array
    public Object[] getValueList(){
        Object[] values = new Object[getSize()];
        for (int i = 0; i < getSize(); i++){
            values[i] = this.arrayListContent.get(i);
        }
        return values;
    }

    // returns the type of the array
    public types getType() {
        return this.type;
    }

}

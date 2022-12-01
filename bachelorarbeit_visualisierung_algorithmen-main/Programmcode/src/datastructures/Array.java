package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.arrayCommands.*;
import supportClasses.types;

public class Array extends AbstractDataStructure {
    // class for the user to access
    // creates commands and organizes the access to the actual array

    // saves all relevant information
    private final AbstractAlgorithm algorithm;
    public InfoArray infoArray;

    // constructor without initial values
    public Array(AbstractAlgorithm AbstractAlgorithm, InfoArray infoArray, types typ, int length){
        this.algorithm = AbstractAlgorithm;
        this.infoArray = infoArray;
        CreateArray CreateArray = new CreateArray(this.infoArray, length);
        this.algorithm.appendCommandOrder(CreateArray);
    }

    // constructor with initial values
    public Array(AbstractAlgorithm AbstractAlgorithm, InfoArray infoArray, types typ, int length, Object[] values) throws InterruptedException {
        this.algorithm = AbstractAlgorithm;
        this.infoArray = infoArray;
        CreateArrayWithValues CreateArrayWithValues = new CreateArrayWithValues(this.infoArray, typ, length, values);
        this.infoArray.createArrayWithValues(typ, length, values);
        this.algorithm.appendCommandOrder(CreateArrayWithValues);
    }

    // for inserting a new value into the array by a given index
    public void insertElement(int index, Object value) throws InterruptedException {
        InsertElementArray InsertElementArray = new InsertElementArray(this.infoArray, index, value);
        this.infoArray.insertElement(index, value);
        this.algorithm.appendCommandOrder(InsertElementArray);
    }

    // for inserting a new element to the array at position 0
    public void insertElementAtStart(Object value) throws InterruptedException {
        InsertElementArray InsertElementArray = new InsertElementArray(this.infoArray, 0, value);
        this.infoArray.insertElement(0, value);
        this.algorithm.appendCommandOrder(InsertElementArray);
    }

    // for inserting a new element to the array at the end of it
    public void insertElementAtEnd(Object value) throws InterruptedException {
        int size = this.infoArray.getSize();
        InsertElementArray InsertElementArray = new InsertElementArray(this.infoArray, size, value);
        this.infoArray.insertElement(size, value);
        this.algorithm.appendCommandOrder(InsertElementArray);
    }

    // for deleting an element of the list by a given value
    public void deleteElementByValue(Object value) throws InterruptedException {
        int index = this.infoArray.getIndexByValue(value);
        DeleteElementByValueArray DeleteElementByValueArray = new DeleteElementByValueArray(this.infoArray, index, value);
        this.infoArray.deleteElementByIndex(index);
        this.algorithm.appendCommandOrder(DeleteElementByValueArray);
    }

    // for deleting an element of the array by a given index
    public void deleteElementByIndex(int index) throws InterruptedException {
        Object value = this.infoArray.getElementByIndex(index);
        DeleteElementByIndexArray DeleteElementByIndexArray = new DeleteElementByIndexArray(this.infoArray, index, value);
        this.algorithm.appendCommandOrder(DeleteElementByIndexArray);
        this.infoArray.deleteElementByIndex(index);

    }

    // for deleting the whole array but not the instance of the infoArray
    public void deleteArray() throws InterruptedException {
        DeleteArray DeleteArray = new DeleteArray(this.infoArray);
        this.algorithm.appendCommandOrder(DeleteArray);
        infoArray.deleteArray();
    }

    // for swapping 2 elements of the array by their given indices
    public void swapElements(int index1, int index2) throws InterruptedException {
        Object value1 = this.infoArray.getElementByIndex(index1);
        Object value2 = this.infoArray.getElementByIndex(index2);
        SwapElementsByIndexArray SwapElementsByIndexArray = new SwapElementsByIndexArray(this.infoArray, index1, index2, value1, value2);
        this.algorithm.appendCommandOrder(SwapElementsByIndexArray);
        this.infoArray.swapElementsByIndex(index1, index2, value1, value2);
    }

    // for setting a new value to an element of the array by a given index
    public void setElement(int index, Object value) throws InterruptedException {
        SetElementArray SetElementArray = new SetElementArray(this.infoArray, index, value);
        this.algorithm.appendCommandOrder(SetElementArray);
        infoArray.setElement(index, value);
    }

    // for returning the index by a given value
    public int getIndexByValue(Object value){
        int index = infoArray.getIndexByValue(value);
        GetIndexByValueArray GetIndexByValueArray = new GetIndexByValueArray(this.infoArray, index, value);
        this.algorithm.appendCommandOrder(GetIndexByValueArray);
        return index;
    }

    // for returning the value of an element by a given index
    public Object getValueByIndex(int index){
        Object value = infoArray.getElementByIndex(index);
        GetValueByIndexArray GetValueByIndexArray = new GetValueByIndexArray(this.infoArray, index, value);
        this.algorithm.appendCommandOrder(GetValueByIndexArray);
        return value;
    }

    // for returning the size of the array
    public int getSize(){
        int size = this.infoArray.getSize();
        GetSizeArray GetSizeArray = new GetSizeArray(this.infoArray, size);
        this.algorithm.appendCommandOrder(GetSizeArray);
        return size;
    }
}

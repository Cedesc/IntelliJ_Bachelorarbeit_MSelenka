package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.arrayListCommands.CreateArrayList;
import commands.arrayListCommands.DeleteArrayList;
import commands.arrayListCommands.InsertElementArrayList;
import commands.arrayListCommands.SwapElementsByIndexArrayList;
import supportClasses.types;

/**
 * Provides less functionality than the previously existing data structure "Array", but includes animations and leads
 * to errors less often.
 */
public class ArrayList extends AbstractDatastructure {

    private AbstractAlgorithm algorithm;
    public InfoArrayList infoArrayList;
    private types typ;
    private int length;

    public ArrayList(AbstractAlgorithm AbstractAlgorithm, InfoArrayList infoArrayList, types typ, int length){
        this.algorithm = AbstractAlgorithm;
        this.infoArrayList = infoArrayList;
        this.typ = typ;
        this.length = length;
        CreateArrayList createArrayList = new CreateArrayList(this.infoArrayList, typ, length);
        this.algorithm.appendCommandOrder(createArrayList);
    }

    // for inserting a new element to the array at the end of it
    public void insertElementAtEnd(Object value) throws InterruptedException {
        int size = this.infoArrayList.getSize();
        InsertElementArrayList insertElementArrayList = new InsertElementArrayList(this.infoArrayList, size, value);
        this.infoArrayList.insertElement(size, value);
        this.algorithm.appendCommandOrder(insertElementArrayList);
    }

    // for inserting a new element to the array at position 0
    public void insertElementAtStart(Object value) throws InterruptedException {
        InsertElementArrayList insertElementArrayList = new InsertElementArrayList(this.infoArrayList, 0, value);
        this.infoArrayList.insertElement(0, value);
        this.algorithm.appendCommandOrder(insertElementArrayList);
    }

    // for inserting a new value into the array by a given index
    public void insertElement(int index, Object value) throws InterruptedException {
        InsertElementArrayList insertElementArrayList = new InsertElementArrayList(this.infoArrayList, index, value);
        this.infoArrayList.insertElement(index, value);
        this.algorithm.appendCommandOrder(insertElementArrayList);
    }

    // for deleting the whole array but not the instance of the infoArray
    public void deleteArrayList() throws InterruptedException {
        DeleteArrayList deleteArrayList = new DeleteArrayList(this.infoArrayList);
        this.algorithm.appendCommandOrder(deleteArrayList);
        infoArrayList.deleteArrayList();
    }

    // for swapping 2 elements of the array by their given indices
    public void swapElements(int index1, int index2) throws InterruptedException {
        Object value1 = this.infoArrayList.getElementByIndex(index1);
        Object value2 = this.infoArrayList.getElementByIndex(index2);
        SwapElementsByIndexArrayList swapElementsByIndexArrayList = new SwapElementsByIndexArrayList(this.infoArrayList, index1, index2, value1, value2);
        this.algorithm.appendCommandOrder(swapElementsByIndexArrayList);
        this.infoArrayList.swapElementsByIndex(index1, index2, value1, value2);
    }

}

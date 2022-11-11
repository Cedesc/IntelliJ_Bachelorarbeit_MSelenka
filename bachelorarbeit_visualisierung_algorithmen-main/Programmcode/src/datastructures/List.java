package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.listCommands.*;

public class List extends AbstractDatastructure {
    // class for the user to access
    // creates commands and organizes the access to the actual list

    // saves all relevant information
    public InfoList infoList;
    private AbstractAlgorithm algorithm;

    // constructor
    public List(AbstractAlgorithm AbstractAlgorithm, InfoList infoList){
        this.algorithm = AbstractAlgorithm;
        this.infoList = infoList;
        CreateList CreateList = new CreateList(infoList);
        this.algorithm.appendCommandOrder(CreateList);
    }

    // for inserting a new variable in the list
    // creates "insertElementList" command
    public void insertELement(Variable variable, int index) throws InterruptedException {
        InsertElementList insertELementList = new InsertElementList(this.infoList, variable, variable.getValue(), index);
        this.algorithm.appendCommandOrder(insertELementList);
        this.infoList.insertElement(variable, index);
    }

    // for inserting a variable at the start of the list
    // creates "insertElementList" command
    public void insertElementAtStart(Variable variable) throws InterruptedException {
        InsertElementList insertELementList = new InsertElementList(this.infoList, variable, variable.getValue(), 0);
        this.algorithm.appendCommandOrder(insertELementList);
        this.infoList.insertElement(variable, 0);
    }

    // for inserting a variable at the end of the list
    // creates "insertElementList" command
    public void insertElementAtEnd(Variable variable) throws InterruptedException {
        InsertElementList insertELementList = new InsertElementList(this.infoList, variable, variable.getValue(), this.infoList.getSize());
        this.algorithm.appendCommandOrder(insertELementList);
        this.infoList.insertElement(variable, this.infoList.getSize());
    }

    // for deleting a variable element of the list by a given variable
    // creates "deleteElementList" command
    public void deleteElement(Variable variable) throws InterruptedException {
        Object value = variable.getValue();
        DeleteElementList DeleteElementList = new DeleteElementList(this.infoList, variable, value, infoList.searchIndex(value));
        this.algorithm.appendCommandOrder(DeleteElementList);
        this.infoList.deleteElement(variable);
    }

    // for deleting a variable element of a list by a given index
    // creates "deleteElementByIndex" command
    public void deleteElementByIndex(int index) throws InterruptedException {
        DeleteElementByIndexList deleteElementList = new DeleteElementByIndexList(this.infoList, infoList.getElement(index), infoList.getElementIndex(index), index);
        this.algorithm.appendCommandOrder(deleteElementList);
        this.infoList.deleteElementByIndex(index);
    }

    // for deleting a variable element of a list by a given value
    // fails if the list doesn't contain the value
    // deletes the first element of the list if the list contains a duplicate of the value
    // creates "deleteElementByValue" command
    public void deleteElementByValue(Object value) throws InterruptedException {
        int index = infoList.searchIndex(value);
        DeleteElementByValueList deleteElementList = new DeleteElementByValueList(this.infoList, infoList.getElement(index), value, index);
        this.algorithm.appendCommandOrder(deleteElementList);
        this.infoList.deleteElementByValue(value);
    }

    // deletes the whole list, but not the instance of the infoList
    // creates "deleteList" command
    public void deleteList(){
        DeleteList DeleteList = new DeleteList(this.infoList);
        this.algorithm.appendCommandOrder(DeleteList);
        infoList.deleteList();
    }

    // for setting a new value to an element of the list
    // creates "setElementList" command
    public void setElement(Variable variable, Object value) throws InterruptedException {
        SetElementList SetElementList = new SetElementList(this.infoList, variable, value, infoList.getIndex(variable));
        this.algorithm.appendCommandOrder(SetElementList);
        infoList.setElement(variable, value);
    }

    // returns the variable element of the list by a given value
    // creates "getElementByValue" command
    public Variable getElementByValue(Object value){
        int index = infoList.searchIndex(value);
        Variable var = this.infoList.searchElement(value);
        GetElementByValueList GetElementByValueList = new GetElementByValueList(this.infoList, var, value, index);
        this.algorithm.appendCommandOrder(GetElementByValueList);
        return var;
    }

    // returns the index of a given variable of the list
    // creates "getIndexByElement" command
    public int getIndexByElement(Variable variable){
        int index = infoList.getIndex(variable);
        GetIndexByElementList GetIndexByElementList = new GetIndexByElementList(this.infoList, variable, infoList.getElementIndex(index), index);
        this.algorithm.appendCommandOrder(GetIndexByElementList);
        return index;
    }

    // returns the Variable of a list by a given index
    // creates "getElementByIndex" command
    public Variable getElementByIndex(int index){
        Variable memVar = this.infoList.getElement(index);
        GetElementByIndexList GetElementByIndexList = new GetElementByIndexList(this.infoList, memVar, infoList.getElementIndex(index), index);
        this.algorithm.appendCommandOrder(GetElementByIndexList);
        return memVar;
    }

    // returns the length of the list
    // creates "getSizeList" command
    public int getSize(){
        int size = this.infoList.getSize();
        GetSizeList GetSizeList = new GetSizeList(this.infoList, size);
        this.algorithm.appendCommandOrder(GetSizeList);
        return size;
    }
}

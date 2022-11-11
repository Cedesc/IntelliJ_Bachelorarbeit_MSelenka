package datastructures;

import visualization.ListVisualization;
import abstractAlgorithm.AbstractAlgorithm;
import java.util.ArrayList;

public class InfoList extends AbstractDatastructure {
    // actual list with alle elements
    // if an error occur by the user: all functions are able to set the error string of the abstract algorithm class if the string is not set yet
    // all functions will cast the corresponding list visualization function if the list visualization is set

    // saves alle relevant information
    private ArrayList<Variable> list;
    private ListVisualization listVisualization;
    private AbstractAlgorithm algorithm;

    // constructor
    public InfoList(AbstractAlgorithm abstractAlgorithm){
        this.list = new ArrayList<Variable>();
        this.algorithm = abstractAlgorithm;
    }

    // recreates the list and calls the visualization
    // called from the command "createList" in the visualization process
    public void createList() throws InterruptedException {
        if (this.listVisualization != null){
            this.listVisualization.createList(this);
        }
    }

    // recreates the list with all given values
    // called from the "deleteList" command in the visualization process if the command is inverted
    public void createListWithValues(ArrayList<Variable> values) throws InterruptedException {
        this.list = values;
        if (this.listVisualization != null){
            this.listVisualization.createListWithValues(this, this.list);
        }
    }

    // sets the given variable with a new value
    public void setElement(Variable variable, Object value) throws InterruptedException {
        int position = getIndex(variable);
        if (position >= 0 && position < this.list.size()){
            this.list.get(position).setValue(value);
            if (this.listVisualization != null){
                this.listVisualization.setElement(this, variable, value);
            }
        }
        else {
            if (this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'setElement' of a list :\nList does not contain variable.");
            }
        }
    }

    // get variable element of the list by a given index
    // returns null if index is out of bound
    public Variable getElement(int index){
        if ((index < 0 || index > this.list.size())){
            if (this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'getElement' of a list :\nIndex is out of bound.");
            }
            return null;
        }
        return list.get(index);
    }

    // returns the index of a given variable of the list
    // returns -1 if not contained
    public int getIndex(Variable variable){
        for (int i = 0; i < list.size(); i++){
            if (this.list.get(i).getValue() == variable.getValue()){
                return i;
            }
        }
        if (this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'getIndex' of a list :\nList does not contain variable");
        }
        return -1;
    }

    // returns the variable element of the list by a given index
    // returns null if index is out of bound
    public Object getElementIndex(int index){
        if ((index < 0 || index > this.list.size())){
            if (this.algorithm.getErrorString().equals("")){
                this.algorithm.setErrorString("Error function 'getElementByValue' of a list :\nIndex is out of bound.");
            }
            return null;
        }
        return this.list.get(index).getValue();
    }

    // deletes the whole list, but not the class instance itself
    public void deleteList(){
        if (this.listVisualization != null){
            this.listVisualization.deleteList(this);
        }
        list.removeAll(list);
    }

    // insert a variable to the list at the position of the given index
    public void insertElement(Variable variable, int index) throws InterruptedException {
        if ((index < 0 || index > this.list.size()) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'insertElement' of a list :\nIndex is out of bound.");
        }
        if (this.listVisualization != null){
            this.listVisualization.insertElement(this, variable, index);
        }
        list.add(index, variable);
    }

    // returns the variable of a given value
    // returns null if not contained
    public Variable searchElement(Object value){
        for (int i = 0; i < list.size(); i++){
            Variable memVar = list.get(i);
            if (memVar.getValue().equals(value)){
                return memVar;
            }
        }
        if (this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'searchElement' of a list :\nList does not contain value");
        }
        return null;
    }

    // returns the index of a given index
    // returns -1 if not contained
    public int searchIndex(Object value){
        for (int i = 0; i < list.size(); i++){
            Variable memVar = list.get(i);
            if (memVar.getValue().equals(value)){
                return i;
            }
        }
        if (this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'searchIndex' of a list :\nList does not contain value");
        }
        return -1;
    }

    // deletes an element of the list by a given variable
    public void deleteElement(Variable variable) throws InterruptedException {
        if (!this.list.contains(variable) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElement' of a list :\nList does not contain variable");
        }
        if (this.listVisualization != null){
            this.listVisualization.deleteElement(this, variable);
        }
        list.remove(variable);
    }

    // returns the length of the list
    public int getSize(){
        return this.list.size();
    }

    // deletes an element of the list by a given index
    public void deleteElementByIndex(int index) throws InterruptedException {
        if ((index < 0 || index > this.list.size()) && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElementByIndex' of a list :\nIndex is out of bound.");
        }
        if (this.listVisualization != null){
            this.listVisualization.deleteElement(this, this.list.get(index));
        }
        this.list.remove(index);
    }

    // deletes an element of the list by a given value
    public void deleteElementByValue(Object value) throws InterruptedException {
        boolean contained = false;
        for (int i = 0; i < this.list.size(); i++){
            if(this.list.get(i).getValue().equals(value)){
                contained = true;
                if (this.listVisualization != null){
                    this.listVisualization.deleteElement(this, this.list.get(i));
                }
                this.list.remove(i);
            }
        }
        if (!contained && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'deleteElementByValue' of a list :\nList does not contain value");
        }
    }

    // sets the list visualization
    public void setListVisualization(ListVisualization listVisualization){
        this.listVisualization = listVisualization;
    }

    // returns the list visualization
    public ListVisualization getListVisualization() {
        return this.listVisualization;
    }

    // returns all current values of the list in an ArrayList
    public ArrayList<Variable> getListValues() {
        return this.list;
    }
}

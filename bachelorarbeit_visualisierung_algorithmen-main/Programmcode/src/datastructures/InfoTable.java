package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import supportClasses.types;

import visualization.TableVisualization;

import java.util.ArrayList;

public class InfoTable extends AbstractDatastructure {

    private ArrayList<Object> tableContent;
    private types type;
    private int length;
    private TableVisualization tableVisualization;
    private AbstractAlgorithm algorithm;

    public InfoTable(AbstractAlgorithm abstractAlgorithm, types type, int length) {
        this.tableContent = new ArrayList<Object>();
        this.type = type;
        this.length = length;
        this.algorithm = abstractAlgorithm;
    }

    public void createTable(int length) throws InterruptedException {
        if (length < 1 && this.algorithm.getErrorString().equals("")){
            this.algorithm.setErrorString("Error function 'createArray' :\nParameter length < 1.");
        }
        this.length = length;
        this.tableContent = new ArrayList<Object>();
        if (this.tableVisualization != null){
            this.tableVisualization.createTable(this, length);
        }
    }

    public TableVisualization getTableVisualization() {
        return this.tableVisualization;
    }

    public void setTableVisualization(TableVisualization tableVisualization) {
        this.tableVisualization = tableVisualization;
    }

}

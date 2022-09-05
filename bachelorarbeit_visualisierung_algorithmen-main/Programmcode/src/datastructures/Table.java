package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import commands.tableCommands.CreateTable;
import supportClasses.types;

public class Table extends AbstractDatastructure {

    private AbstractAlgorithm algorithm;
    public InfoTable infoTable;
    private types typ;
    private int length;

    public Table(AbstractAlgorithm AbstractAlgorithm, InfoTable infoTable, types typ, int length){
        this.algorithm = AbstractAlgorithm;
        this.infoTable = infoTable;
        this.typ = typ;
        this.length = length;
        CreateTable createTable = new CreateTable(this.infoTable, typ, length);
        this.algorithm.appendCommandOrder(createTable);
    }
}

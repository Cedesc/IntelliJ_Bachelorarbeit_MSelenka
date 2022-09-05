package builders;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Table;
import datastructures.InfoTable;
import supportClasses.types;

public class TableBuilder {

    public Table createTable(AbstractAlgorithm AbstractAlgorithm, InfoTable infoTable, types typ, int length){
        Table table = new Table(AbstractAlgorithm, infoTable, typ, length);
        return table;
    }

    public InfoTable createInfoTable(AbstractAlgorithm abstractAlgorithm, types type, int length){
        InfoTable infoTable = new InfoTable(abstractAlgorithm, type, length);
        return infoTable;
    }

}

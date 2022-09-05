package commands.tableCommands;

import datastructures.InfoTable;
import supportClasses.types;

public class CreateTable extends TableCommand {

    private InfoTable createdTable;
    private types type;
    private int length;

    public CreateTable(InfoTable infoTable, types type, int length){
        this.createdTable = infoTable;
        this.type = type;
        this.length = length;
        setCommandString("Create table");
    }

    @Override
    public void exeCommand() throws InterruptedException {
        this.createdTable.createTable(length);
    }

    @Override
    public void backCommand() throws InterruptedException {
        this.createdTable.createTable(3);
    }

    @Override
    public InfoTable getTable() {
        return this.createdTable;
    }
}

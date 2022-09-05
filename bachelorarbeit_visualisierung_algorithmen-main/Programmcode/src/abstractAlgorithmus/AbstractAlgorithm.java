package abstractAlgorithmus;

import builders.ArrayBuilder;
import builders.ListBuilder;
import builders.VariablenBuilder;
import commands.Command;
import datastructures.*;
import supportClasses.types;
import supportClasses.CommandListColumn;
import java.util.ArrayList;

public abstract class AbstractAlgorithm {

    // saves alle builder, command list and error string
    private VariablenBuilder variablenBuilder;
    private ListBuilder listBuilder;
    private ArrayBuilder arrayBuilder;
    protected ArrayList<Command> commandOrder;
    private String errorString;

    // constructor
    public AbstractAlgorithm(){
        this.commandOrder = new ArrayList<Command>();
        this.errorString = "";
    }

    // sets error String
    public void setErrorString(String errorString){
        this.errorString = errorString;
    }

    // return error String
    public String getErrorString(){
        return this.errorString;
    }

    // appends current command list
    public void appendCommandOrder(Command command){
        this.commandOrder.add(command);
    }


    // return command list in shape for visualization
    public ArrayList<CommandListColumn> getCommandListString(){
        ArrayList<CommandListColumn> commandStringList = new ArrayList<CommandListColumn>();
        for (int i = 0; i < commandOrder.size(); i++){
            commandStringList.add(new CommandListColumn(commandOrder.get(i).getCommandString()));
        }
        return commandStringList;
    }

    // returns command list
    public ArrayList<Command> getCommandOrder() {
        return commandOrder;
    }


    // create Variable Method
    public Variable create_Variable(types type){
        if (this.variablenBuilder == null){
            this.variablenBuilder = new VariablenBuilder();
        }
        InfoVariable infoVariable = this.variablenBuilder.createInfoVariable(this, type);
        Variable variable = this.variablenBuilder.createVariable(this, infoVariable, type);
        return variable;
    }


    // create List
    public datastructures.List create_List(){
        if (this.listBuilder == null){
            this.listBuilder = new ListBuilder();
        }
        InfoList infoList = this.listBuilder.createInfoList(this);
        datastructures.List list = this.listBuilder.createList(this, infoList);
        return list;
    }


    // create Array
    public Array create_Array(types type, int length){
        if (length <= 0){
            this.errorString = "Error create Array: length <= 0.\nlength should be positive and greater 0.";
            return null;
        }
        if (this.arrayBuilder == null){
            this.arrayBuilder = new ArrayBuilder();
        }
        InfoArray infoArray = this.arrayBuilder.createInfoArray(this, type, length);
        Array array = this.arrayBuilder.createArray(this, infoArray, type , length);
        return array;
    }

    // create Array with values
    public Array create_ArrayWithValues(types type, int length, Object[] values) throws InterruptedException {
        if (length <= 0){
            this.errorString = "Error create Array: length <= 0.\nlength should be positive and greater 0.";
            return null;
        }
        if (values.length == 0){
            this.errorString = "Error create Array: input Array 'values' is empty.";
            return null;
        }
        if (this.arrayBuilder == null){
            this.arrayBuilder = new ArrayBuilder();
        }
        InfoArray infoArray = this.arrayBuilder.createInfoArray(this, type, length, values);
        Array array = this.arrayBuilder.createArray(this, infoArray, type , length, values);
        return array;
    }

    public abstract void executeAlgorithm() throws InterruptedException;

}

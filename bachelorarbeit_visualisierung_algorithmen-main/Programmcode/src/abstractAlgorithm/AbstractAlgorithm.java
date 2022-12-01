package abstractAlgorithm;

import builders.*;
import commands.Command;
import datastructures.*;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;
import supportClasses.CommandListColumn;

public abstract class AbstractAlgorithm {

    // saves alle builder, command list and error string
    private VariableBuilder variableBuilder;
    private ListBuilder listBuilder;
    private ArrayBuilder arrayBuilder;
    public ArrayListBuilder arrayListBuilder;
    public TreeBuilder treeBuilder;
    protected java.util.ArrayList<Command> commandOrder;
    private String errorString;

    // constructor
    public AbstractAlgorithm(){
        this.commandOrder = new java.util.ArrayList<>();
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
    public java.util.ArrayList<CommandListColumn> getCommandListString(){
        java.util.ArrayList<CommandListColumn> commandStringList = new java.util.ArrayList<>();
        for (Command command : commandOrder) {
            commandStringList.add(new CommandListColumn(command.getCommandString()));
        }
        return commandStringList;
    }

    // returns command list
    public java.util.ArrayList<Command> getCommandOrder() {
        return commandOrder;
    }


    // create Variable Method
    public Variable create_Variable(types type){
        if (this.variableBuilder == null){
            this.variableBuilder = new VariableBuilder();
        }
        InfoVariable infoVariable = this.variableBuilder.createInfoVariable(this, type);
        return this.variableBuilder.createVariable(this, infoVariable, type);
    }


    // create List
    public datastructures.List create_List(){
        if (this.listBuilder == null){
            this.listBuilder = new ListBuilder();
        }
        InfoList infoList = this.listBuilder.createInfoList(this);
        return this.listBuilder.createList(this, infoList);
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
        return this.arrayBuilder.createArray(this, infoArray, type , length);
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
        return this.arrayBuilder.createArray(this, infoArray, type , length, values);
    }

    public abstract void executeAlgorithm() throws InterruptedException;





    // OWN TEST STUFF

    public ArrayList create_ArrayList(types type, int length) {
        if (length <= 0){
            this.errorString = "Error create Array: length <= 0.\nlength should be positive and greater 0.";
            return null;
        }
        if (this.arrayListBuilder == null){
            this.arrayListBuilder = new ArrayListBuilder();
        }
        InfoArrayList infoArrayList = this.arrayListBuilder.createInfoArrayList(this, type, length);
        return this.arrayListBuilder.createArrayList(this, infoArrayList, length);
    }

    public Tree create_Tree(types type, Object rootValue) {
        if (this.treeBuilder == null){
            this.treeBuilder = new TreeBuilder();
        }
        MyNode root = new MyNode(0, rootValue);
        InfoTree infoTree = this.treeBuilder.createInfoTree(this, type, root);
        return this.treeBuilder.createTree(this, infoTree, root);
    }

    public Tree create_Tree(types type) {
        return create_Tree(type, null);
    }

}

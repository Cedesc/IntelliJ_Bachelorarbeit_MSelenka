package abstractAlgorithmus;

import builders.*;
import commands.Command;
import datastructures.*;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;
import supportClasses.CommandListColumn;
import java.util.ArrayList;

public abstract class AbstractAlgorithm {

    // saves alle builder, command list and error string
    private VariablenBuilder variablenBuilder;
    private ListBuilder listBuilder;
    private ArrayBuilder arrayBuilder;
    // OWN TEST STUFF
    public ExperimentBuilder experimentBuilder;
    public TreeBuilder treeBuilder;
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
        return this.variablenBuilder.createVariable(this, infoVariable, type);
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

    public Experiment create_Experiment(types type, int length) {
        if (length <= 0){
            this.errorString = "Error create Array: length <= 0.\nlength should be positive and greater 0.";
            return null;
        }
        if (this.experimentBuilder == null){
            this.experimentBuilder = new ExperimentBuilder();
        }
        InfoExperiment infoExperiment = this.experimentBuilder.createInfoExperiment(this, type, length);
        return this.experimentBuilder.createExperiment(this, infoExperiment, type , length);
    }

    public Tree create_Tree(types type, MyNode root) {
        if (this.treeBuilder == null){
            this.treeBuilder = new TreeBuilder();
        }
        InfoTree infoTree = this.treeBuilder.createInfoTree(this, type, root);
        return this.treeBuilder.createTree(this, infoTree, type, root);
    }

}

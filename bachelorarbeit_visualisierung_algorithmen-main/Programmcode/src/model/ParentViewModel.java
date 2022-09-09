package model;

import commands.tableCommands.TableCommand;
import controller.ExecuteAlgorithmController;
import controller.SelectAlgorithmController;
import datastructures.InfoTable;
import visualization.ArrayVisualization;
import visualization.ListVisualization;
import visualization.TableVisualization;
import visualization.VariableVisualization;
import abstractAlgorithmus.AbstractAlgorithm;
import commands.Command;
import commands.arrayCommands.ArrayCommand;
import commands.listCommands.ListCommand;
import commands.variableCommands.VariableCommand;
import datastructures.InfoArray;
import datastructures.InfoList;
import datastructures.InfoVariable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import supportClasses.CommandListColumn;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class ParentViewModel extends Application {

    // saves all relevant information:
    // e.g. the selected algorithm, the visualization type, the current command which is executed
    private AbstractAlgorithm algorithm;
    private ExecuteAlgorithmController executeAlgorithmController;
    private VariableVisualization variableVisualization;
    private ArrayVisualization arrayVisualization;
    private ListVisualization listVisualization;
    // OWN TEST TABLE STUFF
    private TableVisualization tableVisualization;
    private int currentCommandCount;

    // Start of the Program
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    // visual start of the program
    // sets the window and the first view: selection of the algorithm
    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("Algorithm Visual Studio");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmView = fxmlLoader.load(getClass().getResource("../views/SelectAlgorithmView.fxml").openStream());
        SelectAlgorithmController selectAlgorithmController = (SelectAlgorithmController) fxmlLoader.getController();
        Scene rootScene = new Scene(selectAlgorithmView);
        primaryStage.setScene(rootScene);
        selectAlgorithmController.setParentViewModel(this);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    // gets the next command of the algorithm and executes it and the visualization of it in the background
    // called from the execute algorithm controller
    public void exeNextCommand() throws InterruptedException {
        this.executeAlgorithmController.commandListTable.getSelectionModel().select(currentCommandCount+1);
        exeCommand();

        if (currentCommandCount == 0){
            this.executeAlgorithmController.setStepBackButtonVisible();
        }
        if (currentCommandCount == this.algorithm.getCommandOrder().size()-1){
            this.executeAlgorithmController.setStepForwardButtonUnvisible();
        }
        this.currentCommandCount += 1;
    }

    // inserts the last command, the command reverts itself and changes the visualization in the background
    // called from the execute algorithm controller

    public void exePreviousCommand() throws InterruptedException {
        this.executeAlgorithmController.commandListTable.getSelectionModel().select(currentCommandCount-1);
        String currentClassCommand = this.algorithm.getCommandOrder().get(currentCommandCount-1).getClass().getSuperclass().getSimpleName();
        switch (currentClassCommand){
            case "VariableCommand":
                VariableCommand variableCommand = (VariableCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount-1);
                InfoVariable infoVariable = variableCommand.getVariable();
                if (infoVariable.getVariableVisualization() == null){
                    if (this.variableVisualization == null){
                        this.variableVisualization = new VariableVisualization(executeAlgorithmController);
                    }
                    else {
                        this.variableVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoVariable.setVariableVisualization(this.variableVisualization);

                }
                variableCommand.backCommand();
                break;
            case "ListCommand":
                ListCommand listCommand = (ListCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount-1);
                InfoList infoList = listCommand.getList();
                if (infoList.getListVisualization() == null){
                    if (this.listVisualization == null){
                        this.listVisualization = new ListVisualization(executeAlgorithmController);
                    }
                    else {
                        this.listVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoList.setListVisualization(this.listVisualization);

                }
                listCommand.backCommand();
                break;
            case "ArrayCommand":
                ArrayCommand arrayCommand = (ArrayCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount-1);
                InfoArray infoArray = arrayCommand.getArray();
                if (infoArray.getArrayVisualization() == null){
                    if (this.arrayVisualization == null){
                        this.arrayVisualization = new ArrayVisualization(executeAlgorithmController);
                    }
                    else {
                        this.arrayVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoArray.setArrayVisualization(this.arrayVisualization);

                }
                arrayCommand.backCommand();
                break;
            // OWN TEST TABLE STUFF
            case "TableCommand":
                TableCommand tableCommand = (TableCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount-1);
                InfoTable infoTable = tableCommand.getTable();
                if (infoTable.getTableVisualization() == null) {
                    if (this.tableVisualization == null) {
                        this.tableVisualization = new TableVisualization(executeAlgorithmController);
                    }
                    else {
                        this.tableVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoTable.setTableVisualization(this.tableVisualization);
                }
                tableCommand.backCommand();
                break;
            default:
                System.out.println("Warning! Unknown command type!");
                break;
        }

        if (currentCommandCount == 1){
            this.executeAlgorithmController.setStepBackButtonUnvisible();
        }
        if (currentCommandCount == this.algorithm.getCommandOrder().size()){
            this.executeAlgorithmController.setStepForwardButtonVisible();
        }
        this.currentCommandCount -= 1;
    }
    // executes the whole command list in the order
    // called if the complete visualization is selected
    // DOES NOT WAIT BETWEEN THE COMMAND!!!! ONLY THE END RESULT OF THE ALGORITHM WILL BE SEEN

    public void startVisualization() throws InterruptedException {
        ArrayList<Command> commandOrder = this.algorithm.getCommandOrder();
        for (int i = 0; i < commandOrder.size(); i++){
            this.executeAlgorithmController.commandListTable.getSelectionModel().select(currentCommandCount);
            exeCommand();
            if (i == commandOrder.size()-1){
                this.executeAlgorithmController.terminateVisualization();
            }
            this.currentCommandCount += 1;
        }
    }

    private void exeCommand() throws InterruptedException {
        String currentClassCommand = this.algorithm.getCommandOrder().get(currentCommandCount).getClass().getSuperclass().getSimpleName();
        switch (currentClassCommand){
            case "VariableCommand":
                VariableCommand variableCommand = (VariableCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount);
                InfoVariable infoVariable = variableCommand.getVariable();
                if (infoVariable.getVariableVisualization() == null){
                    if (this.variableVisualization == null){
                        this.variableVisualization = new VariableVisualization(executeAlgorithmController);
                    }
                    else {
                        this.variableVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoVariable.setVariableVisualization(this.variableVisualization);

                }
                variableCommand.exeCommand();
                break;
            case "ListCommand":
                ListCommand listCommand = (ListCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount);
                InfoList infoList = listCommand.getList();
                if (infoList.getListVisualization() == null){
                    if (this.listVisualization == null){
                        this.listVisualization = new ListVisualization(executeAlgorithmController);
                    }
                    else {
                        this.listVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoList.setListVisualization(this.listVisualization);

                }
                listCommand.exeCommand();
                break;
            case "ArrayCommand":
                ArrayCommand arrayCommand = (ArrayCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount);
                InfoArray infoArray = arrayCommand.getArray();
                if (infoArray.getArrayVisualization() == null){
                    if (this.arrayVisualization == null){
                        this.arrayVisualization = new ArrayVisualization(executeAlgorithmController);
                    }
                    else {
                        this.arrayVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoArray.setArrayVisualization(this.arrayVisualization);

                }
                arrayCommand.exeCommand();
                break;
            // OWN TEST TABLE STUFF
            case "TableCommand":
                TableCommand tableCommand = (TableCommand) this.algorithm.getCommandOrder().get(this.currentCommandCount);
                InfoTable infoTable = tableCommand.getTable();
                if (infoTable.getTableVisualization() == null) {
                    if (this.tableVisualization == null) {
                        this.tableVisualization = new TableVisualization(executeAlgorithmController);
                    }
                    else {
                        this.tableVisualization.setExecuteAlgorithmController(executeAlgorithmController);
                    }
                    infoTable.setTableVisualization(this.tableVisualization);
                }
                tableCommand.exeCommand();
                break;
            default:
                System.out.println("Warning! Unknown command type!");
                break;
        }
    }

    // set the selected algorithm, instance it and execute the "executeAlgorithm" function
    // called from the select algorithm controller
    public String setSelectedItem(Object selectedItem) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        ClassLoader classLoader = ParentViewModel.class.getClassLoader();
        try {
            Class classAlgorithm = classLoader.loadClass("Algorithms."+selectedItem);
            Constructor<?> constructorAlgo = classAlgorithm.getConstructor();
            this.algorithm = (AbstractAlgorithm) constructorAlgo.newInstance();
            this.algorithm.executeAlgorithm();
            return this.algorithm.getErrorString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    // sets the visualization type
    // called from select algorithm visualization controller and starts the complete visualization of the command list if chosen
    public void setVisualization(boolean completeVisualization) throws InterruptedException {
        this.currentCommandCount = 0;
        if (completeVisualization){
            startVisualization();
        }
    }

    // sets the used execute algorithm controller
    // resets also all data structure visualizations if they are set (so the visualization can start over again)
    // called by the execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) throws InterruptedException {
        this.executeAlgorithmController = executeAlgorithmController;
        ArrayList<CommandListColumn> commandArrayList = this.algorithm.getCommandListString();
        this.executeAlgorithmController.setCommandListTable(commandArrayList);
        if (this.variableVisualization != null) {
            this.variableVisualization.resetVisualization(executeAlgorithmController);
        }
        if (this.listVisualization != null) {
            this.listVisualization.resetVisualization(executeAlgorithmController);
        }
        if (this.arrayVisualization != null) {
            this.arrayVisualization.resetVisualization(executeAlgorithmController);
        }
        // OWN TEST TABLE STUFF
        if (this.tableVisualization != null) {
            this.tableVisualization.resetVisualization(executeAlgorithmController);
        }
    }

    // resets all data structure visualizations if they are set
    // called by the execute algorithm controller if the visualization should start over again
    // also called by the select algorithm visualization controller if it is changed after a visualization was already represented
    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController){
        if (this.variableVisualization != null){
            this.variableVisualization.resetVisualization(executeAlgorithmController);
        }
        if (this.listVisualization != null){
            this.listVisualization.resetVisualization(executeAlgorithmController);
        }
        if (this.arrayVisualization != null){
            this.arrayVisualization.resetVisualization(executeAlgorithmController);
        }
        // OWN TEST TABLE STUFF
        if (this.tableVisualization != null) {
            this.tableVisualization.resetVisualization(executeAlgorithmController);
        }
    }
}

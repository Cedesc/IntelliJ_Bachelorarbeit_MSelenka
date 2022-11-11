package model;

import commands.experimentCommands.ExperimentCommand;
import commands.treeCommands.TreeCommand;
import controller.ExecuteAlgorithmController;
import controller.SelectAlgorithmController;
import datastructures.*;
import javafx.animation.Transition;
import javafx.scene.Node;
import supportClasses.config.TempConfig;
import visualization.*;
import abstractAlgorithm.AbstractAlgorithm;
import commands.arrayCommands.ArrayCommand;
import commands.listCommands.ListCommand;
import commands.variableCommands.VariableCommand;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import supportClasses.CommandListColumn;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;


public class ParentViewModel extends Application {

    // saves all relevant information:
    // e.g. the selected algorithm, the visualization type, the current command which is executed
    private AbstractAlgorithm algorithm;
    private ExecuteAlgorithmController executeAlgorithmController;
    private VariableVisualization variableVisualization;
    private ArrayVisualization arrayVisualization;
    private ListVisualization listVisualization;
    // OWN TEST STUFF
    private ExperimentVisualization experimentVisualization;
    private TreeVisualization treeVisualization;
    private int currentCommandCount;

    // Start of the Program
    public static void main(String[] args) {
        launch(args);
    }

    // visual start of the program
    // sets the window and the first view: selection of the algorithm
    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("Algorithm Visual Studio");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmView = fxmlLoader.load(Objects.requireNonNull(getClass().
                        getResource("../views/SelectAlgorithmView.fxml")).openStream());
        SelectAlgorithmController selectAlgorithmController = fxmlLoader.getController();
        Scene rootScene = new Scene(selectAlgorithmView, TempConfig.WINDOW_WIDTH, TempConfig.WINDOW_HEIGHT);
        primaryStage.setScene(rootScene);
        primaryStage.setMaximized(TempConfig.MAXIMIZED_WINDOW);
        selectAlgorithmController.setParentViewModel(this);
        primaryStage.show();

        // TODO: 27.09.2022 delete afterwards
        // debugging tool for showing coordinates of clicked point
        // primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
        //   System.out.println("x = " + mouseEvent.getX() + "   y = " + mouseEvent.getY());
        //   System.out.println("");
        // });
    }


    // gets the next command of the algorithm and executes it and the visualization of it in the background
    // called from the execute algorithm controller
    public void exeNextCommand() throws InterruptedException {
        this.executeAlgorithmController.commandListTable.getSelectionModel().select(currentCommandCount + 1);

        exeCommand(true);

        if (currentCommandCount == 0){
            this.executeAlgorithmController.setStepBackButtonVisible();
        }
        if (currentCommandCount == this.algorithm.getCommandOrder().size()-1){
            this.executeAlgorithmController.setStepForwardButtonInvisible();
        }
        this.increaseCurrentCommandCount();
    }

    // inserts the last command, the command reverts itself and changes the visualization in the background
    // called from the execute algorithm controller
    public void exePreviousCommand() throws InterruptedException {
        this.executeAlgorithmController.commandListTable.getSelectionModel().select(currentCommandCount-1);

        exeCommand(false);

        if (currentCommandCount == 1){
            this.executeAlgorithmController.setStepBackButtonInvisible();
        }
        if (currentCommandCount == this.algorithm.getCommandOrder().size()){
            this.executeAlgorithmController.setStepForwardButtonVisible();
        }
        this.currentCommandCount -= 1;
    }

    /**
     * Starts the complete visualization.
     * <p>
     * Called if the complete visualization is selected.
     * <p>
     * Procedure: Executes only the first command. In this function, it doesn't need more, because the next executions
     * are called from the {@link Transition#onFinished}-Events. These events are set in
     * {@link ExecuteAlgorithmController#updateVisualization(Node, Transition)}, where the correct code part is called
     * since {@link ExecuteAlgorithmController#completeVisualization} is true.
     */
    public void startCompleteVisualization() throws InterruptedException {
        // executes the (first) command
        exeCommand(true);
        // increase the currentCommandCount
        this.increaseCurrentCommandCount();
    }

    /**
     * Only used in the complete visualization.
     * <p>
     * The function that is called as an {@link Transition#onFinished}-Event of each transition. In case the current
     * command isn't the last
     * one:
     * <p>
     * 1. The selected string of the (left) vbox of the command strings
     * ({@link ExecuteAlgorithmController#commandListTable}) will be placed on the current command.
     * <p>
     * 2. The current command will be executed with {@link #exeCommand(Boolean)}.
     * <p>
     * 3. The {@link #currentCommandCount} will be increased.
     */
    public void executeNextOnCompleteVisualization() throws InterruptedException {

        // check if the current command is the last one
        if (currentCommandCount == this.algorithm.getCommandOrder().size()){
            // currently, terminateVisualization does nothing noticeable, but if the function will be changed or
            // extended, this is the place where to call it
            this.executeAlgorithmController.terminateVisualization();
        }
        // if the current command isn't the last one, the next command can be executed
        else {
            // select the current command in the vbox on the left
            this.executeAlgorithmController.commandListTable.getSelectionModel().select(currentCommandCount);
            // execute the current command
            exeCommand(true);
            // command iterator moves on
            this.increaseCurrentCommandCount();
        }

    }

    private void exeCommand(Boolean isNextCommand) throws InterruptedException {
        int wantedCommandCount;
        if (isNextCommand) wantedCommandCount = this.currentCommandCount;
        else wantedCommandCount = this.currentCommandCount - 1;

        String currentClassCommand = this.algorithm.getCommandOrder().get(wantedCommandCount).getClass().
                getSuperclass().getSimpleName();
        switch (currentClassCommand) {
            case "VariableCommand" -> {
                VariableCommand variableCommand = (VariableCommand) this.algorithm.getCommandOrder().
                        get(wantedCommandCount);
                exeVariableCommand(variableCommand);
                if (isNextCommand) variableCommand.exeCommand();
                else variableCommand.backCommand();
            }
            case "ListCommand" -> {
                ListCommand listCommand = (ListCommand) this.algorithm.getCommandOrder().
                        get(wantedCommandCount);
                exeListCommand(listCommand);
                if (isNextCommand) listCommand.exeCommand();
                else listCommand.backCommand();
            }
            case "ArrayCommand" -> {
                ArrayCommand arrayCommand = (ArrayCommand) this.algorithm.getCommandOrder().
                        get(wantedCommandCount);
                exeArrayCommand(arrayCommand);
                if (isNextCommand) arrayCommand.exeCommand();
                else arrayCommand.backCommand();
            }
            // OWN TEST STUFF
            case "ExperimentCommand" -> {
                ExperimentCommand experimentCommand = (ExperimentCommand) this.algorithm.getCommandOrder().
                        get(wantedCommandCount);
                exeExperimentCommand(experimentCommand);
                if (isNextCommand) experimentCommand.exeCommand();
                else experimentCommand.backCommand();
            }
            case "TreeCommand" -> {
                TreeCommand treeCommand = (TreeCommand) this.algorithm.getCommandOrder().
                        get(wantedCommandCount);
                exeTreeCommand(treeCommand);
                if (isNextCommand) treeCommand.exeCommand();
                else treeCommand.backCommand();
            }
            default -> System.out.println("Warning! Unknown command type!");
        }
    }

    private void exeExperimentCommand(ExperimentCommand experimentCommand) {
        InfoExperiment infoExperiment = experimentCommand.getExperiment();
        if (infoExperiment.getExperimentVisualization() == null) {
            if (this.experimentVisualization == null) {
                this.experimentVisualization = new ExperimentVisualization(executeAlgorithmController);
            } else {
                this.experimentVisualization.setExecuteAlgorithmController(executeAlgorithmController);
            }
            infoExperiment.setExperimentVisualization(this.experimentVisualization);
        }
    }

    private void exeTreeCommand(TreeCommand treeCommand) {
        InfoTree infoTree = treeCommand.getTree();
        if (infoTree.getTreeVisualization() == null) {
            if (this.treeVisualization == null) {
                this.treeVisualization = new TreeVisualization(executeAlgorithmController);
            } else {
                this.treeVisualization.setExecuteAlgorithmController(executeAlgorithmController);
            }
            infoTree.setTreeVisualization(this.treeVisualization);
        }
    }

    private void exeArrayCommand(ArrayCommand arrayCommand) {
        InfoArray infoArray = arrayCommand.getArray();
        if (infoArray.getArrayVisualization() == null) {
            if (this.arrayVisualization == null) {
                this.arrayVisualization = new ArrayVisualization(executeAlgorithmController);
            } else {
                this.arrayVisualization.setExecuteAlgorithmController(executeAlgorithmController);
            }
            infoArray.setArrayVisualization(this.arrayVisualization);

        }
    }

    private void exeListCommand(ListCommand listCommand) {
        InfoList infoList = listCommand.getList();
        if (infoList.getListVisualization() == null) {
            if (this.listVisualization == null) {
                this.listVisualization = new ListVisualization(executeAlgorithmController);
            } else {
                this.listVisualization.setExecuteAlgorithmController(executeAlgorithmController);
            }
            infoList.setListVisualization(this.listVisualization);

        }
    }

    private void exeVariableCommand(VariableCommand variableCommand) {
        InfoVariable infoVariable = variableCommand.getVariable();
        if (infoVariable.getVariableVisualization() == null) {
            if (this.variableVisualization == null) {
                this.variableVisualization = new VariableVisualization(executeAlgorithmController);
            } else {
                this.variableVisualization.setExecuteAlgorithmController(executeAlgorithmController);
            }
            infoVariable.setVariableVisualization(this.variableVisualization);

        }
    }


    // set the selected algorithm, instance it and execute the "executeAlgorithm" function
    // called from the select algorithm controller
    public String setSelectedItem(Object selectedItem) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        ClassLoader classLoader = ParentViewModel.class.getClassLoader();
        try {
            Class<?> classAlgorithm = classLoader.loadClass("Algorithms."+selectedItem);
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
            startCompleteVisualization();
        }
    }

    // sets the used execute algorithm controller
    // resets also all data structure visualizations if they are set (so the visualization can start over again)
    // called by the execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        ArrayList<CommandListColumn> commandArrayList = this.algorithm.getCommandListString();
        this.executeAlgorithmController.setCommandListTable(commandArrayList);
        resetVisualization(executeAlgorithmController);
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
        // OWN TEST STUFF
        if (this.experimentVisualization != null) {
            this.experimentVisualization.resetVisualization(executeAlgorithmController);
        }
        if (this.treeVisualization != null) {
            this.treeVisualization.resetVisualization(executeAlgorithmController);
        }
    }

    private void increaseCurrentCommandCount() {
        this.currentCommandCount += 1;
    }

}

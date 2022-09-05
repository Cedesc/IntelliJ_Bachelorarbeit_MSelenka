package controller;

import model.ParentViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import supportClasses.CommandListColumn;

public class ExecuteAlgorithmController implements Controller{

    @FXML public Button terminateButton;
    @FXML public VBox algorithmVisualization;
    @FXML public javafx.scene.control.TableView commandListTable;
    @FXML public javafx.scene.control.TableColumn commandListColumn;
    @FXML public ScrollPane scrollPaneCommand;
    @FXML public Button stepBackButton;
    @FXML public Button stepForwardButton;
    @FXML public Button changeVisualizationButton;
    @FXML public Button repeatButton;

    private ParentViewModel parentViewModel;
    private boolean completeVisualization;

    // interaction with the "terminate" button, shuts down the application
    public void terminateButton(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    // interaction with the "change algorithm" button
    // next view is the select algorithm view
    public void changeAlgorithmButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) terminateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmVisualizationView = fxmlLoader.load(getClass().getResource("../views/SelectAlgorithmView.fxml").openStream());
        SelectAlgorithmController selectAlgorithmController = (SelectAlgorithmController) fxmlLoader.getController();
        selectAlgorithmController.setParentViewModel(this.parentViewModel);
        Scene nextScene = new Scene(selectAlgorithmVisualizationView);
        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();
        stage.setScene(nextScene);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
    }

    // interaction with the "step back" button
    // calls the parent to invert the last command of the algorithm
    public void stepBackButton(ActionEvent actionEvent) throws InterruptedException {
        this.parentViewModel.exePreviousCommand();

    }

    // interaction with the "step forward" button
    // calls the parent to execute the next command of the algorithm
    public void stepForwardButton(ActionEvent actionEvent) throws InterruptedException {
        this.parentViewModel.exeNextCommand();
    }


    // set the "change visualization" and "repeat" button to visible
    // called if the visualization is terminated
    public void terminateVisualization(){
        if (!this.completeVisualization){
            // set the "step forward" and "step back" button invisible if the visualization type is "step by step"
            this.stepBackButton.setVisible(false);
            this.stepForwardButton.setVisible(false);
        }
        this.changeVisualizationButton.setVisible(true);
        this.repeatButton.setVisible(true);
    }

    // interaction with the "repeat visualization" button
    // resets all visualization and repeats it with the same parameter
    public void repeatButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        Stage stage = (Stage) terminateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmVisualizationView = fxmlLoader.load(getClass().getResource("../views/ExecuteAlgorithmView.fxml").openStream());
        ExecuteAlgorithmController executeAlgorithmController = (ExecuteAlgorithmController) fxmlLoader.getController();
        executeAlgorithmController.setParentViewModel(this.parentViewModel);
        executeAlgorithmController.setCompleteVisualization(this.completeVisualization);
        Scene nextScene = new Scene(selectAlgorithmVisualizationView);
        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();
        stage.setScene(nextScene);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
        this.parentViewModel.resetVisualization(executeAlgorithmController);
        this.parentViewModel.setVisualization(completeVisualization);
    }

    // interaction with the "change visualization" button
    // next view is the select algorithm visualization view
    public void changeVisualizationButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) terminateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmVisualizationView = fxmlLoader.load(getClass().getResource("../views/SelectAlgorithmVisualizationView.fxml").openStream());
        SelectAlgorithmVisualizationController selectAlgorithmVisualizationController = (SelectAlgorithmVisualizationController) fxmlLoader.getController();
        selectAlgorithmVisualizationController.setParentViewModel(this.parentViewModel);
        Scene nextScene = new Scene(selectAlgorithmVisualizationView);
        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();
        stage.setScene(nextScene);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
        this.parentViewModel.resetVisualization(this);
    }

    // sets the parent model
    public void setParentViewModel(ParentViewModel parentViewModel) throws InterruptedException {
        this.parentViewModel = parentViewModel;
        this.parentViewModel.setExecuteAlgorithmController(this);

    }

    // sets all commands of the algorithm in the table view
    public void setCommandListTable(ArrayList<CommandListColumn> commandListTable) throws InterruptedException {
        this.commandListColumn.setCellValueFactory(new PropertyValueFactory<>("command"));
        this.commandListTable.getItems().addAll(commandListTable);
        this.commandListTable.getSelectionModel().select(0);
    }

    // sets the type of visualization
    // "completeVisualization" is true if the visualization should be executed in one run
    // "completeVisualization" is false if the visualization should be executed step by step
    public void setCompleteVisualization(boolean completeVisualization){
        this.completeVisualization = completeVisualization;
        if (completeVisualization){
            this.stepForwardButton.setVisible(false);
        }

    }

    //  updates the VBox visualization of the algorithm
    public void updateVisualization(Node node) throws InterruptedException {
        String newId = node.getId();
        Boolean edited = false;
        for (int i = 0; i < this.algorithmVisualization.getChildren().size(); i++) {
            if (this.algorithmVisualization.getChildren().get(i).getId().equals(newId)) {
                // executed if the visualization and data structure already exists and only needs to update its node
                this.algorithmVisualization.getChildren().set(i, node);
                edited = true;
                break;
            }

        }
        if (!edited) {
            // executed if a new data structure is added to the visualization
            this.algorithmVisualization.getChildren().add(node);
        }

        // executed if the visualization type is the complete run
        // should pause the visualization after every command
        // !!!!  DOES NOT WORK  !!!
        if (completeVisualization){
            //SequentialTransition seqTransition = new SequentialTransition (new PauseTransition(Duration.millis(1000)));
            //seqTransition.play();
            Stage stage = (Stage) terminateButton.getScene().getWindow();
            stage.show();
            Thread.sleep(100);
            stage.show();
        }
    }

    // sets the "step forward" button to invisible, called if the last command of the algorithm is executed
    public void setStepForwardButtonUnvisible(){
        this.stepForwardButton.setVisible(false);
    }

    // sets the "step back" button to invisible, called it the and set if the first command of the algorithm should be executed
    public void setStepBackButtonUnvisible(){
        this.stepBackButton.setVisible(false);
    }

    // sets the "step forward" button to visible, called if the last command of the algorithm is inverted
    public void setStepForwardButtonVisible(){
        this.stepForwardButton.setVisible(true);
    }

    // sets the "step back" button to visible, called if the first command is executed
    public void setStepBackButtonVisible(){
        this.stepBackButton.setVisible(true);
    }

}


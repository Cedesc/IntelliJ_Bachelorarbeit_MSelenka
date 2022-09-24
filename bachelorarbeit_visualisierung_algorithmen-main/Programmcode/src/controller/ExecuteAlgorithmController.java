package controller;

import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import model.ParentViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import supportClasses.CommandListColumn;
import supportClasses.animations.NullTransition;
import supportClasses.animations.TransitionChain;
import supportClasses.dragging.NodeGestures;
import supportClasses.zooming.SceneGestures;
import supportClasses.zooming.ZoomPane;

public class ExecuteAlgorithmController implements Controller, Initializable {

    @FXML public Button terminateButton;
    @FXML public VBox algVisVBox;
    @FXML public TableView commandListTable;
    @FXML public TableColumn commandListColumn;
    @FXML public ScrollPane scrollPaneCommand;
    @FXML public Button stepBackButton;
    @FXML public Button stepForwardButton;
    @FXML public Button changeVisualizationButton;
    @FXML public Button repeatButton;

    /**
     * Pane only containing the ZoomPane algVisZoomPane.
     */
    @FXML public ScrollPane algVisScrollPane;
    /**
     * ZoomPane, only containing the vbox for the visualization, just for enable zooming.
     */
    @FXML public ZoomPane algVisZoomPane;

    private ParentViewModel parentViewModel;
    private boolean completeVisualization;

    /**
     * TransitionChain for managing the animations and playing one after the other.
     */
    private final TransitionChain transitionChain = new TransitionChain();

    /**
     * Storing the last transition (in the step-by-step visualization) to end it prematurely before the following
     * transition if necessary.
     */
    private Transition lastTransition = new NullTransition();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // create sceneGestures to handle the zooming of algVisZoomPane correctly
        SceneGestures sceneGestures = new SceneGestures(algVisZoomPane, false);
        // add the sceneGestures to algVisScrollPane to listen to mouse wheel inputs on the algVisScrollPane
        algVisScrollPane.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

        // create nodeGestures on the algVisZoomPane for dragging the algVisZoomPane
        NodeGestures nodeGestures = new NodeGestures(algVisZoomPane);
        // add the events to the algVisScrollPane for activating them while aiming in the algVisScrollPane instead of
        // the algVisZoomPane
        algVisScrollPane.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        algVisScrollPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

    }

    // interaction with the "terminate" button, shuts down the application
    public void terminateButton(ActionEvent actionEvent) {
        // resets animation
        this.endAnimationAndDeleteOnFinishedEvents();

        Platform.exit();
        System.exit(0);
    }

    // interaction with the "change algorithm" button
    // next view is the select algorithm view
    public void changeAlgorithmButton(ActionEvent actionEvent) throws IOException {
        // resets animation
        this.endAnimationAndDeleteOnFinishedEvents();

        Stage stage = (Stage) terminateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmVisualizationView = fxmlLoader.load(Objects.requireNonNull(getClass().
                getResource("../views/SelectAlgorithmView.fxml")).openStream());
        SelectAlgorithmController selectAlgorithmController = fxmlLoader.getController();
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
        // resets animation
        this.endAnimationAndDeleteOnFinishedEvents();

        Stage stage = (Stage) terminateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmVisualizationView = fxmlLoader.load(Objects.requireNonNull(getClass().
                getResource("../views/ExecuteAlgorithmView.fxml")).openStream());
        ExecuteAlgorithmController executeAlgorithmController = fxmlLoader.getController();
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
        // resets animation
        this.endAnimationAndDeleteOnFinishedEvents();

        Stage stage = (Stage) terminateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent selectAlgorithmVisualizationView = fxmlLoader.load(Objects.requireNonNull(getClass().
                getResource("../views/SelectAlgorithmVisualizationView.fxml")).openStream());
        SelectAlgorithmVisualizationController selectAlgorithmVisualizationController = fxmlLoader.getController();
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
    public void setParentViewModel(ParentViewModel parentViewModel) {
        this.parentViewModel = parentViewModel;
        this.parentViewModel.setExecuteAlgorithmController(this);
    }

    // sets all commands of the algorithm in the table view
    public void setCommandListTable(ArrayList<CommandListColumn> commandListTable) {
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
    public void updateVisualization(Node node, Transition transition) {
        String newId = node.getId();
        boolean edited = false;
        for (int i = 0; i < this.algVisVBox.getChildren().size(); i++) {
            if (this.algVisVBox.getChildren().get(i).getId().equals(newId)) {
                // executed if the visualization and data structure already exists and only needs to update its node
                this.algVisVBox.getChildren().set(i, node);
                edited = true;
                break;
            }
        }

        if (!edited) {
            // executed if a new data structure is added to the visualization
            this.algVisVBox.getChildren().add(node);
        }

        // if the step-by-step visualization is selected play the animation
        if (! completeVisualization) {
            // skip to the end of the previous animation to avoid errors like further translation to a wrong point
            this.lastTransition.jumpTo("end");
            // start animation of the step
            transition.play();
            // save transition for later
            this.lastTransition = transition;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // TODO: 24.09.2022 pls clean
        // if the complete visualization is selected add the animation to transitionChain
        else {
            transition.setOnFinished(actionEvent -> {
                try {
                    this.parentViewModel.executeNextOnCompleteVisualization();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            transitionChain.addTransition(transition);
            transition.play();
        }
        // TODO: 24.09.2022 pls clean
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    /**
     * Plays the full animation of the complete visualization (like a movie).
     */
    public void playCompleteVisualization() {
        transitionChain.playOneAfterOne();
    }

    // sets the "step forward" button to invisible, called if the last command of the algorithm is executed
    public void setStepForwardButtonInvisible(){
        this.stepForwardButton.setVisible(false);
    }

    // sets the "step back" button to invisible, called it the and set if the first command of the algorithm should be
    // executed
    public void setStepBackButtonInvisible(){
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

    /**
     * Jumps to the end of each animation and delete the event on each transition, that would be triggered at the
     * animations end.
     */
    public void endAnimationAndDeleteOnFinishedEvents() {
        this.transitionChain.endAll();
        this.transitionChain.allSetOnFinishedEvents(actionEvent -> {});
    }

}

package controller;

import model.ParentViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import supportClasses.SaveScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectAlgorithmVisualizationController implements Initializable, Controller {

    @FXML
    private Button helpButton;
    private ParentViewModel parentViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // interaction of the "complete" Button, visualize complete run of the selected algorithm in the execute algorithm view
    public void completeButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent executeAlgorithmView = fxmlLoader.load(getClass().getResource("../views/ExecuteAlgorithmView.fxml").openStream());
        ExecuteAlgorithmController executeAlgorithmController = (ExecuteAlgorithmController) fxmlLoader.getController();
        executeAlgorithmController.setParentViewModel(this.parentViewModel);
        executeAlgorithmController.setCompleteVisualization(true);
        parentViewModel.resetVisualization(executeAlgorithmController);
        Stage stage = (Stage) helpButton.getScene().getWindow();
        Scene nextScene = new Scene(executeAlgorithmView);
        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();
        stage.setScene(nextScene);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
        this.parentViewModel.setVisualization(true);
    }

    // interaction of the "step by step" Button, visualize step by step the selected algorithm in the execute algorithm view
    public void stepByStepButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent executeAlgorithmView = fxmlLoader.load(getClass().getResource("../views/ExecuteAlgorithmView.fxml").openStream());
        ExecuteAlgorithmController executeAlgorithmController = (ExecuteAlgorithmController) fxmlLoader.getController();
        executeAlgorithmController.setParentViewModel(this.parentViewModel);
        executeAlgorithmController.setCompleteVisualization(false);
        parentViewModel.resetVisualization(executeAlgorithmController);
        Stage stage = (Stage) helpButton.getScene().getWindow();
        Scene nextScene = new Scene(executeAlgorithmView);
        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();
        stage.setScene(nextScene);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
        this.parentViewModel.setVisualization(false);
    }

    // interaction of the "help" Button, saves the current scene information in the SaveScene class and view the "help" site
    public void helpButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        SaveScene SaveScene = new SaveScene();
        Stage stage = (Stage) helpButton.getScene().getWindow();
        SaveScene.setStage(stage);
        SaveScene.setScene(stage.getScene());
        SaveScene.setParentViewModel(this.parentViewModel);
        SaveScene.setController(this);
        Node node = (Node) actionEvent.getSource();
        Parent helpView = fxmlLoader.load(getClass().getResource("../views/HelpView.fxml").openStream());
        HelpController helpController = fxmlLoader.getController();
        helpController.setParentViewModel(this.parentViewModel);
        stage.setUserData(SaveScene);

        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();

        Scene sceneNew = new Scene(helpView);
        stage.setScene(sceneNew);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
        stage.show();
    }

    // interaction of the "back" Button, presents the previous view: the selection of an algorithm
    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Stage stage = (Stage) helpButton.getScene().getWindow();
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

    // interaction of the "terminate" Button, shuts down the application
    public void terminateButton(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    // sets the parent model
    public void setParentViewModel(ParentViewModel parentViewModel){
        this.parentViewModel = parentViewModel;
    }



}

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import supportClasses.SaveScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable,Controller  {

    @FXML private Button helpButton;
    @FXML private Text errorText;
    private ParentViewModel parentViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    // sets the error text of the view
    public void setErrorText(String errorText){
        this.errorText.setText(errorText);
    }

    // interaction of the "help" Button, next view is the help-presentation
    public void helpButton(ActionEvent actionEvent) throws IOException {
        SaveScene SaveScene = new SaveScene();
        Stage stage = (Stage) helpButton.getScene().getWindow();
        SaveScene.setStage(stage);
        SaveScene.setScene(stage.getScene());
        SaveScene.setParentViewModel(this.parentViewModel);
        SaveScene.setController(this);
        Node node = (Node) actionEvent.getSource();
        Parent helpView = FXMLLoader.load(getClass().getResource("../views/HelpView.fxml"));
        stage.setUserData(SaveScene);

        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();

        Scene sceneNew = new Scene(helpView);
        stage.setScene(sceneNew);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
        stage.show();
    }

    // interaction of the "terminate" Button, shut down the application
    public void terminateButton(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    // sets the parent model
    public void setParentViewModel(ParentViewModel parentViewModel){
        this.parentViewModel = parentViewModel;
    }

    // interaction of the "change algoithm" Button, next View is the selection of the algoriohtm
    public void changeAlgorithmButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) helpButton.getScene().getWindow();
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
}

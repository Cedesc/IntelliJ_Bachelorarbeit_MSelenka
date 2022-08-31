package controller;

import javafx.fxml.Initializable;
import model.ParentViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import supportClasses.SaveScene;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectAlgorithmController implements Controller, Initializable {

    @FXML private ListView listAlgo;
    @FXML private Button helpButton;
    @FXML private Label notSelectedLabel;
    private List<String> algosStringList;
    private ObservableList observableList = FXCollections.observableArrayList();
    private ParentViewModel parentViewModel;
    private Object selectedItem;


    // creates the table list of all files of the "Algorithms" folder
    public void createList(){

        File folder = new File("src/Algorithms");
        File[] javaFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        algosStringList = new ArrayList<>(javaFiles.length);
        for (int j = 0; j < javaFiles.length; j++){
            this.algosStringList.add(javaFiles[j].getName().replaceFirst("[.][^.]+$", ""));
        }
        observableList.setAll(algosStringList);
        listAlgo.getItems().addAll(observableList);
    }

    // interaction of the "help" Button, next view is the "help" presentation
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

    // interaction of the "terminate" Button, shuts down the application
    public void terminateButton(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }

    // not used in this view
    public void backButton(ActionEvent actionEvent){
    }

    // interaction of the "confirm" Button, confirms the selection of an algorithm
    public void confirmButton(ActionEvent actionEvent) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {

        // if nothing is selected an information will pop up
        this.selectedItem = this.listAlgo.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
            this.notSelectedLabel.setVisible(true);
        }
        else{
            // sends the parent model the selected algorithm
            FXMLLoader fxmlLoader = new FXMLLoader();
            Stage stage = (Stage) helpButton.getScene().getWindow();
            String errorText = this.parentViewModel.setSelectedItem(this.selectedItem);
            // errorText is the return value of the algorithm, if it is empty the algorithm is well defined without errors
            if (!errorText.equals("")){
                Parent errorView = fxmlLoader.load(getClass().getResource("../views/ErrorView.fxml").openStream());
                ErrorController errorController = (ErrorController) fxmlLoader.getController();
                errorController.setParentViewModel(this.parentViewModel);
                errorController.setErrorText(errorText);
                Scene nextScene = new Scene(errorView);
                double prevWidth = stage.getWidth();
                double prevHeight = stage.getHeight();
                stage.setScene(nextScene);
                stage.setHeight(prevHeight);
                stage.setWidth(prevWidth);
            }
            else {
                // algorithm has at least one error an the first error is presented in the error-view
                this.notSelectedLabel.setVisible(false);
                Parent selectAlgorithmVisualizationView = fxmlLoader.load(getClass().getResource("../views/SelectAlgorithmVisualizationView.fxml").openStream());
                SelectAlgorithmVisualizationController selectAlgorithmVisualizationController = (SelectAlgorithmVisualizationController) fxmlLoader.getController();
                selectAlgorithmVisualizationController.setParentViewModel(this.parentViewModel);
                Scene nextScene = new Scene(selectAlgorithmVisualizationView);
                double prevWidth = stage.getWidth();
                double prevHeight = stage.getHeight();
                stage.setScene(nextScene);
                stage.setHeight(prevHeight);
                stage.setWidth(prevWidth);
            }

        }


    }

    // sets the parent model
    public void setParentViewModel(ParentViewModel parentViewModel){
        this.parentViewModel = parentViewModel;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert listAlgo != null : "fx:id=\"listAlgo\" was not injected: check your FXML file 'CustomList.fxml'.";
        createList();
    }

}

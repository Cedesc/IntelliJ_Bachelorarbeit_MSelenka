package controller;

import model.ParentViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import supportClasses.*;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelpController implements Controller, Initializable {

    private ParentViewModel parentViewModel;
    @FXML TextArea helpText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadText()throws FileNotFoundException {
        try {
            helpText.setStyle("-fx-font-size: 1.5em;");
            File file = null;
            file = new File("src\\supportClasses\\helpText.txt");
            Scanner s = new Scanner(file).useDelimiter("<");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    helpText.appendText(s.nextInt() + "\n "); // display the found integer
                } else {
                    helpText.appendText(s.next() + "\n "); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    // interaction of the "back" Button, last view will be presented
    public void backButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        SaveScene saveScene = (SaveScene) stage.getUserData();
        saveScene.getController().setParentViewModel(saveScene.getParentViewModel());
        Scene previousScene = saveScene.getScene();
        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();
        stage.setScene(previousScene);
        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);
    }

    // interaction of the "terminate" Button, shuts down the application
    public void terminateButton(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    // not used in this view
    public void helpButton(ActionEvent actionEvent) {
    }

    // sets the parent model
    public void setParentViewModel(ParentViewModel parentViewModel){
        this.parentViewModel = parentViewModel;
    }
}

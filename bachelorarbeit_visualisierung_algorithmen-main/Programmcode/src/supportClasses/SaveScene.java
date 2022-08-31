package supportClasses;

import controller.Controller;
import model.ParentViewModel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaveScene {
    // saves transition parameter of a changing view

    private Stage stage;
    private Scene scene;
    private ParentViewModel parentViewModel;
    private Controller controller;

    // sets current stage
    public void setStage(Stage stage){
        this.stage = stage;
    }

    // get stage
    public Stage getStage(){
        return this.stage;
    }

    // sets current scene
    public void setScene(Scene scene){
        this.scene = scene;
    }

    // get scene
    public Scene getScene(){
        return this.scene;
    }

    // sets the parent model
    public void setParentViewModel(ParentViewModel parentViewModel){
        this.parentViewModel = parentViewModel;
    }

    // gets parent model
    public ParentViewModel getParentViewModel(){
        return this.parentViewModel;
    }

    // sets current controller
    public void setController(Controller controller){
        this.controller = controller;
    }

    // get controller
    public Controller getController(){
        return this.controller;
    }
}

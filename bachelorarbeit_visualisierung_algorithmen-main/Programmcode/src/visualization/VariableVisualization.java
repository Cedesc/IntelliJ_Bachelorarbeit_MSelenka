package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoVariable;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import supportClasses.types;
import visualization.animationCreation.VariableAnimation;

import java.util.ArrayList;

public class VariableVisualization{

    // saves all current visualization objects and all infoVariables in an array, as well as the used execute algorithm controller
    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<InfoVariable> infoVariables;
    private ArrayList<VBox> layoutVariables = new ArrayList<>();

    /**
     * Instance of seperated class for creating the animations.
     */
    private final VariableAnimation variableAnimation = new VariableAnimation();


    // constructor
    public VariableVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
        infoVariables = new ArrayList<>();
    }

    public void generateNode() {
        generateNode(variableAnimation.getNullTransition());
    }

    // generates an enclosing node object for the visualization
    public void generateNode(Transition transition) {
        VBox node = new VBox();
        node.setId("Variable");
        node.setSpacing(5);
        for (VBox layoutVariable : layoutVariables) {
            node.getChildren().add(layoutVariable);
        }
        this.executeAlgorithmController.updateVisualization(node, transition);

    }

    // sets execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    // creates a visualization for instancing a variable
    public void createVariable(InfoVariable infoVariable, types type) {
        //Label label = new Label("Variable "+(this.infoVariables.size()+1) + " type: "+type.toString());
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        StackPane stackPane = new StackPane();
        Text text = new Text("");
        stackPane.getChildren().addAll(rectangle,text);
        Label label = new Label("Variable "+(this.infoVariables.size()+1) + " type: "+type.toString());
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(label, stackPane);
        this.layoutVariables.add(vBox);
        this.infoVariables.add(infoVariable);
        generateNode();
    }

    // visualization of deleting a variable
    public void deleteVariable(InfoVariable infoVariable) {
        // deletes the variable and the corresponding visualization object from the arrays
        for(int i = 0; i < infoVariables.size(); i++){
            if (infoVariable == infoVariables.get(i)){
                this.layoutVariables.remove(i);
                this.infoVariables.remove(i);
            }
        }
        generateNode();
    }

    // visualization of setting a new value of a variable
    public void setVariable(InfoVariable infoVariable, Object value) {
        if (this.infoVariables.contains(infoVariable)){
            for(int i = 0; i < infoVariables.size(); i++){
                if (infoVariable == infoVariables.get(i)){
                    // sets a new visualization of the variable
                    Rectangle rectangle = new Rectangle();
                    rectangle.setWidth(50);
                    rectangle.setHeight(50);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setStrokeType(StrokeType.OUTSIDE);
                    StackPane stackPane = new StackPane();
                    Text text = new Text(value.toString());
                    stackPane.getChildren().addAll(rectangle,text);
                    Label label = new Label("Variable "+(i+1) + " type: "+this.infoVariables.get(i).getVariableTyp().toString());
                    VBox vBox = new VBox();
                    vBox.setSpacing(5);
                    vBox.getChildren().addAll(label, stackPane);
                    this.layoutVariables.set(i, vBox);
                }
            }
        }
        else{
            this.createVariable(infoVariable, infoVariable.getVariableTyp());
        }
        generateNode();
    }

    // rests the visualization by deleting all instances
    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.infoVariables = new ArrayList<>();
        this.layoutVariables = new ArrayList<>();
        this.executeAlgorithmController = executeAlgorithmController;
    }
}

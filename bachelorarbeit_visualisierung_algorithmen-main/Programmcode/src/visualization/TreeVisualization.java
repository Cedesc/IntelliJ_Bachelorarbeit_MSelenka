package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoTree;
import javafx.animation.Transition;
import javafx.scene.layout.VBox;
import visualization.animationCreation.TreeAnimation;

import java.util.ArrayList;

public class TreeVisualization {

    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutTrees = new ArrayList<>();
    private ArrayList<InfoTree> infoTrees = new ArrayList<>();

    /**
     * Instance of seperated class for creating the animations.
     */
    private final TreeAnimation treeAnimation = new TreeAnimation();

    // constructor
    public TreeVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    /**
     * If no transition is given, a NullTransition will be created for calling the generateNode()-function
     */
    public void generateNode() {
        generateNode(treeAnimation.getNullTransition());
    }

    public void generateNode(Transition transition) {
        VBox node = new VBox();
        node.setId("Tree");
        for (VBox vBox : layoutTrees) {
            node.getChildren().add(vBox);
        }
        this.executeAlgorithmController.updateVisualization(node, transition);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutTrees = new ArrayList<>();
        this.infoTrees = new ArrayList<>();
    }

}

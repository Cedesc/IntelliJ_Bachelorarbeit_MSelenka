package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoTree;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import supportClasses.treeClasses.MyNode;
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
     * Visualization for the "createTree"-Command.
     * @param infoTree InfoTree that will be created in the visualization
     * @param root root node of the tree
     */
    public void createTree(InfoTree infoTree, MyNode root) {

        // test stuff
        HBox hBox = new HBox();
        hBox.setId("Tree" + this.infoTrees.size());
        Rectangle rec = new Rectangle();
        rec.setWidth(50);
        rec.setHeight(50);
        rec.setStroke(Color.BLACK);
        rec.setFill(Color.TRANSPARENT);
        rec.setStrokeType(StrokeType.OUTSIDE);
        Text text = new Text("HelloWorld");
        StackPane stackPane = new StackPane(rec, text);
        stackPane.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(stackPane);

        Text label = new Text("Tree " + (this.infoTrees.size() + "." + 1));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);

        this.infoTrees.add(infoTree);
        this.layoutTrees.add(vBox);


        // TODO: 04.11.2022 create animation

        generateNode();

    }

    /**
     * If no transition is given, a NullTransition will be created for calling the generateNode()-function.
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

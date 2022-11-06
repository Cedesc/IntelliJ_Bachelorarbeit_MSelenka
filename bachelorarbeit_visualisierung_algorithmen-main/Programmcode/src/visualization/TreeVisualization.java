package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoTree;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import visualization.animationCreation.TreeAnimation;

import java.util.ArrayList;

public class TreeVisualization {

    private ExecuteAlgorithmController executeAlgorithmController;
    /**
     * Trees shown in the window.
     */
    private ArrayList<VBox> visualizedTrees = new ArrayList<>();
    /**
     * Data structure behind the shown trees.
     */
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
        this.visualizedTrees.add(vBox);


        // TODO: 04.11.2022 create animation

        updateView();

    }


    /**
     * Draws one entire tree with rectangles and lines.
     * @param tree given tree to be drawn
     * @return Pane including the tree.
     */
    public Pane drawTree(MyTree tree) {

        // pane that will show the drawn tree
        Pane pane = new Pane();
        pane.setStyle("-fx-border-color: blue;");

        drawNode(tree.getRoot(), null, pane);

        return pane;

    }

    private void drawNode(MyNode node, MyNode parent, Pane pane) {

        int nodeSize = 50;

        // edge from the parent to the new node (except for the root node)
        if (parent != null) {
            // used for calculating the center of the node
            int delta = nodeSize / 2;
            Line edge = new Line(parent.xCoordinate + delta, parent.yCoordinate + delta,
                    node.xCoordinate + delta, node.yCoordinate + delta);
            pane.getChildren().add(edge);
            edge.toBack();
        }

        // frame of the node
        Circle circle = new Circle();
        circle.setRadius(nodeSize >> 1);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.OUTSIDE);

        // value of the node
        Text valueText = new Text(node.getValueAsString());

        // combine frame and value
        StackPane visualizedNode = new StackPane();
        visualizedNode.getChildren().addAll(circle, valueText);

        // position the node
        visualizedNode.relocate(node.xCoordinate, node.yCoordinate);


        // add node to the window
        pane.getChildren().add(visualizedNode);

        // draw all children
        MyNode consideredNode = node.leftChild;
        while (consideredNode != null) {
                drawNode(consideredNode, node, pane);
                consideredNode = consideredNode.rightBrother;
            }

    }

    /**
     * If no transition is given, a NullTransition will be created for calling the generateNode()-function.
     */
    public void updateView() {
        updateView(treeAnimation.getNullTransition());
    }

    public void updateView(Transition transition) {

        VBox node = new VBox();
        node.setId("Tree");

        // for each real tree in the background, draw it and add it to the node
        for (InfoTree infoTree : infoTrees) {
            node.getChildren().add(drawTree(infoTree.getTreeContent()));
        }


        this.executeAlgorithmController.updateVisualization(node, transition);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.visualizedTrees = new ArrayList<>();
        this.infoTrees = new ArrayList<>();
    }

}

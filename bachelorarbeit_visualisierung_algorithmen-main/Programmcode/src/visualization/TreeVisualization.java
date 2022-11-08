package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoTree;
import javafx.animation.Transition;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import supportClasses.config.TempConfig;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import visualization.animationCreation.TreeAnimation;

import java.util.ArrayList;
import java.util.Random;

public class TreeVisualization {

    private ExecuteAlgorithmController executeAlgorithmController;
    /**
     * Data structure behind the shown trees.
     */
    private ArrayList<InfoTree> infoTrees = new ArrayList<>();

    /**
     * Instance of seperated class for creating the animations.
     */
    private final TreeAnimation treeAnimation = new TreeAnimation();

    private final int xDistance = TempConfig.NODE_X_DISTANCE;

    private final int yDistance = TempConfig.NODE_Y_DISTANCE;

    private final int nodeSize = TempConfig.NODE_SIZE;

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

        this.infoTrees.add(infoTree);

        // TODO: 04.11.2022 create animation

        updateView();

    }

    public void addLeaf(InfoTree infoTree, MyNode parent, MyNode newLeaf) {

        // TODO: 06.11.2022 create animation

        updateView();
    }


    /**
     * Draws one entire tree with rectangles and lines.
     * @param tree given tree to be drawn
     * @param xDistance distance between nodes on the x-axis
     * @param yDistance distance between nodes on the y-axis
     * @param nodeSize visual size of the node
     * @return Pane including the tree.
     */
    public Pane drawTree(MyTree tree, int xDistance, int yDistance, int nodeSize) {

        // set the correct coordinates for all nodes
        tree.positioning(xDistance, yDistance);

        // pane that will show the drawn tree
        Pane pane = new Pane();
        pane.setStyle("-fx-border-color: blue;");

        drawTree(tree.getRoot(), null, pane, nodeSize);

        return pane;

    }

    /**
     * Recursive helper function for {@link #drawTree(MyTree, int, int, int)}.
     * @param node currently considered node
     * @param parent parent of the node
     * @param pane pane to which all nodes should be added
     * @param nodeSize visual size of the node
     */
    private void drawTree(MyNode node, MyNode parent, Pane pane, int nodeSize) {

        // edge from the parent to the new node (except for the root node)
        if (parent != null) {
            // used for calculating the center of the node
            int delta = nodeSize / 2;
            // edge from the center of the parent to the middle top of the child
            Line edge = new Line(parent.xCoordinate + delta, parent.yCoordinate + delta,
                    node.xCoordinate + delta, node.yCoordinate);
            pane.getChildren().add(edge);
            edge.toBack();
        }

        // create node and add it to the window
        pane.getChildren().add(createVisualizedNode(node, nodeSize));

        // draw all children
        MyNode consideredNode = node.leftChild;
        while (consideredNode != null) {
                drawTree(consideredNode, node, pane, nodeSize);
                consideredNode = consideredNode.rightBrother;
            }

    }

    /**
     * @param node node to be visualized
     * @param nodeSize visual node size
     * @return Visual node with shape and value
     */
    private StackPane createVisualizedNode(MyNode node, int nodeSize) {

        // frame of the node
        Circle circle = new Circle();
        circle.setRadius(nodeSize >> 1);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.OUTSIDE);
        // party
        // double r1 = (double) new Random().nextInt(100) / 100;
        // double r2 = (double) new Random().nextInt(100) / 100;
        // double r3 = (double) new Random().nextInt(100) / 100;
        // circle.setFill(Color.color(r1, r2, r3));

        // value of the node
        Text valueText = new Text(node.getValueAsString());

        // combine frame and Value
        StackPane visualizedNode = new StackPane();
        visualizedNode.getChildren().addAll(circle, valueText);

        // position the node
        visualizedNode.relocate(node.xCoordinate, node.yCoordinate);

        return visualizedNode;
    }

    /**
     * If no transition is given, a NullTransition will be created for calling the generateNode()-function.
     */
    public void updateView() {
        updateView(treeAnimation.getNullTransition());
    }

    /**
     * @param transition animation to be played
     */
    public void updateView(Transition transition) {

        VBox node = new VBox();
        node.setId("Tree");

        // for each real tree in the background, draw it and add it to the node
        for (InfoTree infoTree : infoTrees) {
            node.getChildren().add(drawTree(infoTree.getTreeContent(), this.xDistance, this.yDistance, this.nodeSize));
        }


        this.executeAlgorithmController.updateVisualization(node, transition);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.infoTrees = new ArrayList<>();
    }

}

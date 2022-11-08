package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoTree;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
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

    private final boolean circlesInsteadOfRectangles = TempConfig.CIRCLES_INSTEAD_OF_RECTANGLES;

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
     * @param treeName name of the tree (usually "Tree i")
     * @param tree given tree to be drawn
     * @param xDistance distance between nodes on the x-axis
     * @param yDistance distance between nodes on the y-axis
     * @param nodeSize visual size of the node
     * @return VBox including the name of the tree and the visualized tree itself.
     */
    public VBox drawTree(String treeName, MyTree tree, int xDistance, int yDistance, int nodeSize) {

        // set the correct coordinates for all nodes
        tree.positioning(xDistance, yDistance);

        // pane that will show the drawn tree
        Pane pane = new Pane();

        // draw nodes and edges
        drawTree(tree.getRoot(), null, pane, nodeSize);

        VBox treeWithName = new VBox(new Text(treeName), pane);
        treeWithName.setPadding(new Insets(20));

        return treeWithName;

    }

    /**
     * Recursive helper function for {@link #drawTree(String, MyTree, int, int, int)}.
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
        Shape frame;

        if (this.circlesInsteadOfRectangles) {
            Circle circle = new Circle();
            // radius is the half of the node size
            circle.setRadius(nodeSize >> 1);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(StrokeType.OUTSIDE);
            frame = circle;
        }
        else {
            Rectangle rec = new Rectangle();
            rec.setWidth(nodeSize);
            rec.setHeight(nodeSize);
            rec.setFill(Color.WHITE);
            rec.setStroke(Color.BLACK);
            rec.setStrokeType(StrokeType.OUTSIDE);
            frame = rec;
        }

        // party
//         double r1 = (double) new Random().nextInt(100) / 100;
//         double r2 = (double) new Random().nextInt(100) / 100;
//         double r3 = (double) new Random().nextInt(100) / 100;
//         frame.setFill(Color.color(r1, r2, r3));

        // value of the node
        Text valueText = new Text(node.getValueAsString());

        // index of the node
        Text indexText = new Text(node.getIndexAsString());
        indexText.setFont(Font.font(9));

        // combine frame with index
        StackPane frameWithIndex = new StackPane(frame, indexText);
        frameWithIndex.setAlignment(Pos.BOTTOM_CENTER);

        // combine frame and index with value
        StackPane visualizedNode = new StackPane(frameWithIndex, valueText);

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

        VBox allTrees = new VBox();
        allTrees.setId("Tree");

        // for each real tree in the background, draw it and add it to the node
        for (int i = 0 ; i < infoTrees.size() ; i++) {
            // name of the tree
            String treeName = "Tree " + i;
            // add the tree with its name to the node
            allTrees.getChildren().add(
                    drawTree(treeName, infoTrees.get(i).getTreeContent(), this.xDistance, this.yDistance, this.nodeSize));
        }

        this.executeAlgorithmController.updateVisualization(allTrees, transition);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.infoTrees = new ArrayList<>();
    }

}

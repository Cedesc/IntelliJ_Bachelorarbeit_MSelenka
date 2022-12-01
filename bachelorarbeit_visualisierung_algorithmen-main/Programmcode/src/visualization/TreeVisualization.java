package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoTree;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import supportClasses.config.ConfigReader;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import visualization.animationCreation.TreeAnimation;

import java.util.ArrayList;
import java.util.Objects;
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

    private final int xDistance = ConfigReader.getNodeXDistance();

    private final int yDistance = ConfigReader.getNodeYDistance();

    private final int nodeSize = ConfigReader.getNodeSize();


    // constructor
    public TreeVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    /**
     * Visualization for the "createTree"-Command.
     * @param infoTree InfoTree that will be created in the visualization
     */
    public void createTree(InfoTree infoTree) {

        this.infoTrees.add(infoTree);


        // calculate correct index of the created tree in infoTrees
        int indexOfTreeInInfoTrees = this.infoTrees.indexOf(infoTree);

        // draw all trees
        VBox allTrees = drawAllTrees();

        // get vbox of the correct tree
        VBox vBoxOfCreatedTree = (VBox) allTrees.getChildren().get(indexOfTreeInInfoTrees);
        // create the animation with the vBox of the new created tree
        Transition transition = treeAnimation.forCreateTree(vBoxOfCreatedTree);

        // update the current view with the drawn trees and the animation
        updateView(allTrees, transition);

    }

    public void deleteTree(InfoTree infoTree) {
        // remove the tree
        this.infoTrees.remove(infoTree);

        // TODO: 23.11.2022 create animation

        // update the current view with the drawn trees and the animation
        updateView();
    }

    public void addLeaf(InfoTree infoTree, MyNode parent, MyNode newLeaf) {

        // draw all trees
        VBox allTrees = drawAllTrees();

        // find visualized node in the drawn trees
        StackPane visualizedNode = findVisualizedNode(infoTree, allTrees, newLeaf.getIndex());

        // find visualized edge to the new node in the drawn trees
        Line visualizedEdge = findVisualizedEdge(infoTree, allTrees, parent.getIndex(), newLeaf.getIndex());

        // create the animation
        Transition transition = treeAnimation.forAddLeaf(visualizedNode, visualizedEdge);

        // update the current view with the drawn trees and the animation
        updateView(allTrees, transition);

    }

    public void changeValue(InfoTree infoTree, MyNode node, Object oldValue) {

        // draw all trees
        VBox allTrees = drawAllTrees();

        // find visualized node in the drawn trees
        StackPane visualizedNode = findVisualizedNode(infoTree, allTrees, node.getIndex());

        // get the value text of the node
        Text valueText = (Text) visualizedNode.getChildren().get(1);

        // create the animation
        Transition transition = treeAnimation.forChangeValue(visualizedNode, valueText, oldValue);

        // update the current view with the drawn trees and the animation
        updateView(allTrees, transition);

    }

    public void getIndexByValue(InfoTree infoTree, int index) {

        // if no node has the passed value, no animation is shown
        if (index == -1) {
            updateView();
        }
        else {
            // draw all trees
            VBox allTrees = drawAllTrees();

            // find visualized node in the drawn trees
            StackPane visualizedNode = findVisualizedNode(infoTree, allTrees, index);

            // create the animation
            Transition transition = treeAnimation.forGetIndexByValue(visualizedNode);

            // update the current view with the drawn trees and the animation
            updateView(allTrees, transition);
        }

    }

    public void getNodeByIndex(InfoTree infoTree, MyNode searchedNode) {

        // if no node has the passed index, no animation is shown
        if (searchedNode == null) {
            updateView();
        }
        else {
            // draw all trees
            VBox allTrees = drawAllTrees();

            // find visualized node in the drawn trees
            StackPane visualizedNode = findVisualizedNode(infoTree, allTrees, searchedNode.getIndex());

            // create the animation
            Transition transition = treeAnimation.forGetNodeByIndex(visualizedNode);

            // update the current view with the drawn trees and the animation
            updateView(allTrees, transition);
        }

    }

    public void deleteLeaf() {
        // TODO: 21.11.2022 create animation

        // update the current view with the drawn trees and the animation
        updateView();
    }

    public void changeParent() {
        // TODO: 21.11.2022 create animation

        // update the current view with the drawn trees and the animation
        updateView();
    }



    // Helper Functions

    private StackPane findVisualizedNode(InfoTree infoTree, VBox allTrees, int nodeIndex) {
        // calculate correct index of the created tree in infoTrees
        int indexOfTreeInInfoTrees = this.infoTrees.indexOf(infoTree);

        // get vbox of the correct tree
        VBox vBoxOfCreatedTree = (VBox) allTrees.getChildren().get(indexOfTreeInInfoTrees);
        // get the pane ; each VBox has the tree name as the 0. element and the pane with the tree as the 1. element
        Pane paneOfCreatedTree = (Pane) vBoxOfCreatedTree.getChildren().get(1);

        // get the elements (all visualized nodes and edges) of the pane
        ObservableList<Node> visualizedElements = paneOfCreatedTree.getChildren();
        // determine the id to be searched for
        String idToBeSearchedFor = "node " + nodeIndex;
        // get the leaf as visualized node by its id
        StackPane visualizedNode = (StackPane) getByID(visualizedElements, idToBeSearchedFor);
        assert visualizedNode != null;

        return visualizedNode;
    }

    private Line findVisualizedEdge(InfoTree infoTree, VBox allTrees, int parentNodeIndex, int childNodeIndex) {
        // calculate correct index of the created tree in infoTrees
        int indexOfTreeInInfoTrees = this.infoTrees.indexOf(infoTree);

        // get vbox of the correct tree
        VBox vBoxOfCreatedTree = (VBox) allTrees.getChildren().get(indexOfTreeInInfoTrees);
        // get the pane ; each VBox has the tree name as the 0. element and the pane with the tree as the 1. element
        Pane paneOfCreatedTree = (Pane) vBoxOfCreatedTree.getChildren().get(1);

        // get the elements (all visualized nodes and edges) of the pane
        ObservableList<Node> visualizedElements = paneOfCreatedTree.getChildren();
        // determine the id to be searched for
        String idToBeSearchedFor = "edge from node " + parentNodeIndex + " to node " + childNodeIndex;
        // get the leaf as visualized node by its id
        Line visualizedEdge = (Line) getByID(visualizedElements, idToBeSearchedFor);
        assert visualizedEdge != null;

        return visualizedEdge;
    }


    /**
     * @param list list to be searched
     * @param id id to be searched for
     * @return The found node if any, null otherwise.
     */
    private Node getByID(ObservableList<Node> list, String id) {
        for (Node node : list) {
            if (Objects.equals(node.getId(), id)) {
                return node;
            }
        }
        System.out.println("TreeVisualization.getByID : Warning! No node found!");
        return null;
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
    private VBox drawTree(String treeName, MyTree tree, int xDistance, int yDistance, int nodeSize) {

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
            Line edge = new Line(parent.getX() + delta, parent.getY() + delta,
                    node.getX() + delta, node.getY());
            edge.setId("edge from node " + parent.getIndexAsString() + " to node " + node.getIndexAsString());
            pane.getChildren().add(edge);
            // push to the background
            edge.toBack();
        }

        // create node
        StackPane visualizedNode = createVisualizedNode(node, nodeSize);
        visualizedNode.setId("node " + node.getIndexAsString());
        // add the node to the window
        pane.getChildren().add(visualizedNode);

        // draw all children
        MyNode consideredNode = node.getLeftChild();
        while (consideredNode != null) {
                drawTree(consideredNode, node, pane, nodeSize);
                consideredNode = consideredNode.getRightBrother();
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

        Circle circle = new Circle();
        // radius is the half of the node size
        circle.setRadius(nodeSize >> 1);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.OUTSIDE);
        frame = circle;

        if (false) {
            // party
            double r1 = (double) new Random().nextInt(100) / 100;
            double r2 = (double) new Random().nextInt(100) / 100;
            double r3 = (double) new Random().nextInt(100) / 100;
            frame.setFill(Color.color(r1, r2, r3));
        }

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
        visualizedNode.relocate(node.getX(), node.getY());

        return visualizedNode;
    }

    /**
     * @return VBox including all trees of infoTrees visualized.
     */
    private VBox drawAllTrees() {
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

        return allTrees;
    }

    /**
     * If neither a transition nor the trees are passed, all trees will be drawn and a NullTransition will be created
     * for calling the updateView()-function.
     */
    private void updateView() {
        updateView(drawAllTrees(), treeAnimation.getNullTransition());
    }

    /**
     * @param allTrees VBox of all visualized trees
     * @param transition animation to be played
     */
    private void updateView(VBox allTrees, Transition transition) {
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

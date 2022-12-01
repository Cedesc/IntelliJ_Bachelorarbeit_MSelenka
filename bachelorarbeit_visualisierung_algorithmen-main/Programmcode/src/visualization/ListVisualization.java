package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoList;
import datastructures.Variable;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import visualization.animationCreation.ListAnimation;

import java.util.ArrayList;

public class ListVisualization{

    // saves all relevant data
    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutList = new ArrayList<>();
    private ArrayList<InfoList> infoLists = new ArrayList<>();

    /**
     * Instance of seperated class for creating the animations.
     */
    private final ListAnimation listAnimation = new ListAnimation();


    // constructor
    public ListVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void generateNode() {
        generateNode(listAnimation.getNullTransition());
    }

    // generates a node object which contains all current used lists
    public void generateNode(Transition transition) {
        VBox node = new VBox();
        node.setId("List");
        for (VBox vBox : layoutList) {
            node.getChildren().add(vBox);
        }
        this.executeAlgorithmController.updateVisualization(node, transition);

    }

    // resets all relative data of the visualization
    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutList = new ArrayList<>();
        this.infoLists = new ArrayList<>();
    }

    // sets a new execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    // visualization of setting an element of the list with a new value
    public void setElement() {
    }

    // visualization of deleting a list
    public void deleteList(InfoList infoList) {
        for (int i = 0; i < this.infoLists.size(); i++){
            if (this.infoLists.get(i).equals(infoList)){
                this.infoLists.remove(i);
                this.layoutList.remove(i);
            }
        }

    }

    // visualization of inserting an element in the list
    public void insertElement(InfoList infoList) throws InterruptedException {
        int sizeOfLayoutList = layoutList.size();
        for (int i = 0; i < sizeOfLayoutList; i++){
            if (this.infoLists.get(i).equals(infoList)){

                Rectangle rectangleValue = new Rectangle();
                rectangleValue.setWidth(50);
                rectangleValue.setHeight(50);
                rectangleValue.setStroke(Color.BLACK);
                rectangleValue.setFill(Color.TRANSPARENT);
                rectangleValue.setStrokeType(StrokeType.OUTSIDE);
                Rectangle rectanglePointer = new Rectangle();
                rectanglePointer.setWidth(20);
                rectanglePointer.setHeight(50);
                rectanglePointer.setStroke(Color.BLACK);
                rectanglePointer.setFill(Color.TRANSPARENT);
                rectanglePointer.setStrokeType(StrokeType.OUTSIDE);
                Circle circle = new Circle(rectanglePointer.getX()+10, rectanglePointer.getY()+25, 3);
                circle.setFill(Color.BLACK);
                StackPane pointerStackpane = new StackPane();
                pointerStackpane.getChildren().addAll(circle,rectanglePointer);
                Line line = new Line(10, 26, 25, 26);
                line.setStrokeWidth(2);
                Line lineUp = new Line(25, 26, 17, 21);
                lineUp.setStrokeWidth(2);
                Line lineDown = new Line(25,26, 17, 31);
                lineDown.setStrokeWidth(2);
                Group pointerGroup = new Group();
                pointerGroup.getChildren().addAll(pointerStackpane,line, lineUp, lineDown);
                StackPane valueStackPane = new StackPane();
                valueStackPane.getChildren().addAll(rectangleValue);

                HBox firstElement = new HBox();
                firstElement.setId("0");
                firstElement.getChildren().addAll(valueStackPane,pointerGroup);
                HBox listElementsHBox = new HBox();
                listElementsHBox.setId("Elements");
                listElementsHBox.getChildren().add(firstElement);
                Label label = new Label("List "+(this.infoLists.size()));

                VBox vBox = new VBox();
                vBox.setId("Content");
                vBox.setSpacing(5);
                vBox.getChildren().addAll(label, listElementsHBox);

                this.layoutList.add(vBox);
            }
        }

        generateNode();
    }

    // visualization of deleting an element of the list
    public void deleteElement(InfoList infoList, Variable variable) throws InterruptedException {
        int k;
        for (int i = 0; i < this.layoutList.size(); i++){
            if (this.infoLists.get(i).equals(infoList)){
                InfoList currentList = this.infoLists.get(i);
                int index = currentList.getIndex(variable);
                String indexString = String.valueOf(index);
                System.out.println(indexString);
                //FilteredList content = this.layoutList.get(i).getChildren().filtered(s -> s.getId() == "Elements").filtered(s -> s.getId() == indexString);
                this.layoutList.get(i).getChildren().remove(index);

                for (k = Integer.parseInt(indexString); k < this.layoutList.get(i).getChildren().size(); k++){
                    System.out.println(k);
                    this.layoutList.get(i).getChildren().get(k).setId(String.valueOf(k));
                }
                break;
            }
        }

        generateNode();
    }

    // visualization of creating a list, creates the first element without a value
    public void createList(InfoList infoList) {
        this.infoLists.add(infoList);

        Rectangle rectangleValue = new Rectangle();
        rectangleValue.setWidth(50);
        rectangleValue.setHeight(50);
        rectangleValue.setStroke(Color.BLACK);
        rectangleValue.setFill(Color.TRANSPARENT);
        rectangleValue.setStrokeType(StrokeType.OUTSIDE);
        Rectangle rectanglePointer = new Rectangle();
        rectanglePointer.setWidth(20);
        rectanglePointer.setHeight(50);
        rectanglePointer.setStroke(Color.BLACK);
        rectanglePointer.setFill(Color.TRANSPARENT);
        rectanglePointer.setStrokeType(StrokeType.OUTSIDE);
        Circle circle = new Circle(rectanglePointer.getX()+10, rectanglePointer.getY()+25, 3);
        circle.setFill(Color.BLACK);
        StackPane pointerStackpane = new StackPane();
        pointerStackpane.getChildren().addAll(circle,rectanglePointer);
        Line line = new Line(10, 26, 25, 26);
        line.setStrokeWidth(2);
        Line lineUp = new Line(25, 26, 17, 21);
        lineUp.setStrokeWidth(2);
        Line lineDown = new Line(25,26, 17, 31);
        lineDown.setStrokeWidth(2);
        Group pointerGroup = new Group();
        pointerGroup.getChildren().addAll(pointerStackpane,line, lineUp, lineDown);
        StackPane valueStackPane = new StackPane();
        valueStackPane.getChildren().addAll(rectangleValue);

        HBox firstElement = new HBox();
        firstElement.setId("0");
        firstElement.getChildren().addAll(valueStackPane,pointerGroup);
        HBox listElementsHBox = new HBox();
        listElementsHBox.setId("Elements");
        listElementsHBox.getChildren().add(firstElement);
        Label label = new Label("List "+(this.infoLists.size()));

        VBox vBox = new VBox();
        vBox.setId("Content");
        vBox.setSpacing(5);
        vBox.getChildren().addAll(label, listElementsHBox);

        this.layoutList.add(vBox);

        generateNode();
    }

    // visualization of recreating the list with values
    // called by the deleteList command, when it inverts itself
    public void createListWithValues() {

    }
}

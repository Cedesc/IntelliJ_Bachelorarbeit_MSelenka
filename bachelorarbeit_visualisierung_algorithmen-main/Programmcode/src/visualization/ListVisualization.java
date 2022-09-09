package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoList;
import datastructures.Variable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class ListVisualization{

    // saves all relevant data
    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutList = new ArrayList<VBox>();
    private ArrayList<InfoList> infoLists = new ArrayList<InfoList>();

    // constructor
    public ListVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    // generates a node object which contains all current used lists
    public void generateNode() throws InterruptedException {
        Node node = new VBox();
        node.setId("List");
        for (int i = 0; i < layoutList.size(); i++){
            ((VBox) node).getChildren().add(layoutList.get(i));
        }
        this.executeAlgorithmController.updateVisualization(node);

    }

    // resets all relative data of the visualization
    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutList = new ArrayList<VBox>();
        this.infoLists = new ArrayList<InfoList>();
    }

    // sets a new execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    // visualization of setting an element of the list with a new value
    public void setElement(InfoList infoList, Variable variable, Object object) {
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
    // TODO: 08.09.2022 ListVisualization.insertElement mit createArray und insertElement vergleichen
    // TODO: 09.09.2022 Das tatsächliche Einfügen des Elements fehlt
    public void insertElement(InfoList infoList, Variable variable, int index) throws InterruptedException {
        int sizeOfLayoutList = layoutList.size();
        for (int i = 0; i < sizeOfLayoutList; i++){
            if (this.infoLists.get(i).equals(infoList)){

                // Rechteck (für Wert) erstellen
                Rectangle rectangleValue = new Rectangle();
                rectangleValue.setWidth(50);
                rectangleValue.setHeight(50);
                rectangleValue.setStroke(Color.BLACK);
                rectangleValue.setFill(Color.TRANSPARENT);
                rectangleValue.setStrokeType(StrokeType.OUTSIDE);
                // Rechteck (für Pfeil) erstellen
                Rectangle rectanglePointer = new Rectangle();
                rectanglePointer.setWidth(20);
                rectanglePointer.setHeight(50);
                rectanglePointer.setStroke(Color.BLACK);
                rectanglePointer.setFill(Color.TRANSPARENT);
                rectanglePointer.setStrokeType(StrokeType.OUTSIDE);
                // Kreis (Pfeilursprung) erstellen
                Circle circle = new Circle(rectanglePointer.getX()+10, rectanglePointer.getY()+25, 3);
                circle.setFill(Color.BLACK);
                // Stackpane aus Rechteck (für Pfeil) und Kreis (Pfeilursprung) erstellen
                StackPane pointerStackpane = new StackPane();
                pointerStackpane.getChildren().addAll(circle,rectanglePointer);
                // Pfeilspitze erstellen
                Line line = new Line(10, 26, 25, 26);
                line.setStrokeWidth(2);
                Line lineUp = new Line(25, 26, 17, 21);
                lineUp.setStrokeWidth(2);
                Line lineDown = new Line(25,26, 17, 31);
                lineDown.setStrokeWidth(2);
                // Gruppe aus PointerStackpane (Rechteck mit Kreis in der Mitte) und Pfeilspitze erstellen
                Group pointerGroup = new Group();
                pointerGroup.getChildren().addAll(pointerStackpane,line, lineUp, lineDown);
                // Stackpane aus Rechteck (für Wert) erstellen
                StackPane valueStackPane = new StackPane();
                valueStackPane.getChildren().addAll(rectangleValue);

                // Eine hbox aus dem Rechteck für den Wert und zuvor erstellten Gruppe erstellen (quasi alles zuvor).
                // Diese hbox umfasst nur ein Element.
                HBox firstElement = new HBox();
                firstElement.setId("0");
                firstElement.getChildren().addAll(valueStackPane,pointerGroup);
                // Eine hbox mit der hbox von zuvor erstellen.
                // Diese hbox soll alle Elemente der Liste umfassen.
                HBox listElementsHBox = new HBox();
                listElementsHBox.setId("Elements");
                listElementsHBox.getChildren().add(firstElement);
                // Label für Liste erstellen
                Label label = new Label("Liste "+(this.infoLists.size()));

                // Eine vbox mit der zuvor erstellten hbox für die Liste erstellen.
                // Diese vbox soll alle listen umfassen.
                VBox vBox = new VBox();
                vBox.setId("Content");
                vBox.setSpacing(5);
                vBox.getChildren().addAll(label, listElementsHBox);

                // Die letztendliche vbox zur layoutList hinzufügen
                this.layoutList.add(vBox);
            }
        }

        generateNode();
    }

    // visualization of deleting an element of the list
    public void deleteElement(InfoList infoList, Variable variable) throws InterruptedException {
        int k = 0;
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
    public void createList(InfoList infoList) throws InterruptedException {
        // todo warum das?
        this.infoLists.add(infoList);

        // Rechteck (für Wert) erstellen
        Rectangle rectangleValue = new Rectangle();
        rectangleValue.setWidth(50);
        rectangleValue.setHeight(50);
        rectangleValue.setStroke(Color.BLACK);
        rectangleValue.setFill(Color.TRANSPARENT);
        rectangleValue.setStrokeType(StrokeType.OUTSIDE);
        // Rechteck (für Pfeil) erstellen
        Rectangle rectanglePointer = new Rectangle();
        rectanglePointer.setWidth(20);
        rectanglePointer.setHeight(50);
        rectanglePointer.setStroke(Color.BLACK);
        rectanglePointer.setFill(Color.TRANSPARENT);
        rectanglePointer.setStrokeType(StrokeType.OUTSIDE);
        // Kreis (Pfeilursprung) erstellen
        Circle circle = new Circle(rectanglePointer.getX()+10, rectanglePointer.getY()+25, 3);
        circle.setFill(Color.BLACK);
        // Stackpane aus Rechteck (für Pfeil) und Kreis (Pfeilursprung) erstellen
        StackPane pointerStackpane = new StackPane();
        pointerStackpane.getChildren().addAll(circle,rectanglePointer);
        // Pfeilspitze erstellen
        Line line = new Line(10, 26, 25, 26);
        line.setStrokeWidth(2);
        Line lineUp = new Line(25, 26, 17, 21);
        lineUp.setStrokeWidth(2);
        Line lineDown = new Line(25,26, 17, 31);
        lineDown.setStrokeWidth(2);
        // Gruppe aus PointerStackpane (Rechteck mit Kreis in der Mitte) und Pfeilspitze erstellen
        Group pointerGroup = new Group();
        pointerGroup.getChildren().addAll(pointerStackpane,line, lineUp, lineDown);
        // Stackpane aus Rechteck (für Wert) erstellen
        StackPane valueStackPane = new StackPane();
        valueStackPane.getChildren().addAll(rectangleValue);

        // Eine hbox aus dem Rechteck für den Wert und zuvor erstellten Gruppe erstellen (quasi alles zuvor).
        // Diese hbox umfasst nur ein Element.
        HBox firstElement = new HBox();
        firstElement.setId("0");
        firstElement.getChildren().addAll(valueStackPane,pointerGroup);
        // Eine hbox mit der hbox von zuvor erstellen.
        // Diese hbox soll alle Elemente der Liste umfassen.
        HBox listElementsHBox = new HBox();
        listElementsHBox.setId("Elements");
        listElementsHBox.getChildren().add(firstElement);
        // Label für Liste erstellen
        Label label = new Label("Liste "+(this.infoLists.size()));

        // Eine vbox mit der zuvor erstellten hbox für die Liste erstellen.
        // Diese vbox enthält zwei Elemente: das Label und die Liste.
        VBox vBox = new VBox();
        vBox.setId("Content");
        vBox.setSpacing(5);
        vBox.getChildren().addAll(label, listElementsHBox);

        // Die letztendliche vbox zur layoutList hinzufügen
        this.layoutList.add(vBox);

        generateNode();
    }

    // visualization of recreating the list with values
    // called by the deleteList command, when it inverts itself
    public void createListWithValues(InfoList infoList, ArrayList<Variable> list) {

    }
}

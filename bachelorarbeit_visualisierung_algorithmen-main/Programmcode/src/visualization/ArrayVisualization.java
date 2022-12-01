package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoArray;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import visualization.animationCreation.ArrayAnimation;

import java.util.ArrayList;

public class ArrayVisualization {

    // saves all current using infoArrays and their visualizations also as an array, as well as the used execute
    // algorithm controller
    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutArray = new ArrayList<>();
    private ArrayList<InfoArray> infoArrays = new ArrayList<>();

    /**
     * Instance of seperated class for creating the animations.
     */
    private final ArrayAnimation arrayAnimation = new ArrayAnimation();


    // constructor
    public ArrayVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    // creates an empty array with a given length
    public void createArray(InfoArray infoArray,int length) {
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+ this.infoArrays.size());

        for (int i = 0; i < length; i++){
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(i +" ");
            indexText.setId("textIndex"+this.infoArrays.size()+"."+ i);
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            Text value = new Text("");
            value.setId("textValue"+this.infoArrays.size()+"."+ i);
            StackPane stackPane2 = new StackPane(stackPane, value);
            stackPane2.setId("stackPane"+this.infoArrays.size()+"."+ i);
            hBox.getChildren().add(stackPane2);
        }

        Text label = new Text("Array "+(this.infoArrays.size()+1));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);

        this.infoArrays.add(infoArray);

        this.layoutArray.add(vBox);

        generateNode();
    }

    // visualization of creating an array with given values and length
    public void createArrayWithValues(InfoArray infoArray, int length, Object[] values) {
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+ this.infoArrays.size());
        for (int i = 0; i < length; i++){
            // creates all array boxes
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(i +" ");
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            if (i < values.length){
                // set the boxes with the given values in the visualization
                Text value = new Text(String.valueOf(values[i]));
                value.setId("textValue"+ this.infoArrays.size() +"."+ i);
                StackPane stackPane2 = new StackPane(stackPane, value);
                stackPane2.setId("stackPane"+ this.infoArrays.size() +"."+ i);

                hBox.getChildren().add(stackPane2);
            }
            else{
                // the rest oof the array will be empty, but the visualization objects will  be created
                Text value = new Text("");
                value.setId("textValue"+ this.infoArrays.size() +"."+ i);
                StackPane stackPane2 = new StackPane(stackPane, value);
                stackPane2.setId("stackPane"+ this.infoArrays.size() +"."+ i);

                hBox.getChildren().add(stackPane2);
            }


        }
        // creates the header and enclosing object
        Text label = new Text("Array "+(this.infoArrays.size()+1));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);
        this.infoArrays.add(infoArray);
        this.layoutArray.add(vBox);
        generateNode();

    }

    public void getIndexByValue() {
        generateNode();
    }

    public void getValueByIndex() {
        generateNode();
    }

    public void getSize() {
        generateNode();
    }

    // swaps the values of both array elements of index1 and index2
    public void swapElements(InfoArray infoArray, int index1, int index2) throws InterruptedException{
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> vBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);

        // get visualization text of index 1
        FilteredList<Node> element1 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexArray +"."+ index1));
        StackPane stackPaneElement1 = (StackPane) element1.get(0);
        ObservableList<Node> stackPaneChildren1 = stackPaneElement1.getChildren();
        Text textValue = (Text) stackPaneChildren1.get(1);
        String memValue1 = textValue.getText();

        // get visualization text of index 2
        FilteredList<Node> element2 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexArray +"."+ index2));
        StackPane stackPaneElement2 = (StackPane) element2.get(0);
        ObservableList<Node> stackPaneChildren2 = stackPaneElement2.getChildren();
        Text textValue2 = (Text) stackPaneChildren2.get(1);
        String memValue2 = textValue2.getText();

        // set both texts
        textValue.setText(memValue2);
        textValue2.setText(memValue1);
        generateNode();
    }

    // visualization of deleting an array element by a given index
    public void deleteElement(InfoArray infoArray, int index) throws InterruptedException{
        // get visualization text of the index
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> observableVBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) observableVBoxChildren.get(1);
        FilteredList<Node> elementStackPaneFilteredList = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexArray +"."+ index));
        StackPane stackPaneElement = (StackPane) elementStackPaneFilteredList.get(0);
        ObservableList<Node> stackPaneElementChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneElementChildren.get(1);
        textValue.setText("");

        // iterate over the rest of the element and move all elements one position backwards
        for (int i = index+1; i < this.infoArrays.get(indexArray).getSize()+1; i++){
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            FilteredList<Node> elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+
                    indexArray +"."+ finalI));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            Text textValueNext = (Text) stackPaneChildrenNext.get(1);
            textValue.setText(textValueNext.getText());
            textValueNext.setText("");
            textValue = textValueNext;
        }

        generateNode();
    }

    // deletes the infoArray from the list
    // deletes the related Node-Object from the Array as well
    public void deleteArray(InfoArray infoArray) throws InterruptedException{
        int index = this.infoArrays.indexOf(infoArray);
        this.infoArrays.remove(index);
        this.layoutArray.remove(index);
        generateNode();
    }

    // visualization of inserting an array element with a given index and value
    public void insertElement(InfoArray infoArray, int index, Object value) throws InterruptedException{
        // sets the Box
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        HBox elements = (HBox) vBoxArray.getChildren().get(1);
        FilteredList<Node> element = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexArray +"."+ index));
        StackPane stackPaneElement = (StackPane) element.get(0);
        ObservableList<Node> stackPaneChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneChildren.get(1);
        String memValue = textValue.getText();
        textValue.setText(value.toString());

        // iterates over the rest of the array and moves all elements one position forward
        for (int i = index+1; i < this.infoArrays.get(indexArray).getSize()+1; i++){
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            FilteredList<Node> elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+
                    indexArray +"."+ finalI));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            textValue = (Text) stackPaneChildrenNext.get(1);
            String memValueNext = textValue.getText();
            textValue.setText(memValue);
            memValue = memValueNext;
        }

        generateNode();

    }

    // visualization of setting an array element with a given index and value
    public void setElement(InfoArray infoArray, int index, Object value) throws InterruptedException{
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> vBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);
        FilteredList<Node> element = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexArray +"."+ index));
        StackPane stackPaneElement = (StackPane) element.get(0);
        ObservableList<Node> stackPaneChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneChildren.get(1);
        textValue.setText(value.toString());

        generateNode();
    }


    public void generateNode() {
        generateNode(arrayAnimation.getNullTransition());
    }

    // generates a node object to include in the visualization
    public void generateNode(Transition transition) {
        VBox node = new VBox();
        node.setId("Array");
        for (VBox vBox : layoutArray) {
            node.getChildren().add(vBox);
        }
        this.executeAlgorithmController.updateVisualization(node, transition);

    }

    // resets all relative data
    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutArray = new ArrayList<>();
        this.infoArrays = new ArrayList<>();
    }

    // sets execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }
}

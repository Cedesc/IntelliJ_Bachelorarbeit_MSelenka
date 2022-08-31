package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoArray;
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
import java.util.ArrayList;

public class ArrayVisualization {

    // saves all current using infoArrays and their visualizations also as an array, as well as the used execute algorithm controller
    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutArray = new ArrayList<VBox>();
    private ArrayList<InfoArray> infoArrays = new ArrayList<InfoArray>();

    // constructor
    public ArrayVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    // creates an empty array with a given length
    public void createArray(InfoArray infoArray,int length) throws InterruptedException{
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+String.valueOf(this.infoArrays.size()));
        for (int i = 0; i < length; i++){
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(String.valueOf(i)+" ");
            indexText.setId("textIndex"+this.infoArrays.size()+"."+String.valueOf(i));
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            Text value = new Text("");
            value.setId("textValue"+this.infoArrays.size()+"."+String.valueOf(i));
            StackPane stackPane2 = new StackPane(stackPane, value);
            stackPane2.setId("stackPane"+this.infoArrays.size()+"."+String.valueOf(i));
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
    public void createArrayWithValues(InfoArray infoArray, int length, Object[] values) throws InterruptedException{
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+String.valueOf(this.infoArrays.size()));
        for (int i = 0; i < length; i++){
            // creates all array boxes
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(String.valueOf(i)+" ");
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            if (i < values.length){
                // set the boxes with the given values in the visualization
                Text value = new Text(String.valueOf(values[i]));
                value.setId("textValue"+String.valueOf(this.infoArrays.size())+"."+String.valueOf(i));
                StackPane stackPane2 = new StackPane(stackPane, value);
                stackPane2.setId("stackPane"+String.valueOf(this.infoArrays.size())+"."+String.valueOf(i));

                hBox.getChildren().add(stackPane2);
            }
            else{
                // the rest oof the array will be empty, but the visualiation objects will  be created
                Text value = new Text("");
                value.setId("textValue"+String.valueOf(this.infoArrays.size())+"."+String.valueOf(i));
                StackPane stackPane2 = new StackPane(stackPane, value);
                stackPane2.setId("stackPane"+String.valueOf(this.infoArrays.size())+"."+String.valueOf(i));

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

    // swaps the values of both array elements of index1 and index2
    public void swapElements(InfoArray infoArray, int index1, int index2) throws InterruptedException{
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> vBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);

        // get visualization text of index 1
        FilteredList element1 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(index1)));
        StackPane stackPaneElement1 = (StackPane) element1.get(0);
        ObservableList<Node> stackPaneChildren1 = stackPaneElement1.getChildren();
        Text textValue = (Text) stackPaneChildren1.get(1);
        String memValue1 = textValue.getText();

        // new


        // get visualiation text of inde 2
        FilteredList element2 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(index2)));
        StackPane stackPaneElement2 = (StackPane) element2.get(0);
        ObservableList<Node> stackPaneChildren2 = stackPaneElement2.getChildren();
        Text textValue2 = (Text) stackPaneChildren2.get(1);
        String memValue2 = textValue2.getText();

        // new


        // set both texts
        textValue.setText(memValue2);
        textValue2.setText(memValue1);
        generateNode();
    }

    // visualization of deleteing an array element by an given index
    public void deleteElement(InfoArray infoArray, int index) throws InterruptedException{
        // get visualization text of the index
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> observableVBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) observableVBoxChildren.get(1);
        FilteredList elementStackpaneFilteredList = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(index)));
        StackPane stackPaneElement = (StackPane) elementStackpaneFilteredList.get(0);
        ObservableList<Node> stackPaneElementChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneElementChildren.get(1);
        textValue.setText("");

        // iterate over the rest of the element and move all elements one position backwards
        for (int i = index+1; i < this.infoArrays.get(indexArray).getSize()+1; i++){
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            FilteredList elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(finalI)));
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
    // deltes the related Node-Object from the Array as well
    public void deleteArray(InfoArray infoArray) throws InterruptedException{
        int index = this.infoArrays.indexOf(infoArray);
        this.infoArrays.remove(index);
        this.layoutArray.remove(index);
        generateNode();
    }

    // visualization of inserting an array element with an given index and value
    public void insertElement(InfoArray infoArray, int index, Object value) throws InterruptedException{
        // sets the Box
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> vBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);
        FilteredList element = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(index)));
        StackPane stackPaneElement = (StackPane) element.get(0);
        ObservableList<Node> stackPaneChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneChildren.get(1);
        String memValue = textValue.getText();
        textValue.setText(value.toString());

        // iterates over the rest of the array and moves all elements one position forward
        for (int i = index+1; i < this.infoArrays.get(indexArray).getSize()+1; i++){
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            FilteredList elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(finalI)));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            textValue = (Text) stackPaneChildrenNext.get(1);
            String memValueNext = textValue.getText();
            textValue.setText(memValue);
            memValue = memValueNext;
        }

        generateNode();

    }

    // visualization of setting an array element with an given index and value
    public void setElement(InfoArray infoArray, int index, Object value) throws InterruptedException{
        int indexArray = this.infoArrays.indexOf(infoArray);
        VBox vBoxArray = this.layoutArray.get(indexArray);
        ObservableList<Node> vBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);
        FilteredList element = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+String.valueOf(indexArray)+"."+String.valueOf(index)));
        StackPane stackPaneElement = (StackPane) element.get(0);
        ObservableList<Node> stackPaneChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneChildren.get(1);
        textValue.setText(value.toString());

        generateNode();
    }

    // generates a node object to include in the visualization
    public void generateNode() throws InterruptedException {
        Node node = new VBox();
        node.setId("Array");
        for (int i = 0; i < layoutArray.size(); i++){
            ((VBox) node).getChildren().add(layoutArray.get(i));
        }
        this.executeAlgorithmController.updateVisualization(node);

    }

    // resets all relative data
    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutArray = new ArrayList<VBox>();
        this.infoArrays = new ArrayList<InfoArray>();
    }

    // sets execute algorithm controller
    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }
}

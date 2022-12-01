package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoArrayList;
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
import visualization.animationCreation.ArrayListAnimation;

import java.util.ArrayList;

public class ArrayListVisualization {

    private ExecuteAlgorithmController executeAlgorithmController;
    /**
     * ArrayLists shown in the window.
     */
    private ArrayList<VBox> layoutArrayList = new ArrayList<>();
    /**
     * Data structure behind the shown trees.
     */
    private ArrayList<InfoArrayList> infoArrayLists = new ArrayList<>();

    /**
     * Instance of seperated class for creating the animations.
     */
    private final ArrayListAnimation arrayListAnimation = new ArrayListAnimation();

    // constructor
    public ArrayListVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void createArrayList(InfoArrayList infoArrayList, int length) {
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+ this.infoArrayLists.size());
        for (int i = 0; i < length; i++){
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(i +" ");
            indexText.setId("textIndex"+this.infoArrayLists.size()+"."+ i);
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            Text value = new Text("");
            value.setId("textValue"+this.infoArrayLists.size()+"."+ i);
            StackPane stackPane2 = new StackPane(stackPane, value);
            stackPane2.setId("stackPane"+this.infoArrayLists.size()+"."+ i);
            hBox.getChildren().add(stackPane2);
        }
        Text label = new Text("Array "+(this.infoArrayLists.size()+1));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);
        this.infoArrayLists.add(infoArrayList);
        this.layoutArrayList.add(vBox);


        // create animation
        Transition transition = arrayListAnimation.forCreateArrayList(vBox);

        generateNode(transition);
    }


    // deletes the infoArray from the list
    // deletes the related Node-Object from the Array as well
    public void deleteArrayList(InfoArrayList infoArrayList) {
        int index = this.infoArrayLists.indexOf(infoArrayList);
        this.infoArrayLists.remove(index);
        VBox deletedVBox = this.layoutArrayList.remove(index);


        // create animation
        Transition transition = arrayListAnimation.forDeleteArrayList(deletedVBox);

        generateNode(transition);
    }


    // visualization of inserting an array element with a given index and value
    public void insertElement(InfoArrayList infoArrayList, int index, Object value) throws InterruptedException{
        // determine which index the infoArrayList has in "infoArrayList" and use it to get to the corresponding vBox
        int indexOfArrayList = this.infoArrayLists.indexOf(infoArrayList);
        // Access the elements by taking the second element (i.e. index 1) from the vbox, since the first element is
        // just the label, while the second element contains the elements of the ArrayList.
        VBox vBoxArrayList = this.layoutArrayList.get(indexOfArrayList);
        HBox elements = (HBox) vBoxArrayList.getChildren().get(1);
        // Filter all elements of the array by ID and then accessing the first element (i.e. index 0) of the filtered
        // list. Maybe unnecessary to use the filter on it instead of just searching for the one element? Or maybe you
        // can even find the element via index access?
        FilteredList<Node> element = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexOfArrayList +"."+ index));
        StackPane stackPaneElement = (StackPane) element.get(0);
        ObservableList<Node> stackPaneChildren = stackPaneElement.getChildren();
        // save TextValue
        Text newTextValue = (Text) stackPaneChildren.get(1);
        // save the current value for later
        String memValue = newTextValue.getText();
        // overwrite the shown value
        newTextValue.setText(value.toString());

        Text textValue;
        ArrayList<Text> movedValuesForAnimation = new ArrayList<>();

        // iterates over the rest of the array and moves all elements one position forward
        for (int i = index+1; i < this.infoArrayLists.get(indexOfArrayList).getSize()+1; i++){
            // store i in variable, since parameters for lambda expressions should be final
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            // Search for the ID with filtering like before
            FilteredList<Node> elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+
                    indexOfArrayList +"."+ finalI));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            // save the Text for the animation
            movedValuesForAnimation.add((Text) stackPaneChildrenNext.get(1));
            // save TextValue
            textValue = (Text) stackPaneChildrenNext.get(1);
            // save the current value for later
            String memValueNext = textValue.getText();
            // overwrite the shown value
            textValue.setText(memValue);
            // save the next value
            memValue = memValueNext;
        }


        // create animation
        Transition transition = arrayListAnimation.forInsertElement(newTextValue, movedValuesForAnimation);

        generateNode(transition);

    }


    // visualization of deleting an array element by a given index
    public void deleteElement(InfoArrayList infoArrayList, int index) throws InterruptedException{
        // get visualization text of the index
        int indexOfArrayList = this.infoArrayLists.indexOf(infoArrayList);
        VBox vBoxArray = this.layoutArrayList.get(indexOfArrayList);
        ObservableList<Node> observableVBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) observableVBoxChildren.get(1);
        FilteredList<Node> elementStackPaneFilteredList = elements.getChildren().
                filtered(s -> s.getId().equals("stackPane"+ indexOfArrayList +"."+ index));
        StackPane stackPaneElement = (StackPane) elementStackPaneFilteredList.get(0);
        ObservableList<Node> stackPaneElementChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneElementChildren.get(1);
        textValue.setText("");

        // iterate over the rest of the elements and move each element one position backwards
        for (int i = index+1; i < this.infoArrayLists.get(indexOfArrayList).getSize()+1; i++){
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            FilteredList<Node> elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+
                    indexOfArrayList +"."+ finalI));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            Text textValueNext = (Text) stackPaneChildrenNext.get(1);
            textValue.setText(textValueNext.getText());
            textValueNext.setText("");
            textValue = textValueNext;
        }

        generateNode();
    }


    public void swapElements(InfoArrayList infoArrayList, int index1, int index2) throws InterruptedException{
        // determine which index the infoArrayList has in "infoArrayList" and use it to get to the corresponding vBox
        int indexOfArrayList = this.infoArrayLists.indexOf(infoArrayList);
        // Access the elements by taking the second element (i.e. index 1) from the vbox, since the first element is
        // just the label, while the second element contains the elements of the ArrayList.
        VBox vBoxArrayList = this.layoutArrayList.get(indexOfArrayList);
        ObservableList<Node> vBoxChildren = vBoxArrayList.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);

        // Filter all elements of the array by ID and then accessing the first element (i.e. index 0) of the filtered
        // list. Maybe unnecessary to use the filter on it instead of just searching for the one element? Or maybe you
        // can even find the element via index access?
        FilteredList<Node> element1 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexOfArrayList +"."+ index1));
        StackPane stackPaneElement1 = (StackPane) element1.get(0);
        ObservableList<Node> stackPaneChildren1 = stackPaneElement1.getChildren();
        // Der TextValue wird abgespeichert
        Text textValue = (Text) stackPaneChildren1.get(1);
        // Der momentane, später veraltete Wert, wird für später abgespeichert
        String memValue1 = textValue.getText();


        // Filter all elements of the array by ID and then accessing the first element (i.e. index 0) of the filtered
        // list. Maybe unnecessary to use the filter on it instead of just searching for the one element? Or maybe you
        // can even find the element via index access?
        FilteredList<Node> element2 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexOfArrayList +"."+ index2));
        StackPane stackPaneElement2 = (StackPane) element2.get(0);
        ObservableList<Node> stackPaneChildren2 = stackPaneElement2.getChildren();
        // save TextValue
        Text textValue2 = (Text) stackPaneChildren2.get(1);
        // save the current value for later
        String memValue2 = textValue2.getText();


        // set both texts
        textValue.setText(memValue2);
        textValue2.setText(memValue1);


        // create animation
        Transition transition = arrayListAnimation.forSwapElements(textValue, textValue2, index1, index2);

        generateNode(transition);
    }


    /**
     * If no transition is given, a NullTransition will be created for calling the generateNode()-function
     */
    public void generateNode() {
        generateNode(arrayListAnimation.getNullTransition());
    }

    public void generateNode(Transition transition) {
        VBox node = new VBox();
        node.setId("Table");
        for (VBox vBox : layoutArrayList) {
            node.getChildren().add(vBox);
        }
        this.executeAlgorithmController.updateVisualization(node, transition);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutArrayList = new ArrayList<>();
        this.infoArrayLists = new ArrayList<>();
    }

}

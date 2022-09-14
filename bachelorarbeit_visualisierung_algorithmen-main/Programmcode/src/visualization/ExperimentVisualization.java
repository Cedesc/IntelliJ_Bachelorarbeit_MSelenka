package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoExperiment;
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

public class ExperimentVisualization {

    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutExperiment = new ArrayList<VBox>();
    private ArrayList<InfoExperiment> infoExperiments = new ArrayList<InfoExperiment>();

    // constructor
    public ExperimentVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void createExperiment(InfoExperiment infoExperiment, int length) throws InterruptedException {
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+String.valueOf(this.infoExperiments.size()));
        for (int i = 0; i < length; i++){
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(String.valueOf(i)+" ");
            indexText.setId("textIndex"+this.infoExperiments.size()+"."+String.valueOf(i));
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            Text value = new Text("");
            value.setId("textValue"+this.infoExperiments.size()+"."+String.valueOf(i));
            StackPane stackPane2 = new StackPane(stackPane, value);
            stackPane2.setId("stackPane"+this.infoExperiments.size()+"."+String.valueOf(i));
            hBox.getChildren().add(stackPane2);
        }
        Text label = new Text("Array "+(this.infoExperiments.size()+1));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);
        this.infoExperiments.add(infoExperiment);
        this.layoutExperiment.add(vBox);
        generateNode();
    }


    // deletes the infoArray from the list
    // deletes the related Node-Object from the Array as well
    public void deleteExperiment(InfoExperiment infoExperiment) throws InterruptedException{
        int index = this.infoExperiments.indexOf(infoExperiment);
        this.infoExperiments.remove(index);
        this.layoutExperiment.remove(index);
        generateNode();
    }


    // visualization of inserting an array element with a given index and value
    public void insertElement(InfoExperiment infoExperiment, int index, Object value) throws InterruptedException{
        // Bestimmen welcher index das infoExperiment in "infoExperiments" hat und anhand dessen an die zugehörige
        // vbox gelangen
        // sets the Box
        int indexOfExperiment = this.infoExperiments.indexOf(infoExperiment);
        // Auf die Elemente zugreifen, indem man das zweite Element (also Index 1) von der vbox nimmt, da das erste
        // Element nur das Label ist, während das zweite Element die Elemente des Experiments sind.
        VBox vBoxExperiment = this.layoutExperiment.get(indexOfExperiment);
        HBox elements = (HBox) vBoxExperiment.getChildren().get(1);
        // Das eine gesuchte Element an Index "index" finden, indem alle Elemente des Arrays nach der ID gefiltert
        // werden und dann auf das erste Element (also Index 0) der gefilterten Liste zugegriffen wird. Irgendwie
        // unnötig direkt den Filter darauf zu nutzen, statt nur nach dem einen Element zu suchen? Oder kann man
        // vielleicht sogar per Indexzugriff das Element finden?
        // get visualization text of index 1
        FilteredList<Node> element = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexOfExperiment +"."+ index));
        StackPane stackPaneElement = (StackPane) element.get(0);
        ObservableList<Node> stackPaneChildren = stackPaneElement.getChildren();
        // Der TextValue wird abgespeichert
        Text textValue = (Text) stackPaneChildren.get(1);
        // Der momentane, später veraltete Wert, wird für später abgespeichert
        String memValue = textValue.getText();
        // Der geschriebene Wert wird überschrieben
        textValue.setText(value.toString());

        // iterates over the rest of the array and moves all elements one position forward
        for (int i = index+1; i < this.infoExperiments.get(indexOfExperiment).getSize()+1; i++){
            // i in Variable speichern, da Parameter für lambda expressions final sein sollten
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            // Wie weiter oben wieder nach der ID mithilfe einer FilteredList suchen
            FilteredList<Node> elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+
                    String.valueOf(indexOfExperiment)+"."+String.valueOf(finalI)));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            // Der TextValue wird abgespeichert
            textValue = (Text) stackPaneChildrenNext.get(1);
            // Der momentane, später veraltete Wert, wird für später abgespeichert
            String memValueNext = textValue.getText();
            // Der geschriebene Wert wird überschrieben
            textValue.setText(memValue);
            // Der nächste Wert wird abgespeichert
            memValue = memValueNext;
        }

        generateNode();

    }


    // visualization of deleting an array element by a given index
    public void deleteElement(InfoExperiment infoExperiment, int index) throws InterruptedException{
        // get visualization text of the index
        int indexOfExperiment = this.infoExperiments.indexOf(infoExperiment);
        VBox vBoxArray = this.layoutExperiment.get(indexOfExperiment);
        ObservableList<Node> observableVBoxChildren = vBoxArray.getChildren();
        HBox elements = (HBox) observableVBoxChildren.get(1);
        FilteredList elementStackpaneFilteredList = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                String.valueOf(indexOfExperiment)+"."+String.valueOf(index)));
        StackPane stackPaneElement = (StackPane) elementStackpaneFilteredList.get(0);
        ObservableList<Node> stackPaneElementChildren = stackPaneElement.getChildren();
        Text textValue = (Text) stackPaneElementChildren.get(1);
        textValue.setText("");

        // iterate over the rest of the element and move all elements one position backwards
        for (int i = index+1; i < this.infoExperiments.get(indexOfExperiment).getSize()+1; i++){
            int finalI = i;
            ObservableList<Node> elementsHBoxChildren = elements.getChildren();
            FilteredList elementNext = elementsHBoxChildren.filtered(s -> s.getId().equals("stackPane"+
                    String.valueOf(indexOfExperiment)+"."+String.valueOf(finalI)));
            StackPane stackPaneElementNext = (StackPane) elementNext.get(0);
            ObservableList<Node> stackPaneChildrenNext = stackPaneElementNext.getChildren();
            Text textValueNext = (Text) stackPaneChildrenNext.get(1);
            textValue.setText(textValueNext.getText());
            textValueNext.setText("");
            textValue = textValueNext;
        }

        generateNode();
    }


    public void swapElements(InfoExperiment infoExperiment, int index1, int index2) throws InterruptedException{
        // Bestimmen welcher index das infoExperiment in "infoExperiments" hat und anhand dessen an die zugehörige vbox
        // gelangen
        int indexOfExperiment = this.infoExperiments.indexOf(infoExperiment);
        // Auf die Elemente zugreifen, indem man das zweite Element (also Index 1) von der vbox nimmt, da das erste
        // Element nur das Label ist, während das zweite Element die Elemente des Experiments sind.
        VBox vBoxExperiment = this.layoutExperiment.get(indexOfExperiment);
        ObservableList<Node> vBoxChildren = vBoxExperiment.getChildren();
        HBox elements = (HBox) vBoxChildren.get(1);

        // Das eine gesuchte Element an Index "index" finden, indem alle Elemente des Arrays nach der ID gefiltert
        // werden und dann auf das erste Element (also Index 0) der gefilterten Liste zugegriffen wird. Irgendwie
        // unnötig direkt den Filter darauf zu nutzen, statt nur nach dem einen Element zu suchen? Oder kann man
        // vielleicht sogar per Indexzugriff das Element finden?
        // get visualization text of index 1
        FilteredList<Node> element1 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexOfExperiment +"."+ index1));
        StackPane stackPaneElement1 = (StackPane) element1.get(0);
        ObservableList<Node> stackPaneChildren1 = stackPaneElement1.getChildren();
        // Der TextValue wird abgespeichert
        Text textValue = (Text) stackPaneChildren1.get(1);
        // Der momentane, später veraltete Wert, wird für später abgespeichert
        String memValue1 = textValue.getText();


        // Das eine gesuchte Element an Index "index" finden, indem alle Elemente des Arrays nach der ID gefiltert
        // werden und dann auf das erste Element (also Index 0) der gefilterten Liste zugegriffen wird. Irgendwie
        // unnötig direkt den Filter darauf zu nutzen, statt nur nach dem einen Element zu suchen? Oder kann man
        // vielleicht sogar per Indexzugriff das Element finden?
        // get visualization text of index 2
        FilteredList<Node> element2 = elements.getChildren().filtered(s -> s.getId().equals("stackPane"+
                indexOfExperiment +"."+ index2));
        StackPane stackPaneElement2 = (StackPane) element2.get(0);
        ObservableList<Node> stackPaneChildren2 = stackPaneElement2.getChildren();
        // Der TextValue wird abgespeichert
        Text textValue2 = (Text) stackPaneChildren2.get(1);
        // Der momentane, später veraltete Wert, wird für später abgespeichert
        String memValue2 = textValue2.getText();


        // set both texts
        textValue.setText(memValue2);
        textValue2.setText(memValue1);
        generateNode();
    }


    public void generateNode() throws InterruptedException {
        Node node = new VBox();
        node.setId("Table");
        for (int i = 0; i < layoutExperiment.size(); i++){
            ((VBox) node).getChildren().add(layoutExperiment.get(i));
        }
        this.executeAlgorithmController.updateVisualization(node);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutExperiment = new ArrayList<VBox>();
        this.infoExperiments = new ArrayList<InfoExperiment>();
    }

}

package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoExperiment;
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

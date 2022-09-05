package visualization;

import controller.ExecuteAlgorithmController;
import datastructures.InfoArray;
import datastructures.InfoTable;
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

public class TableVisualization {

    private ExecuteAlgorithmController executeAlgorithmController;
    private ArrayList<VBox> layoutTable = new ArrayList<VBox>();
    private ArrayList<InfoTable> infoTables = new ArrayList<InfoTable>();

    // constructor
    public TableVisualization(ExecuteAlgorithmController executeAlgorithmController){
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void createTable(InfoTable infoTable, int length) throws InterruptedException {
        HBox hBox = new HBox();
        hBox.setId("Elementarray"+String.valueOf(this.infoTables.size()));
        for (int i = 0; i < length; i++){
            Rectangle rectangleValue = new Rectangle();
            rectangleValue.setWidth(50);
            rectangleValue.setHeight(50);
            rectangleValue.setStroke(Color.BLACK);
            rectangleValue.setFill(Color.TRANSPARENT);
            rectangleValue.setStrokeType(StrokeType.OUTSIDE);
            Text indexText = new Text(String.valueOf(i)+" ");
            indexText.setId("textIndex"+this.infoTables.size()+"."+String.valueOf(i));
            StackPane stackPane = new StackPane(rectangleValue, indexText);
            stackPane.setAlignment(Pos.BOTTOM_RIGHT);
            Text value = new Text("");
            value.setId("textValue"+this.infoTables.size()+"."+String.valueOf(i));
            StackPane stackPane2 = new StackPane(stackPane, value);
            stackPane2.setId("stackPane"+this.infoTables.size()+"."+String.valueOf(i));
            hBox.getChildren().add(stackPane2);
        }
        Text label = new Text("Array "+(this.infoTables.size()+1));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, hBox);
        this.infoTables.add(infoTable);
        this.layoutTable.add(vBox);
        generateNode();
    }


    public void generateNode() throws InterruptedException {
        Node node = new VBox();
        node.setId("Table");
        for (int i = 0; i < layoutTable.size(); i++){
            ((VBox) node).getChildren().add(layoutTable.get(i));
        }
        this.executeAlgorithmController.updateVisualization(node);
    }

    public void setExecuteAlgorithmController(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
    }

    public void resetVisualization(ExecuteAlgorithmController executeAlgorithmController) {
        this.executeAlgorithmController = executeAlgorithmController;
        this.layoutTable = new ArrayList<VBox>();
        this.infoTables = new ArrayList<InfoTable>();
    }

}

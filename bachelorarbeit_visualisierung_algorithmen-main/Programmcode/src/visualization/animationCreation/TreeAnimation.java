package visualization.animationCreation;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import visualization.ArrayListVisualization;

/**
 * Seperated class for creating animations for {@link ArrayListVisualization}.
 * <p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public class TreeAnimation extends AbstractAnimationCreator {

    public Transition forCreateTree(VBox visualizedTree) {

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslate = createInstantTranslate(visualizedTree, 100, 0);

        // create translate transition
        TranslateTransition translate = new TranslateTransition(this.standardDuration.multiply(1.), visualizedTree);
        translate.setByX(-100);

        // create fade transition
        FadeTransition fade = new FadeTransition(this.standardDuration.multiply(1.), visualizedTree);
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(instantTranslate, translate, fade);
    }

    public Transition forAddLeaf(StackPane addedLeaf, Line addedEdge) {

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslateLeaf = createInstantTranslate(addedLeaf, 0, 25);

        // create translate transition for the inserted value
        TranslateTransition translateLeaf = new TranslateTransition(this.standardDuration.multiply(0.7), addedLeaf);
        translateLeaf.setByY(-25);

        // make the node invisible for a correct fade-in
        addedLeaf.setOpacity(0);

        // create fade transition for the inserted value
        FadeTransition fadeLeaf = new FadeTransition(this.standardDuration.multiply(0.7), addedLeaf);
        fadeLeaf.setFromValue(0);
        fadeLeaf.setToValue(1);

        ParallelTransition addLeaf = new ParallelTransition(instantTranslateLeaf, translateLeaf, fadeLeaf);

        // create fade-in transition for the created edge
        FadeTransition fadeEdge = new FadeTransition(this.standardDuration.multiply(0.3), addedEdge);
        fadeEdge.setFromValue(0);
        fadeEdge.setToValue(1);

        return new SequentialTransition(addLeaf, fadeEdge);

    }

    public Transition forChangeValue(StackPane visualizedNode, Text valueText, Object oldValue) {

        // create new Text for showing the old value
        String oldValueString = oldValue.toString();
        Text oldValueText = new Text(oldValueString);
        visualizedNode.getChildren().add(oldValueText);

        // create fade-out transition for the old value
        FadeTransition fadeOut = new FadeTransition(this.standardDuration.multiply(0.5), oldValueText);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        // remove the new created Text node
        fadeOut.setOnFinished(actionEvent -> visualizedNode.getChildren().remove(oldValueText));

        // create fade-in transition for the new value
        FadeTransition fadeIn = new FadeTransition(this.standardDuration.multiply(0.5), valueText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        return new SequentialTransition(fadeOut, fadeIn);

    }

    public Transition forGetNodeByIndex(StackPane visualizedNode) {

        // create scale transition
        ScaleTransition scale = new ScaleTransition(this.standardDuration.multiply(0.5), visualizedNode);
        scale.setByX(0.3);
        scale.setByY(0.3);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        return scale;

    }

    public Transition forGetIndexByValue(StackPane visualizedNode) {

        // create scale transition
        ScaleTransition scale = new ScaleTransition(this.standardDuration.multiply(0.5), visualizedNode);
        scale.setByX(0.3);
        scale.setByY(0.3);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        return scale;

    }

}

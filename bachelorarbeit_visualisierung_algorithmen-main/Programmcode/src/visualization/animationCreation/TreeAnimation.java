package visualization.animationCreation;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Seperated class for creating animations for {@link visualization.ExperimentVisualization}.
 * <p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public class TreeAnimation extends AbstractAnimationCreator {

    public Transition forCreateTree(VBox visualizedTree) {

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslate = createInstantTranslate(visualizedTree, 100, 0);

        // create translate transition
        TranslateTransition translate = new TranslateTransition(this.standardDuration.multiply(1.2), visualizedTree);
        translate.setByX(-100);

        // create fade transition
        FadeTransition fade = new FadeTransition(this.standardDuration.multiply(1.2), visualizedTree);
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(instantTranslate, translate, fade);
    }

    public Transition forAddLeaf(StackPane addedLeaf, Line addedEdge) {

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslateLeaf = createInstantTranslate(addedLeaf, 0, -25);

        // create translate transition for the inserted value
        TranslateTransition translateLeaf = new TranslateTransition(this.standardDuration, addedLeaf);
        translateLeaf.setByY(25);

        // create fade transition for the inserted value
        FadeTransition fadeLeaf = new FadeTransition(this.standardDuration.multiply(1.6), addedLeaf);
        fadeLeaf.setFromValue(0);
        fadeLeaf.setToValue(1);

        ParallelTransition addLeaf = new ParallelTransition(instantTranslateLeaf, translateLeaf, fadeLeaf);

        // create fade-in transition for the created edge
        FadeTransition fadeEdge = new FadeTransition(this.standardDuration.multiply(0.35), addedEdge);
        fadeEdge.setFromValue(0);
        fadeEdge.setToValue(1);

        return new SequentialTransition(addLeaf, fadeEdge);

    }

    public Transition forChangeValue(StackPane visualizedNode, Text valueText, Object oldValue) {

        // create new Text for showing the old value
        String oldValueString;
        if (oldValue == null)
            oldValueString = "Nil";
        else
            oldValueString = oldValue.toString();
        Text oldValueText = new Text(oldValueString);
        visualizedNode.getChildren().add(oldValueText);

        // create fade-out transition for the old value
        FadeTransition fadeOut = new FadeTransition(this.standardDuration.multiply(0.8), oldValueText);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        // remove the new created Text node
        fadeOut.setOnFinished(actionEvent -> visualizedNode.getChildren().remove(oldValueText));

        // create fade-in transition for the new value
        FadeTransition fadeIn = new FadeTransition(this.standardDuration.multiply(0.8), valueText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        return new SequentialTransition(fadeOut, fadeIn);

    }

    public Transition forMoveNodeToAnotherParent() {
        // 1. fade out the edge
        // 2. move the node to the right position
        // 3. fade in the edge
        return null;
    }

    public Transition forGetNodeByIndex(StackPane visualizedNode) {

        // create scale transition
        ScaleTransition scale = new ScaleTransition(this.standardDuration.multiply(1.), visualizedNode);
        scale.setByX(0.3);
        scale.setByY(0.3);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        // create fade transition
        FadeTransition fade = new FadeTransition(this.standardDuration.multiply(1.), visualizedNode);
        fade.setFromValue(1);
        fade.setToValue(0.5);
        fade.setCycleCount(2);
        fade.setAutoReverse(true);

        return new ParallelTransition(scale);

    }

    public Transition forGetIndexByValue(StackPane visualizedNode) {

        // create scale transition
        ScaleTransition scale = new ScaleTransition(this.standardDuration.multiply(1.), visualizedNode);
        scale.setByX(0.3);
        scale.setByY(0.3);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        // create fade transition
        FadeTransition fade = new FadeTransition(this.standardDuration.multiply(1.), visualizedNode);
        fade.setFromValue(1);
        fade.setToValue(0.5);
        fade.setCycleCount(2);
        fade.setAutoReverse(true);

        return new ParallelTransition(scale);

    }

}

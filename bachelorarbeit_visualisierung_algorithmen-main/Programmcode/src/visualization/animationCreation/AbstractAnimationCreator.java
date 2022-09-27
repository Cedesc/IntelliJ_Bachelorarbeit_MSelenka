package visualization.animationCreation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import supportClasses.config.TempConfig;

/**
 * Abstract class for the seperated classes for creating animations.
 * <p>
 *     Note: Directly changing the translation of a node with {@link Node#setTranslateX(double)} or
 *     {@link Node#setTranslateY(double)} can cause errors if the input is too fast. Instead, create a
 *     TranslateTransition with the duration of zero. You can use the function
 *     {@link #createInstantTranslate(Node, int, int)}.
 * <p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public abstract class AbstractAnimationCreator {

    /**
     * Standard distance of the concrete elements in the gui.
     */
    protected final int fieldDistance = TempConfig.FIELD_DISTANCE;
    /**
     * Standard duration for the transitions. When changed, all runtimes of the animations are changed.
     */
    protected final Duration standardDuration = TempConfig.STANDARD_DURATION;
    /**
     * Default translation. Placeholder for animations that aren't created yet.
     */
    protected final TranslateTransition nullTransition =
            new TranslateTransition(standardDuration.multiply(3.0), new Node() {});


    public TranslateTransition getNullTransition() {
        return nullTransition;
    }

    /**
     * Directly changing the translation of a node with {@link Node#setTranslateX(double)} or
     * {@link Node#setTranslateY(double)} can cause
     * errors if the input is too fast. Instead, create a TranslateTransition with a
     * duration of zero.
     * @param node the node to which the TranslateTransition is to be applied
     * @param x the amount of the translation in x direction
     * @param y the amount of the translation in y direction
     * @return a TranslateTransition with a duration of zero
     */
    protected TranslateTransition createInstantTranslate(Node node, int x, int y) {
        TranslateTransition instantTranslate = new TranslateTransition();
        instantTranslate.setNode(node);
        instantTranslate.setByX(x);
        instantTranslate.setByY(y);
        instantTranslate.setDuration(Duration.ZERO);
        return instantTranslate;
    }

}

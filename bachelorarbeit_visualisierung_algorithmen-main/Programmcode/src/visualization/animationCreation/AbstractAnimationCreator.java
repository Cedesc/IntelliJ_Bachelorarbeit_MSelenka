package visualization.animationCreation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import supportClasses.config.ConfigReader;

/**
 * Abstract class for the seperated classes for creating animations.
 * <p>
 *     Note: Directly changing the translation of a node with {@link Node#setTranslateX(double)} or
 *     {@link Node#setTranslateY(double)} can cause errors if the input is too fast. Instead, create a
 *     TranslateTransition with the duration of zero. You can use the function
 *     {@link #createInstantTranslate(Node, double, double)}.
 * <p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public abstract class AbstractAnimationCreator {

    /**
     * Standard distance of the concrete elements in the gui.
     */
    protected final int fieldDistance = 50;
    /**
     * Standard duration for the transitions. When changed, all runtimes of the animations are changed.
     */
    protected final Duration standardDuration = ConfigReader.getStandardDuration();
    /**
     * Default translation. Placeholder for animations that aren't created yet.
     */
    protected final TranslateTransition nullTransition =
            new TranslateTransition(standardDuration.multiply(1.), new Node() {});


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
    protected TranslateTransition createInstantTranslate(Node node, double x, double y) {
        TranslateTransition instantTranslate = new TranslateTransition();
        instantTranslate.setNode(node);
        instantTranslate.setByX(x);
        instantTranslate.setByY(y);
        instantTranslate.setDuration(Duration.ZERO);
        return instantTranslate;
    }

}

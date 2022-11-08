package visualization.animationCreation;


import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;

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
        TranslateTransition translate = new TranslateTransition(this.standardDuration.multiply(2), visualizedTree);
        translate.setByX(-100);

        // create fade transition
        FadeTransition fade = new FadeTransition(this.standardDuration.multiply(2), visualizedTree);
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(instantTranslate, translate, fade);
    }

}

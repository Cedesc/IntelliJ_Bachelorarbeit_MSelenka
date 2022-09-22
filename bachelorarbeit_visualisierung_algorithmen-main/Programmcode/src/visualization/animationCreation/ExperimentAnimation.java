package visualization.animationCreation;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Seperated class for creating animations for ExperimentVisualization.
 * <p></p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public class ExperimentAnimation {

    /**
     * @param visualizedArray vBox, in which are both the label and the hBox containing the values
     * @return a fade-in transition for createExperiment
     */
    public Transition forCreateExperiment(VBox visualizedArray) {

        // create translate transition
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(visualizedArray);
        translate.setDuration(Duration.millis(500));
        translate.setCycleCount(4);
        translate.setByY(100);
        translate.setAutoReverse(true);

        // create fade transition
        FadeTransition fade = new FadeTransition();
        fade.setNode(visualizedArray);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(translate, fade);
    }

}

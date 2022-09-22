package visualization.animationCreation;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        translate.setDuration(Duration.millis(800));
        visualizedArray.setTranslateX(100);
        translate.setByX(-100);

        // create fade transition
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(800));
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(visualizedArray, translate, fade);
    }

    public Transition forDeleteExperiment(VBox visualizedArray) {

        // TODO: 22.09.2022 doesn't work, maybe because the array is completely removed before the animations are played

        // create translate transition
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.millis(800));
        visualizedArray.setTranslateX(-100);
        translate.setByX(100);

        // create fade transition
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(800));
        fade.setFromValue(1);
        fade.setToValue(0);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(visualizedArray, translate, fade);
    }

    public Transition forInsertElement(Text element) {

        // create translate transition
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.millis(300));
        element.setTranslateY(-25);
        translate.setByY(25);

        // create fade transition
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(element, translate, fade);
    }

}

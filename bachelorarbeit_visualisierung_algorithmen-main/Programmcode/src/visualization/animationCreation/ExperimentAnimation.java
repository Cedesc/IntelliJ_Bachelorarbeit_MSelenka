package visualization.animationCreation;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Seperated class for creating animations for ExperimentVisualization.
 * <p></p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public class ExperimentAnimation {

    /**
     * @param visualizedArray vBox, in which are both the label and the hBox containing the values
     * @return a fade-in translation
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

    /**
     * @param visualizedArray vBox, in which are both the label and the hBox containing the values
     * @return a fade-out translation
     */
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

    /**
     * @param insertedValue new value in the array
     * @param movedValues values to the right of the inserted value that needs to be moved to the next fields
     * @return a fade-in translation for the inserted element and a translation to the next field to the
     * right for each element, that needs to be moved
     */
    public Transition forInsertElement(Text insertedValue, ArrayList<Text> movedValues) {

        // create translate transition for the inserted value
        TranslateTransition translateInserted = new TranslateTransition();
        translateInserted.setNode(insertedValue);
        translateInserted.setDuration(Duration.millis(300));
        insertedValue.setTranslateY(-25);
        translateInserted.setByY(25);

        // create fade transition for the inserted value
        FadeTransition fadeInserted = new FadeTransition();
        fadeInserted.setNode(insertedValue);
        fadeInserted.setDuration(Duration.millis(500));
        fadeInserted.setFromValue(0);
        fadeInserted.setToValue(1);

        ParallelTransition parallel = new ParallelTransition(translateInserted, fadeInserted);

        // for each moved Value, create a translation to the next field to the right
        for (Text moveValue : movedValues) {
            // create translate transition
            TranslateTransition translateMoved = new TranslateTransition();
            translateMoved.setNode(moveValue);
            translateMoved.setDuration(Duration.millis(300));
            moveValue.setTranslateX(-50);
            translateMoved.setByX(50);
            // add to the parallelTransition
            parallel.getChildren().add(translateMoved);
        }

        return parallel;
    }

}

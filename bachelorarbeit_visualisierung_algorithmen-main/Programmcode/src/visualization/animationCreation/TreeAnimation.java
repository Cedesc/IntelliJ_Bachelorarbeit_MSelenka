package visualization.animationCreation;


import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
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
        TranslateTransition translate = new TranslateTransition(this.standardDuration.multiply(2), visualizedTree);
        translate.setByX(-100);

        // create fade transition
        FadeTransition fade = new FadeTransition(this.standardDuration.multiply(2), visualizedTree);
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(instantTranslate, translate, fade);
    }

    public Transition forAddLeaf(VBox visualizedTree) {
        /*
        // create instant translate transition for the correct start point
        TranslateTransition instantTranslateInserted = createInstantTranslate(insertedValue, 0, -25);

        // create translate transition for the inserted value
        TranslateTransition translateInserted = new TranslateTransition(this.standardDuration, insertedValue);
        translateInserted.setByY(25);

        // create fade transition for the inserted value
        FadeTransition fadeInserted = new FadeTransition(this.standardDuration.multiply(1.6), insertedValue);
        fadeInserted.setFromValue(0);
        fadeInserted.setToValue(1);

        ParallelTransition parallel = new ParallelTransition(instantTranslateInserted, translateInserted, fadeInserted);

        // for each moved Value, create a translation to the next field to the right
        for (Text moveValue : movedValues) {
            // create instant translate transition for the correct start point
            TranslateTransition instantTranslateMoved = createInstantTranslate(moveValue, -this.fieldDistance, 0);

            // create translate transition
            TranslateTransition translateMoved = new TranslateTransition(this.standardDuration, moveValue);
            translateMoved.setByX(this.fieldDistance);

            // add them to the parallelTransition
            parallel.getChildren().add(instantTranslateMoved);
            parallel.getChildren().add(translateMoved);
        }

        return parallel;

         */
        return null;
    }

}

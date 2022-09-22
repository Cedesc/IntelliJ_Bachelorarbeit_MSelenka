package visualization.animationCreation;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Seperated class for creating animations for ExperimentVisualization.
 * <p>
 *     Note: Directly changing the translation of a node with node.setTranslateX() or
 *     node.setTranslateY() can cause errors if the input is too fast. Instead, create a
 *     TranslateTransition with the duration of zero. You can use the function
 *     {@link #createInstantTranslate(Node, int, int)}.
 * <p>
 * <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/animation/ParallelTransition.html">Documentation</a>
 */
public class ExperimentAnimation {

    private final int fieldDistance = 50;

    /**
     * @param visualizedArray vBox, in which are both the label and the hBox containing the values
     * @return a fade-in translation
     */
    public Transition forCreateExperiment(VBox visualizedArray) {

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslate = createInstantTranslate(visualizedArray, 100, 0);

        // create translate transition
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.millis(800));
        translate.setByX(-100);

        // create fade transition
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(800));
        fade.setFromValue(0);
        fade.setToValue(1);

        // create parallel transition with the two preceding transitions
        return new ParallelTransition(visualizedArray, instantTranslate, translate, fade);
    }

    /**
     * @param visualizedArray vBox, in which are both the label and the hBox containing the values
     * @return a fade-out translation
     */
    public Transition forDeleteExperiment(VBox visualizedArray) {

        // TODO: 22.09.2022 doesn't work, maybe because the array is completely removed before the animations are played

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslate = createInstantTranslate(visualizedArray, -100, 0);

        // create translate transition
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.millis(800));
        translate.setByX(100);

        // create fade transition
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(800));
        fade.setFromValue(1);
        fade.setToValue(0);

        // create parallel transition and add all transitions
        return new ParallelTransition(visualizedArray, instantTranslate, translate, fade);
    }

    /**
     * @param insertedValue new value in the array
     * @param movedValues values to the right of the inserted value that needs to be moved to the next fields
     * @return a fade-in translation for the inserted element and a translation to the next field to the
     * right for each element, that needs to be moved
     */
    public Transition forInsertElement(Text insertedValue, ArrayList<Text> movedValues) {

        // create instant translate transition for the correct start point
        TranslateTransition instantTranslateInserted = createInstantTranslate(insertedValue, 0, -25);

        // create translate transition for the inserted value
        TranslateTransition translateInserted = new TranslateTransition();
        translateInserted.setNode(insertedValue);
        translateInserted.setDuration(Duration.millis(300));
        translateInserted.setByY(25);

        // create fade transition for the inserted value
        FadeTransition fadeInserted = new FadeTransition();
        fadeInserted.setNode(insertedValue);
        fadeInserted.setDuration(Duration.millis(500));
        fadeInserted.setFromValue(0);
        fadeInserted.setToValue(1);

        ParallelTransition parallel = new ParallelTransition(instantTranslateInserted, translateInserted, fadeInserted);

        // for each moved Value, create a translation to the next field to the right
        for (Text moveValue : movedValues) {
            // create instant translate transition for the correct start point
            TranslateTransition instantTranslateMoved = createInstantTranslate(moveValue, -this.fieldDistance, 0);

            // create translate transition
            TranslateTransition translateMoved = new TranslateTransition();
            translateMoved.setNode(moveValue);
            translateMoved.setDuration(Duration.millis(300));
            translateMoved.setByX(50);

            // add them to the parallelTransition
            parallel.getChildren().add(instantTranslateMoved);
            parallel.getChildren().add(translateMoved);
        }

        return parallel;
    }

    /**
     * @param text1 first text field to swap
     * @param text2 second text field to swap
     * @param index1 index of text1
     * @param index2 index of text2
     * @return a translation in y direction followed by a translation to the start point of the other text
     * and finished with a translation in the opposite y direction respectively to the first translation
     */
    public Transition forSwapElements(Text text1, Text text2, int index1, int index2) {

        // calculate distance between text1 and text2
        int deltaX = (index2 - index1) * this.fieldDistance;

        // determine the translation in y direction
        int toY = 15;

        // create instant translate transitions for the correct start points
        TranslateTransition instantTranslateText1 = createInstantTranslate(text1, deltaX, 0);
        TranslateTransition instantTranslateText2 = createInstantTranslate(text2, -deltaX, 0);


        // text1 down
        TranslateTransition translate11 = new TranslateTransition();
        translate11.setNode(text1);
        translate11.setByY(toY);
        translate11.setDuration(Duration.millis(300));

        // text1 to position of text2
        TranslateTransition translate12 = new TranslateTransition();
        translate12.setNode(text1);
        translate12.setByX(-deltaX);
        translate12.setDuration(Duration.millis(300));

        // text1 up
        TranslateTransition translate13 = new TranslateTransition();
        translate13.setNode(text1);
        translate13.setByY(-toY);
        translate13.setDuration(Duration.millis(300));

        // SequentialTransition of text1
        SequentialTransition sequentialTransition1 = new SequentialTransition(translate11, translate12, translate13);


        // text2 up
        TranslateTransition translate21 = new TranslateTransition();
        translate21.setNode(text2);
        translate21.setByY(-toY);
        translate21.setDuration(Duration.millis(300));

        // text2 to position of text1
        TranslateTransition translate22 = new TranslateTransition();
        translate22.setNode(text2);
        translate22.setByX(deltaX);
        translate22.setDuration(Duration.millis(300));

        // text2 down
        TranslateTransition translate23 = new TranslateTransition();
        translate23.setNode(text2);
        translate23.setByY(toY);
        translate23.setDuration(Duration.millis(300));

        // SequentialTransition of text2
        SequentialTransition sequentialTransition2 = new SequentialTransition(translate21, translate22, translate23);


        // create parallel transition and add the transitions
        return new ParallelTransition(instantTranslateText1, instantTranslateText2,
                sequentialTransition1, sequentialTransition2);
    }


    /**
     * Directly changing the translation of a node with node.setTranslateX() can cause
     * errors if the input is too fast. Instead, create a TranslateTransition with a
     * duration of zero.
     * @param node the node to which the TranslateTransition is to be applied
     * @param x the amount of the translation in x direction
     * @param y the amount of the translation in y direction
     * @return a TranslateTransition with a duration of zero
     */
    private TranslateTransition createInstantTranslate(Node node, int x, int y) {
        TranslateTransition instantTranslate = new TranslateTransition();
        instantTranslate.setNode(node);
        instantTranslate.setByX(x);
        instantTranslate.setByY(y);
        instantTranslate.setDuration(Duration.ZERO);
        return instantTranslate;
    }

}

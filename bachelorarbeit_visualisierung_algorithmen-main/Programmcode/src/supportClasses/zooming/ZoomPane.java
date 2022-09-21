package supportClasses.zooming;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;

/**
 * Pane which enables zooming.
 * <p></p>
 * From
 * <a href="https://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer">here</a>
 * as "PannableCanvas"
 */
public class ZoomPane extends Pane {

    /**
     * Actual scale of the ZoomPane.
     */
    DoubleProperty myScale = new SimpleDoubleProperty(1.0);

    public ZoomPane() {
        // bind x and y scale of the Pane to the myScale variable => if myScale changes, x and y will change too
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);
    }

    public double getScale() {
        return myScale.get();
    }

    public void setScale(double scale) {
        myScale.set(scale);
    }

    /**
     * @param x x-coordinate of the new pivot point
     * @param y y-coordinate of the new pivot point
     */
    public void setPivot(double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }
}

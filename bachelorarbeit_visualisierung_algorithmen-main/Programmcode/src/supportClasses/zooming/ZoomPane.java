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

    DoubleProperty myScale = new SimpleDoubleProperty(1.0);

    public ZoomPane() {
        // add scale transform
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);
    }

    public double getScale() {
        return myScale.get();
    }

    public void setScale( double scale) {
        myScale.set(scale);
    }

    public void setPivot( double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }
}

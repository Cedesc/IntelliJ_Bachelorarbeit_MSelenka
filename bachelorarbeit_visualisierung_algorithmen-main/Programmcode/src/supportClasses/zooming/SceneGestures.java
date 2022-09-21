package supportClasses.zooming;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

/**
 * Handles the parameters and the event for zooming.
 * <p></p>
 * From
 * <a href="https://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer">here</a>
 * as "SceneGestures"
 */
public class SceneGestures {

    /**
     * Set maximum value of the scale.
     */
    private static final double MAX_SCALE = 10.0d;
    /**
     * Set minimum value of the scale.
     */
    private static final double MIN_SCALE = .1d;

    public boolean zoomRelativeToMousePosition;

    /**
     * Saved ZoomPane to apply changes.
     */
    ZoomPane zoomPane;

    public SceneGestures(ZoomPane zoomPane, boolean zoomRelativeToMousePosition) {
        this.zoomPane = zoomPane;
        this.zoomRelativeToMousePosition = zoomRelativeToMousePosition;
    }

    public EventHandler<ScrollEvent> getOnScrollEventHandler() {
        return onScrollEventHandler;
    }

    /**
     * Mouse wheel handler: zoom to pivot point.
     */
    private final EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<>() {

        @Override
        public void handle(ScrollEvent event) {

            // Less than 1.0 leads to inverted zoom
            double scrollSpeed = 1.2;

            // save the scale of the ZoomPane as scale and oldScale
            double scale = zoomPane.getScale();
            double oldScale = scale;

            // calculate the new scale using the mouse wheel movement direction
            if (event.getDeltaY() < 0)
                scale /= scrollSpeed;
            else
                scale *= scrollSpeed;

            // check if the new scale is neither less than MIN_SCALE nor greater than MAX_SCALE
            scale = withinTheLimits(scale, MIN_SCALE, MAX_SCALE);


            // calculate the new pivot point
            double f = (scale / oldScale) - 1;
            double dx = (event.getSceneX() - (zoomPane.getBoundsInParent().getWidth() / 2
                    + zoomPane.getBoundsInParent().getMinX()));
            double dy = (event.getSceneY() - (zoomPane.getBoundsInParent().getHeight() / 2
                    + zoomPane.getBoundsInParent().getMinY()));

            // set the scale to the new value
            zoomPane.setScale(scale);

            if (zoomRelativeToMousePosition) {
                // note: pivot point must be untransformed, i.e. without scaling
                zoomPane.setPivot(f * dx, f * dy);
            }

            event.consume();
        }

    };

    /**
     * @param value given value to check
     * @param min given minimum
     * @param max given maximum
     * @return maximumOf(minimumOf(value, max), min)
     */
    public static double withinTheLimits(double value, double min, double max) {

        if(Double.compare(value, min) < 0)
            return min;

        if(Double.compare(value, max) > 0)
            return max;

        return value;
    }
}

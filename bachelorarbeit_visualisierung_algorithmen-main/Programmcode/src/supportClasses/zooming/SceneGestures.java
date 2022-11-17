package supportClasses.zooming;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import supportClasses.config.TempConfig;

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
    private static final double maxScale = TempConfig.MAX_ZOOM_SCALE;
    /**
     * Set minimum value of the scale.
     */
    private static final double minScale = TempConfig.MIN_ZOOM_SCALE;

    /**
     * The frame in which the {@link ZoomPane} moves. Used for getting {@link Bounds} of the frame to calculate the correct
     * pivot point.
     */
    private final Node frame;

    /**
     * Saved {@link ZoomPane} to apply changes.
     */
    private final ZoomPane zoomPane;

    public SceneGestures(ZoomPane zoomPane, Node frame) {
        this.zoomPane = zoomPane;
        this.frame = frame;
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
            scale = withinTheLimits(scale, minScale, maxScale);

            // set the scale to the new value
            zoomPane.setScale(scale);
            

            // get the frame bounds
            Bounds bounds = frame.localToScene(frame.getBoundsInLocal());
            
            // calculate the new pivot point
            double f = (scale / oldScale) - 1;
            double dx = event.getSceneX() - bounds.getMinX()
                    - zoomPane.getBoundsInParent().getWidth() / 2 - zoomPane.getBoundsInParent().getMinX();
            double dy = event.getSceneY() - bounds.getMinY()
                    - zoomPane.getBoundsInParent().getHeight() / 2 - zoomPane.getBoundsInParent().getMinY();

            // translate the ZoomPane
            zoomPane.setPivot(f * dx, f * dy);


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

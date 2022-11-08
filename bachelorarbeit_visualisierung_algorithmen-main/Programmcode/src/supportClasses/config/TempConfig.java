package supportClasses.config;

import javafx.util.Duration;

public class TempConfig {

    /**
     * Filepath of the Algorithms package.
     * <p>
     * Used in {@link controller.SelectAlgorithmController}
     */
    public static final String PATH_TO_ALGORITHMS =
            "bachelorarbeit_visualisierung_algorithmen-main/Programmcode/src/Algorithms";
    // TODO: 05.11.2022 doesn't work correctly if the package isn't named "Algorithms" because of the "setSelectedItem"
    //  method in ParentViewModel

    /**
     * The width of the window.
     * <p>
     * Used in {@link model.ParentViewModel}
     */
    public static final double WINDOW_WIDTH =
            1200;

    /**
     * The height of the window.
     * <p>
     * Used in {@link model.ParentViewModel}
     */
    public static final double WINDOW_HEIGHT =
            800;

    /**
     * Whether the window should be maximized or not.
     * <p>
     * Used in {@link model.ParentViewModel}
     */
    public static final boolean MAXIMIZED_WINDOW =
            false;


    /**
     * The distance between the elements.
     * <p>
     * Used in {@link visualization.animationCreation.AbstractAnimationCreator}
     */
    public static final int FIELD_DISTANCE =
            50;

    /**
     * The standard duration of animations. Not every animation has this duration, but each is oriented to it.
     * <p>
     * Used in {@link visualization.animationCreation.AbstractAnimationCreator}
     */
    public static final Duration STANDARD_DURATION =
            Duration.millis(100);


    /**
     * The maximal possible zoom.
     * <p>
     * Used in {@link supportClasses.zooming.SceneGestures}
     */
    public static final double MAX_ZOOM_SCALE =
            5;

    /**
     * The minimal possible zoom.
     * <p>
     * Used in {@link supportClasses.zooming.SceneGestures}
     */
    public static final double MIN_ZOOM_SCALE =
            0.1;

}

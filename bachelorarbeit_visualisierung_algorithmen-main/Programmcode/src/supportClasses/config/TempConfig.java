package supportClasses.config;

import controller.SelectAlgorithmController;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TempConfig {

    /**
     * Filepath of the Algorithms package.
     * <p>
     * Used in {@link SelectAlgorithmController#createList()}
     */
    public static final String PATH_TO_ALGORITHMS =
            "bachelorarbeit_visualisierung_algorithmen-main/Programmcode/src/Algorithms";
    // TODO: 05.11.2022 doesn't work correctly if the package isn't named "Algorithms" because of the "setSelectedItem"
    //  method in ParentViewModel

    /**
     * The width of the window.
     * <p>
     * Used in {@link model.ParentViewModel#start(Stage)}
     */
    public static final double WINDOW_WIDTH =
            1200;

    /**
     * The height of the window.
     * <p>
     * Used in {@link model.ParentViewModel#start(Stage)}
     */
    public static final double WINDOW_HEIGHT =
            800;

    /**
     * Whether the window should be maximized or not.
     * <p>
     * Used in {@link model.ParentViewModel#start(Stage)}
     */
    public static final boolean MAXIMIZED_WINDOW =
            false;


    /**
     * The distance between the elements.
     * <p>
     * Used in {@link visualization.animationCreation.AbstractAnimationCreator#fieldDistance}
     */
    public static final int FIELD_DISTANCE =
            50;

    /**
     * The standard duration of animations. Not every animation has this duration, but each is oriented to it.
     * The value must not be zero.
     * <p>
     * Used in {@link visualization.animationCreation.AbstractAnimationCreator#standardDuration}
     */
    public static final Duration STANDARD_DURATION =
            Duration.millis(1000);


    /**
     * The maximal possible zoom.
     * <p>
     * Used in {@link supportClasses.zooming.SceneGestures#maxScale}
     */
    public static final double MAX_ZOOM_SCALE =
            5;

    /**
     * The minimal possible zoom.
     * <p>
     * Used in {@link supportClasses.zooming.SceneGestures#minScale}
     */
    public static final double MIN_ZOOM_SCALE =
            0.1;


    /**
     * For visualization of trees. The x-distance between nodes.
     * Used in {@link visualization.TreeVisualization#xDistance}
     */
    public static final int NODE_X_DISTANCE = 75;

    /**
     * For visualization of trees. The y-distance between nodes.
     * Used in {@link visualization.TreeVisualization#yDistance}
     */
    public static final int NODE_Y_DISTANCE = 75;

    /**
     * For visualization of trees. The node size.
     * Used in {@link visualization.TreeVisualization#nodeSize}
     */
    public static final int NODE_SIZE = 50;

    /**
     * For visualization of trees. The nodes are visualized as circles if it's true, otherwise as rectangles.
     * Used in {@link visualization.TreeVisualization#circlesInsteadOfRectangles}
     */
    public static final boolean CIRCLES_INSTEAD_OF_RECTANGLES = true;

}

package supportClasses.config;

import controller.SelectAlgorithmController;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // doesn't work correctly if the package isn't named "Algorithms" because of "ParentViewModel.setSelectedItem()"
    /**
     * Filepath of the Algorithms package.
     * <p>
     * Used in {@link SelectAlgorithmController#createList()}
     */
    private final String pathToAlgorithms;


    // window settings

    /**
     * The width of the window.
     * <p>
     * Used in {@link model.ParentViewModel#start(Stage)}
     */
    private final double windowWidth;

    /**
     * The height of the window.
     * <p>
     * Used in {@link model.ParentViewModel#start(Stage)}
     */
    private final double windowHeight;

    /**
     * Whether the window should be maximized or not.
     * <p>
     * Used in {@link model.ParentViewModel#start(Stage)}
     */
    private final boolean maximizedWindow;


    // animation settings

    /**
     * The standard duration of animations. Not every animation has this duration, but each is oriented to it.
     * The value must not be zero.
     * <p>
     * Used in {@link visualization.animationCreation.AbstractAnimationCreator#standardDuration}
     */
    private final Duration standardDuration;


    // zoom settings

    /**
     * The maximal possible zoom.
     * <p>
     * Used in {@link supportClasses.zooming.SceneGestures#maxScale}
     */
    private final double maxZoomScale;

    /**
     * The minimal possible zoom.
     * <p>
     * Used in {@link supportClasses.zooming.SceneGestures#minScale}
     */
    private final double minZoomScale;


    // tree visualization settings

    /**
     * For visualization of trees. The x-distance between nodes.
     * Used in {@link visualization.TreeVisualization#xDistance}
     */
    private final int nodeXDistance;

    /**
     * For visualization of trees. The y-distance between nodes.
     * Used in {@link visualization.TreeVisualization#yDistance}
     */
    private final int nodeYDistance;

    /**
     * For visualization of trees. The node size.
     * Used in {@link visualization.TreeVisualization#nodeSize}
     */
    private final int nodeSize;


    /**
     * Singleton instance.
     */
    private static final ConfigReader instance = new ConfigReader();


    private ConfigReader() {
        // read the config file
        Properties prop = new Properties();
        String fileName = "bachelorarbeit_visualisierung_algorithmen-main/Programmcode/src/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("Error in reading the config file: FileNotFound");
        } catch (IOException ex) {
            System.out.println("Error in reading the config file: IOException");
        }
        pathToAlgorithms =
                prop.getProperty("path_to_algorithms");
        windowWidth =
                Double.parseDouble(prop.getProperty("window_width"));
        windowHeight =
                Double.parseDouble(prop.getProperty("window_height"));
        maximizedWindow =
                Boolean.parseBoolean(prop.getProperty("maximized_window"));
        standardDuration =
                Duration.millis(Integer.parseInt(prop.getProperty("animation_standard_duration")));
        maxZoomScale =
                Double.parseDouble(prop.getProperty("max_zoom_scale"));
        minZoomScale =
                Double.parseDouble(prop.getProperty("min_zoom_scale"));
        nodeXDistance =
                Integer.parseInt(prop.getProperty("tree_node_distance_x"));
        nodeYDistance =
                Integer.parseInt(prop.getProperty("tree_node_distance_y"));
        nodeSize =
                Integer.parseInt(prop.getProperty("tree_node_size"));
    }

    /**
     * @return The instance of the singleton.
     */
    private static ConfigReader getInstance() {
        return instance;
    }


    public static String getPathToAlgorithms() {
        return getInstance().pathToAlgorithms;
    }

    public static double getWindowWidth() {
        return getInstance().windowWidth;
    }

    public static double getWindowHeight() {
        return getInstance().windowHeight;
    }

    public static boolean isMaximizedWindow() {
        return getInstance().maximizedWindow;
    }

    public static Duration getStandardDuration() {
        return getInstance().standardDuration;
    }

    public static double getMaxZoomScale() {
        return getInstance().maxZoomScale;
    }

    public static double getMinZoomScale() {
        return getInstance().minZoomScale;
    }

    public static int getNodeXDistance() {
        return getInstance().nodeXDistance;
    }

    public static int getNodeYDistance() {
        return getInstance().nodeYDistance;
    }

    public static int getNodeSize() {
        return getInstance().nodeSize;
    }
}

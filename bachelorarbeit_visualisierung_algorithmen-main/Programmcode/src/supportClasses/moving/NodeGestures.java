package supportClasses.moving;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Listeners for making the nodes draggable via left mouse button.
 * <p></p>
 * From
 * <a href="https://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer">here</a>
 * as "NodeGestures"
 */
public class NodeGestures {

    /**
     * Saved anchor points.
     */
    private final DragContext nodeDragContext = new DragContext();

    /**
     * Node that will be dragged by the events.
     */
    Node draggedNode;

    public NodeGestures(Node draggedNode) {
        this.draggedNode = draggedNode;

    }

    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    /**
     * Saves start point and translation in the nodeDragContext.
     */
    private final EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<>() {

        public void handle(MouseEvent event) {

            // left mouse button => dragging
            if (! event.isPrimaryButtonDown())
                return;

            // save start point
            nodeDragContext.mouseAnchorX = event.getSceneX();
            nodeDragContext.mouseAnchorY = event.getSceneY();

            // save start translation
            nodeDragContext.translateAnchorX = draggedNode.getTranslateX();
            nodeDragContext.translateAnchorY = draggedNode.getTranslateY();
        }

    };

    /**
     * Moves the node relative to the saved data in nodeDragContext.
     */
    private final EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<>() {

        public void handle(MouseEvent event) {

            // left mouse button => dragging
            if (! event.isPrimaryButtonDown())
                return;

            // move node
            draggedNode.setTranslateX(nodeDragContext.translateAnchorX + event.getSceneX() - nodeDragContext.mouseAnchorX);
            draggedNode.setTranslateY(nodeDragContext.translateAnchorY + event.getSceneY() - nodeDragContext.mouseAnchorY);

            event.consume();
        }

    };
}

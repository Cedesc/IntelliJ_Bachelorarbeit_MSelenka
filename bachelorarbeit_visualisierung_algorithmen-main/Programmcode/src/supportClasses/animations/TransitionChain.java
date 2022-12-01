package supportClasses.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

/**
 * Chain of multiple transitions, so the transitions can be easily managed.
 */
public class TransitionChain {

    /**
     * Array of the transitions to be played.
     */
    private final ArrayList<Transition> transitions = new ArrayList<>();


    /**
     * Adds a transition to the chain.
     * @param newTransition the transition to be added
     */
    public void addTransition(Transition newTransition) {
        transitions.add(newTransition);
    }

    /**
     * Adds multiple transitions to the chain.
     * @param newTransition the transitions to be added
     */
    public void addAllTransitions(Transition... newTransition) {
        for (Transition transition : newTransition) {
            addTransition(transition);
        }
    }

    /**
     * Sets all translations to their endpoint.
     */
    public void endAll() {
        for (Transition transition : transitions) {
            transition.jumpTo("end");
        }
    }

    /**
     * Sets all onFinishedEvents to the given event.
     * @param eventHandler event, that will be executed if the transition is finished
     */
    public void setAllSetOnFinishedEvents(EventHandler<ActionEvent> eventHandler) {
        for (Transition transition : transitions) {
            transition.setOnFinished(eventHandler);
        }
    }

}

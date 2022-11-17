package supportClasses.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

// TODO: 26.09.2022 "played correctly one after the other" isn't necessary anymore, since the command executions are
//  called by the onFinished-Events, so the animations are created one by one
//  => write this as docstring
/**
 * Chain of multiple transitions, so the transitions can be easily managed and played correctly one after the other.
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
     * The delay of each transition is set so that they can be played correctly one after the other.
     */
    private void calculateDelaysForCompleteVisualization() {

        Duration delaySum = Duration.millis(0);

        for (Transition transition : transitions) {
            transition.setDelay(delaySum);
            delaySum = delaySum.add(transition.getTotalDuration());
        }

    }

    /**
     * Starts all transitions.
     */
    private void startAnimation() {
        for (Transition transition : transitions) {
            transition.play();
        }
    }

    /**
     * Calculates and sets the delay of each transition and starts the full animation chain.
     */
    public void playOneByOne() {
        calculateDelaysForCompleteVisualization();
        startAnimation();
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

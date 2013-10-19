package timer.states;

/**
 * @author adkozlov
 */
public class StoppedState extends State {

    public static final String NAME = "stopped";

    public StoppedState(long previousTimeMillis) {
        super(previousTimeMillis);
    }

    public StoppedState(long stopTime, StartedState previousState) {
        super(stopTime - previousState.getStartTime() + previousState.getPreviousTimeMillis());
    }

    @Override
    public long getTimeMillis() {
        return getPreviousTimeMillis();
    }

    @Override
    public String getName() {
        return NAME;
    }
}

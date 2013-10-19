package timer.states;

/**
 * @author adkozlov
 */
public class StartedState extends State {

    public static final String NAME = "started";

    private final long startTime;

    public StartedState(long previousTimeMillis, long startTime) {
        super(previousTimeMillis);
        this.startTime = startTime;
    }

    public StartedState(long startTime, State previousState) {
        super(previousState.getPreviousTimeMillis());
        this.startTime = startTime;
    }

    @Override
    public long getTimeMillis() {
        return System.currentTimeMillis() - getStartTime() + getPreviousTimeMillis();
    }

    @Override
    public String getName() {
        return NAME;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + startTime;
    }
}

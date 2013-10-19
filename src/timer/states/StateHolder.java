package timer.states;

import java.io.*;

/**
 * @author adkozlov
 */
public class StateHolder {

    public static final String SOURCE_NAME = "ti.mer";
    public static final String SOURCE_PATH = "./";

    private static final StateHolder INSTANCE = new StateHolder();

    public synchronized static StateHolder getInstance() throws IOException {
        if (state == InitialState.getInstance()) {
            if (source.exists()) {
                state = State.readFromFile(source);
            }
        }

        return INSTANCE;
    }

    private static State state = InitialState.getInstance();
    private static final File source = new File(SOURCE_PATH + SOURCE_NAME);

    public long getTimeMillis() {
        return state.getTimeMillis();
    }

    public void onStartClick() throws IOException {
        if (!(state instanceof StartedState)) {
            state = new StartedState(System.currentTimeMillis(), state);
            state.writeToFile(source);
        }
    }

    public void onStopClick() throws IOException {
        if (!(state instanceof StoppedState)) {
            if (state instanceof StartedState) {
                state = new StoppedState(System.currentTimeMillis(), (StartedState) state);
            } else {
                state = new StoppedState(System.currentTimeMillis());
            }
            state.writeToFile(source);
        }
    }
}
package timer.states;

import java.io.*;
import java.util.Scanner;

/**
 * @author adkozlov
 */
abstract class State implements IState {

    private final long previousTimeMillis;

    protected State(long previousTimeMillis) {
        this.previousTimeMillis = previousTimeMillis;
    }

    public long getPreviousTimeMillis() {
        return previousTimeMillis;
    }

    @Override
    public String toString() {
        return getName() + "\n" + previousTimeMillis;
    }

    public static State readFromFile(File file) throws IOException {
        Scanner reader = new Scanner(file);
        String name = reader.next();

        switch (name) {
            case StartedState.NAME:
                return new StartedState(reader.nextLong(), reader.nextLong());
            case StoppedState.NAME:
                return new StoppedState(reader.nextLong());
            default:
                throw new IOException("illegal state name: " + name);
        }
    }

    public void writeToFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(toString());
        writer.close();
    }
}

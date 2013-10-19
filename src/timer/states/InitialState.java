package timer.states;

/**
 * @author adkozlov
 */
public class InitialState extends State {

    public static final String NAME = "initial";

    private static final InitialState INSTANCE = new InitialState();

    private InitialState() {
        super(0);
    }

    public synchronized static InitialState getInstance() {
        return INSTANCE;
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

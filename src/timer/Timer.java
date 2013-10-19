package timer;

import timer.states.StateHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TimerTask;

/**
 * @author adkozlov
 */
public class Timer extends JFrame {

    public static final String APPLICATION_LABEL = "Timer";
    public static final String START_BUTTON_LABEL = "Start";
    public static final String STOP_BUTTON_LABEL = "Stop";
    private static final long ONE_SECOND = 1000;

    public static void main(String[] args) {
        new Timer();
    }

    private final JTextField timeField = new JTextField();
    private final JButton startButton = new JButton(START_BUTTON_LABEL);
    private final JButton stopButton = new JButton(STOP_BUTTON_LABEL);

    public Timer() {
        super(APPLICATION_LABEL);

        Container content = getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new GridLayout());

        timeField.setEditable(false);
        timeField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeField.setHorizontalAlignment(JTextField.CENTER);

        content.add(timeField);
        content.add(startButton);
        content.add(stopButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StateHolder.getInstance().onStartClick();
                } catch (IOException exc) {
                    System.err.println(exc);
                }
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StateHolder.getInstance().onStopClick();
                } catch (IOException exc) {
                    System.err.println(exc.getMessage());
                }
            }
        });

        new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    timeField.setText(String.valueOf(StateHolder.getInstance().getTimeMillis() / ONE_SECOND));
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }, 0, ONE_SECOND);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
}

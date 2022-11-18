package ui.view.ViewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// displays and controls buttons on side panel
public class ControlPanel extends JPanel {

    JButton startButton;
    JButton stopButton;
    JButton quitButton;

    public ControlPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setFocusable(false);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        quitButton = new JButton("Quit");

        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);

        disableStopButton();
    }

    // MODIFIES: this
    // EFFECTS: disables start button
    public void disableStartButton() {
        startButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: disables stop button
    public void disableStopButton() {
        stopButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: disables quit button
    public void disableQuitButton() {
        quitButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: enables start button
    public void enableStartButton() {
        startButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: enables stop button
    public void enableStopButton() {
        stopButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: enables quit button
    public void enableQuitButton() {
        quitButton.setEnabled(true);
    }

    // EFFECTS: registers when start button pressed
    public void addStartButtonListener(ActionListener listenForStartButton) {
        startButton.addActionListener(listenForStartButton);
    }

    // EFFECTS: registers when stop button pressed
    public void addStopButtonListener(ActionListener listenForStopButton) {
        stopButton.addActionListener(listenForStopButton);
    }

    // EFFECTS: registers when quit button pressed
    public void addQuitButtonListener(ActionListener listenForQuitButton) {
        quitButton.addActionListener(listenForQuitButton);
    }
}

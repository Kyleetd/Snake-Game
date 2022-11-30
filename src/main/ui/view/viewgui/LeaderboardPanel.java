package ui.view.viewgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

// Displays and updates leaderboard.
public class LeaderboardPanel extends JPanel {

    JLabel title;
    JLabel[] leaderboard;

    JLabel loadLeaderboardLabel;
    JButton loadButton;
    JButton dontLoadButton;

    JLabel saveLeaderboardLabel;
    JButton saveButton;
    JButton dontSaveButton;

    JButton clearLeaderboardButton;

    public LeaderboardPanel() {
        this.setLayout(new GridLayout(12, 1));
        this.setBackground(Color.white);
        this.setFocusable(false);

        title = new JLabel("Leaderboard", SwingConstants.CENTER);
        leaderboard = new JLabel[10];

        loadLeaderboardLabel = new JLabel("Load Previous Leaderboard?", SwingConstants.CENTER);
        loadButton = new JButton("Load");
        dontLoadButton = new JButton("Don't Load");

        saveLeaderboardLabel = new JLabel("Save Leaderboard?", SwingConstants.CENTER);
        saveButton = new JButton("Save");
        dontSaveButton = new JButton("Don't Save");

        clearLeaderboardButton = new JButton("Clear Leaderboard");

        loadLeaderboardOption();
    }

    // MODIFIES: This.
    // EFFECTS: Displays Leaderboard in LeaderboardPanel.
    public void loadLeaderboard() {
        this.removeAll();
        this.add(title);
        initializeLeaderboard();
    }

    // MODIFIES: This.
    // EFFECTS: Displays loadLeaderboardLabel with load or don't load options.
    public void loadLeaderboardOption() {
        this.removeAll();
        this.add(loadLeaderboardLabel);
        this.add(loadButton);
        this.add(dontLoadButton);
        reloadLeaderboard();
    }

    // MODIFIES: This.
    // EFFECTS: Displays saveLeaderboardLabel with save or don't save options.
    public void loadSaveLeaderboardOption() {
        this.removeAll();
        this.add(saveLeaderboardLabel);
        this.add(saveButton);
        this.add(dontSaveButton);
        reloadLeaderboard();
    }

    // MODIFIES: This.
    // EFFECTS: Creates Leaderboard in LeaderboardPanel.
    public void initializeLeaderboard() {
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i] = new JLabel("", SwingConstants.CENTER);
            this.add(leaderboard[i]);
        }
        this.add(clearLeaderboardButton);
        reloadLeaderboard();
    }

    // MODIFIES: This.
    // EFFECTS: Adds leaderboard entries to entries panel.
    public void updateLeaderboard(List<String> leaderboardEntries) {
        for (int i = 0; i < leaderboardEntries.size(); i++) {
            leaderboard[i].setText(leaderboardEntries.get(i));
        }
        reloadLeaderboard();
    }

    // EFFECTS: Resets and reloads LeaderboardPanel.
    public void reloadLeaderboard() {
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: Registers when Load button is pressed.
    public void addLoadButtonListener(ActionListener listenLoadButton) {
        loadButton.addActionListener(listenLoadButton);
    }

    // EFFECTS: Registers when Don't Load button is pressed.
    public void addDontLoadButtonListener(ActionListener listenDontLoadButton) {
        dontLoadButton.addActionListener(listenDontLoadButton);
    }

    // EFFECTS: Registers when Save button is pressed.
    public void addSaveButtonListener(ActionListener listenSaveButton) {
        saveButton.addActionListener(listenSaveButton);
    }

    // EFFECTS: Registers when Clear Leaderbaord button is pressed.
    public void addClearLeaderboardListener(ActionListener listenClearLeaderboardButton) {
        clearLeaderboardButton.addActionListener(listenClearLeaderboardButton);
    }

    // EFFECTS: Registers when Don't Save button is pressed.
    public void addDontSaveButtonListener(ActionListener listenDontSaveButton) {
        dontSaveButton.addActionListener(listenDontSaveButton);
    }
}

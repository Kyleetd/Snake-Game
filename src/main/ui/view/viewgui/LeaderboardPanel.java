package ui.view.viewgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

// Displays and updates leaderboard.
public class LeaderboardPanel extends JPanel {

    JLabel title;
    JLabel[] leaderboard;
    JButton viewLeaderboardButton;

    JLabel loadLeaderboardLabel;
    JButton loadButton;
    JButton dontLoadButton;

    public LeaderboardPanel() {
        this.setLayout(new GridLayout(11, 1));
        this.setBackground(Color.white);
        this.setFocusable(false);

        title = new JLabel("Leaderboard", SwingConstants.CENTER);
        leaderboard = new JLabel[10];

        loadLeaderboardLabel = new JLabel("Load Previous Leaderboard?", SwingConstants.CENTER);
        loadButton = new JButton("Load");
        dontLoadButton = new JButton("Don't Load");

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
    // EFFECTS: Creates Leaderboard in LeaderboardPanel.
    public void initializeLeaderboard() {
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i] = new JLabel("", SwingConstants.CENTER);
            this.add(leaderboard[i]);
        }
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

    // EFFECTS: Registers when View Leaderboard button is pressed.
    public void addViewLeaderboardButtonListener(ActionListener listenViewLeaderboardButton) {
        viewLeaderboardButton.addActionListener(listenViewLeaderboardButton);
    }

    // EFFECTS: Registers when Load button is pressed.
    public void addLoadButtonListener(ActionListener listenLoadButton) {
        loadButton.addActionListener(listenLoadButton);
    }

    // EFFECTS: Registers when Don't Load button is pressed.
    public void addDontLoadButtonListener(ActionListener listenDontLoadButton) {
        dontLoadButton.addActionListener(listenDontLoadButton);
    }
}

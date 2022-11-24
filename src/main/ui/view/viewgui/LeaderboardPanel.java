package ui.view.viewgui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Displays and updates leaderboard
public class LeaderboardPanel extends JPanel {

    JLabel title;
    JLabel[] leaderboard;

    public LeaderboardPanel() {
        this.setLayout(new GridLayout(11, 1));
        this.setBackground(Color.white);
        this.setFocusable(false);

        title = new JLabel("Leaderboard", SwingConstants.CENTER);
        leaderboard = new JLabel[10];

        this.add(title);
        initializeLeaderboard();
    }

    // EFFECTS: Creates Leaderboard in LeaderboardPanel
    public void initializeLeaderboard() {
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i] = new JLabel("", SwingConstants.CENTER);
            this.add(leaderboard[i]);
        }
    }

    // MODIFIES: This
    // EFFECTS: Adds leaderboard entries to entries panel
    public void updateLeaderboard(List<String> leaderboardEntries) {
        for (int i = 0; i < leaderboardEntries.size(); i++) {
            leaderboard[i].setText(leaderboardEntries.get(i));
        }
    }
}

package ui.view.viewgui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

// displays side panel with buttons and leaderboard
public class SidePanel extends JPanel {

    ControlPanel controlPanel;
    LeaderboardPanel leaderboardPanel;

    public SidePanel() {
        this.setPreferredSize(new Dimension(200, 200));
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setFocusable(false);

        controlPanel = new ControlPanel();
        leaderboardPanel = new LeaderboardPanel();

        this.add(controlPanel);
        this.add(leaderboardPanel);
    }
}

package ui;

import model.LeaderboardModel;
import model.SnakeModel;
import persistence.JsonReader;
import ui.controller.ControllerCMD;
import ui.controller.ControllerGUI;
import ui.view.ViewCMD;
import ui.view.ViewGUI.ViewGUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SnakeModel snakeModel = new SnakeModel();
        LeaderboardModel leaderboardModel = new LeaderboardModel();
        ViewGUI viewGUI = new ViewGUI(snakeModel);
        new ControllerGUI(snakeModel, leaderboardModel, viewGUI);

        viewGUI.setVisible(true);
    }
}

package ui;

import model.LeaderboardModel;
import model.SnakeModel;
import persistence.JsonReader;
import ui.controller.ControllerGUI;
import ui.view.ViewGUI.ViewGUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SnakeModel snakeModel = new SnakeModel();
        LeaderboardModel leaderboardModel = new LeaderboardModel();

        JsonReader reader = new JsonReader("./data/leaderboard.json");
        leaderboardModel.loadJson(reader.read());

        ViewGUI viewGUI = new ViewGUI(snakeModel);
        new ControllerGUI(snakeModel, leaderboardModel, viewGUI);

        viewGUI.setVisible(true);

//        SnakeModel snakeModel = new SnakeModel();
//        ViewCMD view = new ViewCMD();
//        RenderSnake render = new RenderSnake(snakeModel);
//        ControllerCMD controller = new ControllerCMD(snakeModel, leaderboardModel, view, render);
//
//        controller.startMainLoop();
    }
}

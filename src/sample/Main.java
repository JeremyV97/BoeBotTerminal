package sample;

import Controller.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Gui gui = new Gui(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}

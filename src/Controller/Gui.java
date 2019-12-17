package Controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Gui {

    private static Stage primaryStage;

    public Gui(Stage primaryStage){
        this.primaryStage = primaryStage;
        RouteController route = new RouteController();
    }


    public static void setScene(Pane pane, String title, int x, int y){
        primaryStage.setScene(new Scene(pane, x, y));
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public static void setScene(Pane pane){
        primaryStage.setScene(new Scene(pane, 600, 900));
        primaryStage.setTitle("BoeBot Terminal");
        primaryStage.show();
    }
}

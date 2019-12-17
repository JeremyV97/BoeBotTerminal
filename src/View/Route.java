package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Controller.Gui;

public class Route {

    public final Pane root;
    public TextField txtX, txtY;
    public Button btnSubmit;

    public Route(){
        root = new Pane();
        Gui.setScene(root, "BoeBot Terminal RouteController View", 600, 900);

        txtX = new TextField();
        txtY = new TextField();
        btnSubmit = new Button("Drive to coordinates");

        txtX.relocate(20, 20);
        txtY.relocate(20, 50);
        btnSubmit.relocate(20, 80);

        root.getChildren().addAll(txtX, txtY, btnSubmit);
    }
}

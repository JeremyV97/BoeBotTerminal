package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Controller.Gui;

import java.util.IllegalFormatConversionException;

public class Route {

    public final Pane root;
    public Label lblX, lblY, lblFirstCoordinates;
    public TextField txtX, txtY;
    public Button btnSubmit;

    public Route(){
        root = new Pane();
        Gui.setScene(root, "BoeBot Terminal RouteController View", 600, 900);

        lblX = new Label("X Coordinate: ");
        lblY = new Label("Y Coordinate: ");
        lblFirstCoordinates = new Label("Fill in the set of coordinates. X between 0 and 4, Y between 0 and 6");
        txtX = new TextField();
        txtY = new TextField();
        btnSubmit = new Button("Drive to coordinates");

        lblFirstCoordinates.relocate(80, 0);
        lblX.relocate(0, 20);
        lblY.relocate(0, 50);
        txtX.relocate(80, 20);
        txtY.relocate(80, 50);
        btnSubmit.relocate(80, 80);

        root.getChildren().addAll(lblFirstCoordinates, lblX, lblY, txtX, txtY, btnSubmit);
    }

    public int getX(){
        try {
            return Integer.parseInt(this.txtX.getText());
        }catch(Exception ex){
            txtX.clear();
            return -1;
        }
    }

    public int getY(){
        try {
            return Integer.parseInt(this.txtY.getText());
        }catch(Exception ex){
            txtX.clear();
            return -1;
        }
    }
}

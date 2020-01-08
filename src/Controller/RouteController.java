package Controller;

import Classes.DatabaseController;
import Model.Obstacle;
import View.Route;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteController {


    private Route route;
    private List<Obstacle> obstacles;
    private String databaseIP;
    int endCoordinateX = 0;
    int endCoordinateY = 0;
    int gridSizeX = 8;
    int gridSizeY = 5;

    public RouteController(){
        obstacles = new ArrayList<Obstacle>();
        route = new Route();
        clearDatabase();
        route.btnDrive.setOnAction(event -> {
            getCoordinates();
            getDatabaseIP();
            syncAllCoordinates();
        });

        route.btnAddObstacle.setOnAction(event -> {
            saveObsticle();
        });

        route.btnGridUpdate.setOnAction(event ->{
            try {
                this.gridSizeX = Integer.parseInt(route.txtGridSizeX.getText());
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR, "Given grid size for X is unacceptable").showAndWait();
                return;
            }
            try {
                this.gridSizeY = Integer.parseInt(route.txtGridSizeY.getText());
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR, "Given grid size for Y is unacceptable").showAndWait();
                return;
            }
        });

        route.btnClearDatabase.setOnAction(event->{
            clearDatabase();
        });
    }

    private void getCoordinates(){
        int x = route.getX();
        int y = route.getY();
        if((x < 0) || (y < 0)){
            //Invalid coordinates
            System.out.println("Invalid, coordinates can't be lower than 0");
            return;
        }
        if (x > gridSizeX || y > gridSizeY){
            System.out.println("Invalid, coordinates can't be higher than the given gridsize");
            return;
        }
        System.out.println("Uploading coordinates X: " + x + ", Y: " + y);
        endCoordinateX = x;
        endCoordinateY = y;
    }

    private String getDatabaseIP(){
        this.databaseIP = route.txtDatabaseIP.getText();
        return this.databaseIP;
    }

    private void saveObsticle(){
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        try {
            x1 = Integer.parseInt(route.txtObX1.getText());
            x2 = Integer.parseInt(route.txtObX2.getText());
            y1 = Integer.parseInt(route.txtObY1.getText());
            y2 = Integer.parseInt(route.txtObY2.getText());
        }catch(Exception ex){
            System.out.println("Type in valid integers");
            return;
        }

        if(x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0){
            System.out.println("Invalid coordinates, integer(s) is(are) too low, it has to be between the given grid size");
            new Alert(Alert.AlertType.ERROR, "Invalid coordinates, integer(s) is(are) too low, it has to be between the given grid size").showAndWait();
            return;
        }
        if(x1 > gridSizeX || x2 > gridSizeX || y1 > gridSizeY || y2 > gridSizeY){
            System.out.println("Invalid coordinates, integer(s) is(are) too high, it has to be between the given grid size");
            new Alert(Alert.AlertType.ERROR, "Invalid coordinates, integer(s) is(are) too hight, it has to be between the given grid size").showAndWait();
            return;
        }

        route.txtObX1.clear();
        route.txtObX2.clear();
        route.txtObY1.clear();
        route.txtObY2.clear();

        obstacles.add(new Obstacle(x1, y1, x2, y2));
        System.out.println("Obstacle has been added, click on upload once you are ready");
        new Alert(Alert.AlertType.INFORMATION, "Obstacle has been added, click on upload once you are ready!").showAndWait();
    }

    private void syncAllCoordinates(){
        //Now we upload all the coordinates to the database so the boebot can download them
        String sqlPath = "INSERT INTO path VALUES (?, ?);";
        DatabaseController con = new DatabaseController(getDatabaseIP(),"3306",  "boebot", "root", "");

        try {
            ArrayList endCoordinates = new ArrayList<>();
            endCoordinates.add(endCoordinateX);
            endCoordinates.add(endCoordinateY);
            con.preparedStatement(sqlPath, endCoordinates);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Upload every obstacle
        for(Obstacle o : obstacles){
            String sqlObstacle = "INSERT INTO obstakel VALUES (?, ?, ?, ?);";
            ArrayList obstacleList = new ArrayList<>();
            obstacleList.add(o.getX1());
            obstacleList.add(o.getY1());
            obstacleList.add(o.getX2());
            obstacleList.add(o.getY2());
            try {
                con.preparedStatement(sqlObstacle, obstacleList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearDatabase(){
        DatabaseController con = new DatabaseController(getDatabaseIP(),"3306",  "boebot", "root", "");
        try {
            con.preparedStatement("DELETE FROM path", new ArrayList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.preparedStatement("DELETE FROM obstakel", new ArrayList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

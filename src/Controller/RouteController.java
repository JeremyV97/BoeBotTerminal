package Controller;

import Model.Obstacle;
import View.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteController {

    //Grid grootte 4 x 6

    private Route route;
    private List<Obstacle> obstacles;

    public RouteController(){
        obstacles = new ArrayList<Obstacle>();
        route = new Route();

        route.btnDrive.setOnAction(event -> {
            getCoordinates();
        });

        route.btnAddObstacle.setOnAction(event -> {
            saveObsticle();
        });
    }

    private void getCoordinates(){
        int x = route.getX();
        int y = route.getY();
        if((x == -1) || (y == -1)){
            //Invalid coordinates
            System.out.println("Invalid coordinates");
            return;
        }
        System.out.println(x + " " + y);
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
            System.out.println("Invalid coordinates, integer(s) is(are) too low");
            return;
        }

        //TODO: add cases for integers that are too high

        route.txtObX1.clear();
        route.txtObX2.clear();
        route.txtObY1.clear();
        route.txtObY2.clear();

        obstacles.add(new Obstacle(x1, y1, x2, y2));
        System.out.println("Obstacle has been added locally");
    }
}

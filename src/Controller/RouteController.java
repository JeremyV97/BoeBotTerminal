package Controller;

import View.Route;

public class RouteController {

    //Grid grootte 4 x 6

    private Route route;

    public RouteController(){
        route = new Route();

        route.btnSubmit.setOnAction(event -> {
            getCoordinates();
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
}

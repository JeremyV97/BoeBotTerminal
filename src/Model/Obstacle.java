package Model;

public class Obstacle {

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Obstacle(int x1, int y1, int x2, int y2){
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
    }

    public int getX1(){
        return this.x1;
    }

    public void setX1(int x){
        this.x1 = x;
    }

    public int getX2(){
        return this.x2;
    }

    public void setX2(int x){
        this.x2 = x;
    }

    public int getY1(){
        return this.y1;
    }

    public void setY1(int y){
        this.y1 = y;
    }

    public int getY2(){
        return this.y2;
    }

    public void setY2(int y){
        this.y2 = y;
    }
}

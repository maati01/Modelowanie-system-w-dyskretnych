import java.util.Random;

public class Point {
    int type;
    Point next;
    boolean moved;
    int speed = 1;
    static final int max_speed = 5;
    Random rand = new Random();

    public void move() {
        if(type == 1 && !moved) {
            if(speed < max_speed){
                speed += 1;
            }

            int distance = check_distance(this.next);

            if(distance < speed){
                speed = distance;
            }

            if(speed >= 1 && rand.nextInt(4)==0){
                speed -= 1;
            }

            type = 0;
            Point nextPoint = next_point();
            nextPoint.type = 1;
            nextPoint.speed = speed;
            moved = nextPoint.moved = true;
            speed = 0;

        }

    }

    Point next_point(){
        Point point = this;
        for(int i = 0; i < speed; i++){
            point = point.next;
        }
        return point;
    }

    int check_distance(Point neighbour){
        int distance = 0;
        while(neighbour.type == 0 && distance < max_speed){
            neighbour = neighbour.next;
            distance += 1;

        }
        return distance;
    }

    public void clicked() {
        this.type = 1;
    }

    public void clear() {
        this.type = 0;
    }
}


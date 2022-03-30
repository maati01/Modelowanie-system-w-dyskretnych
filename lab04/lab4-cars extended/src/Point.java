import java.util.Random;

public class Point {
    int type;
    Point next;
    Point prev;
    Point up;
    Point down;
    boolean moved;
    int speed;
    int max_speed;
    String side;
    public static Integer[] types = {0, 1, 2, 3, 5};

    public Point(){}

    public Point(int type){
        this.type = type;
        switch (type){
            case 1 -> this.speed = this.max_speed = 3;
            case 2 -> this.speed = this.max_speed = 5;
            case 3 -> this.speed = this.max_speed = 7;
        }

    }

    public Point(int type, String side){
        this.type = type;
        this.side = side;
        switch (type){
            case 1 -> this.speed = this.max_speed = 3;
            case 2 -> this.speed = this.max_speed = 5;
            case 3 -> this.speed = this.max_speed = 7;
        }
    }

    public void move() {
        Point nextPoint;
        int distance;
        if(type != 0 && type != 5 && !moved) {
            if(speed < max_speed){
                speed += 1;
            }


            distance = check_distance(this.next);
            if(distance < speed){
                speed = distance - 1;
            }
            if(side.equals("right") && speed < max_speed && prev_distance(this.prev) >= max_speed
                    && prev_distance(this.up.prev) >= max_speed && check_distance(this.up) >= speed){
                nextPoint = next_point(this.up);
                distance = check_distance(this.up);
            }else if(side.equals("left") && prev_distance(this.down.prev) >= max_speed && prev_distance(this.prev) >= max_speed
                && check_distance(this) >= speed){
                nextPoint = next_point(this.down);
            }else{
                nextPoint = next_point(this);
            }

            nextPoint.type = type;
            nextPoint.speed = speed;
            nextPoint.max_speed = max_speed;
            moved = nextPoint.moved = true;
            speed = 0;
            if (distance > 0){
                type = 0;
            }

        }

    }

    Point next_point(Point point){
        Point curr_point = point;
        for(int i = 0; i < speed; i++){
            curr_point = curr_point.next;
        }
        return curr_point;
    }

    int check_distance(Point neighbour){
        int distance = 0;
        while(neighbour.type == 0 && distance <= max_speed){
            neighbour = neighbour.next;
            distance += 1;
        }
        return distance;
    }

    int prev_distance(Point neighbour){
        int distance = 0;
        while(neighbour.type == 0 && distance <= max_speed){
            neighbour = neighbour.prev;
            distance += 1;
        }
        return distance;
    }


    public void clicked() {
        this.type = 0;
    }

    public void clear() {
        this.type = 0;
    }
}


package ttanja.vehicle;

import ttanja.people.User;

import java.util.StringJoiner;

public class Car {
    private String model;
    private int maxSpeed;
    private User owner;

    public Car() {
        this.model = "Granta";
        this.maxSpeed = 80;
        this.owner = null;
    }

    public Car(String model, int maxSpeed, User owner) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.owner = owner;
    }

    public int move(long distance, int sleep) {
        while (distance-- > 0){
            System.out.print(".");
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
        return (0);
    }

    public void doSignal(){
        System.out.println("Beep");
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model='" + model +"'")
                .add("max speed='" + maxSpeed + "'")
                .add("Owner='" + owner +"'")
                .toString();
    }
}

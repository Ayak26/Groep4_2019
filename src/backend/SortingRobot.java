package backend;

public class SortingRobot extends Robot{

    private Servo servos[];


    public SortingRobot(String port, int servos) {
        super(port);
        super.on = false;
        this.servos = new Servo[servos];
        for (int i = 0; i < servos; i++) {
            this.servos[i] = new Servo();
        }
    }

}

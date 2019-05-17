package backend;

public class PackingRobot extends Robot {

    private Servo servos[];


    private Order order;

    public PackingRobot(String port, Order order, int servos) {
        super(port);
        this.order = order;
        super.on = false;
        this.servos = new Servo[servos];
        for (int i = 0; i < servos; i++) {
            this.servos[i] = new Servo();
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isOn() {
        return on;
    }

    public Order getOrder() {
        return order;
    }
}

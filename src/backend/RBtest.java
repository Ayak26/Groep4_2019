package backend;

public class RBtest {
    public RBtest() {

        Robot inpakrobot1 = new PackingRobot("COM4");

        if (inpakrobot1.openConnection()) {

            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inpakrobot1.closeConnection();
        } else {
            System.out.println("pech gehad");
        }
    }
}
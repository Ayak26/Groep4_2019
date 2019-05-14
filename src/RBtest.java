public class RBtest {
public RBtest() {

    Robot inpakrobot1 = new Robot("COM7");

    if (inpakrobot1.openConnection()) {

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inpakrobot1.closeConnection();
    } else {
        System.out.println("pech gehad");
    }
}
}

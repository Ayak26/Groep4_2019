package gui.home;

public class LogoThread extends Thread {
    public void run() {
        System.out.println("Logo thread is running");
        SortRobot Bot = new SortRobot();

    }
}

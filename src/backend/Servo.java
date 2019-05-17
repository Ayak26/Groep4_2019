package backend;

public class Servo {

    private boolean open;

    public Servo() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

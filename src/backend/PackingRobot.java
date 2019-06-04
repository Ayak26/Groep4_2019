package backend;


public class PackingRobot extends Robot {

    private Order order;
    private boolean servo1 = false, servo2 = false;

    private Box[] boxes;

    public PackingRobot(String port) {
        super(port);
        boxes = new Box[3];
        boxes[0] = new Box(5, "one");
        boxes[1] = new Box(5, "two");
        boxes[2] = new Box(5, "three");
    }

    @Override
    protected void readCommand(String command) {

        if (command.equals("AMON\r")) {
            System.out.println("Robot is aan");
        } else if (command.equals("AMOFF\r")) {
            System.out.println("Robot is uit");
        } else if (command.equals("S1:OPEN\r")) {
            servo1 = true;
            System.out.println("Servo 1 is open");
        } else if (command.equals("S1:CLOSED\r")) {
            servo1 = false;
            System.out.println("Servo 1 is dicht");
        } else if (command.equals("S2:OPEN\r")) {
            servo2 = true;
            System.out.println("Servo 2 is open");
        } else if (command.equals("S2:CLOSED\r")) {
            servo2 = false;
            System.out.println("Servo 2 is dicht");
        }
    }

    public void setOrder(int order_id) {
        System.out.println("order laden");
        order = new Order(order_id);
        if (packOrder(order)) {
            System.out.println("gelukt");
        } else {
            order = null;
            boxes[0].content.clear();
            boxes[1].content.clear();
            boxes[2].content.clear();
            System.out.println("past niet");
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setBoxes(int size) {
        boxes[0].setSize(size);
        boxes[1].setSize(size);
        boxes[2].setSize(size);
    }

    public boolean packOrder(Order order) {

        Article[] article_list = order.getArticle_list();

        long startTime = System.nanoTime();

        for (Article article : article_list) {
            Box best_fit = bestFit(article);
            if (best_fit != null) {
                best_fit.addContent(article);
            } else {
                return false;
            }
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration);
        System.out.println(boxes[0].toString());
        System.out.println(boxes[1].toString());
        System.out.println(boxes[2].toString());

        return true;
    }


    /**
     * First check of all boxes are empty, next loop through the boxes and check if the current box is full.
     * <p>
     * Now he checks if this is the first box or if this specific box is empty, because if so he sets this box as best fit.
     * After that he checks if the current box is fuller that the current best fit and if the space that is left is enough to accomadate the current article.
     * if so, he sets the current box as best fit
     * this repeats until we have run out of boxes
     *
     * @param article is to be checked in which box it fits the best
     * @return the box which is the best fit for the given article
     */
    public Box bestFit(Article article) {
        Box best_fit = null;
        if (boxes[0].empty() && boxes[1].empty() && boxes[2].empty()) {
            System.out.println(article.getName() + " set to one because everything is empty");
            return boxes[0];
        }
        for (Box box : boxes) {
            if (box.spaceLeft() >= article.getSize()) {

                System.out.println("current box: " + box.name);

                if (best_fit == null || best_fit.spaceLeft() == 0) {
                    System.out.println(article.getName() + " set to " + box.name + " because there was no best fit yet");
                    best_fit = box;
                }

                if (box.spaceLeft() < best_fit.spaceLeft()) {

                    System.out.println(article.getName() + " set to " + box.name + " because this box is a better fit for the article");
                    best_fit = box;
                } else {
                    System.out.println(box.spaceLeft() + " < " + best_fit.spaceLeft() + " && " + box.spaceLeft() + " >= " + article.getSize());
                }
            } else {
                System.out.println(box.name + " is full");
            }
        }

        System.out.println("returned box: " + best_fit);
        System.out.println("");

        return best_fit;

    }

    public Box[] getBoxes() {
        return boxes;
    }



    public boolean isServo1() {
        return servo1;
    }

    public void setServo1(boolean servo1) {
        this.servo1 = servo1;
    }

    public boolean isServo2() {
        return servo2;
    }

    public void setServo2(boolean servo2) {
        this.servo2 = servo2;
    }
}

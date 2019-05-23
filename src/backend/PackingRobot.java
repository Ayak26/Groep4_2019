package backend;


public class PackingRobot extends Robot {
    private int order_id;

    private Box[] boxes;

    public PackingRobot(String port) {
        super(port);
        boxes = new Box[3];
        boxes[0] = new Box(5);
        boxes[1] = new Box(5);
        boxes[2] = new Box(5);
    }

    public void setOrder(int order_id) {
        Order order = new Order(order_id);
//        ArrayList<ArrayList> order_items = order.getOrderInfo();
        packOrder(order);
    }

    public void setBoxes(int size) {
        boxes[0].setSize(size);
        boxes[1].setSize(size);
        boxes[2].setSize(size);

    }

    public void packOrder(Order order) {

        Article[] article_list = order.getArticle_list();

//        System.out.println(one);
        long startTime = System.nanoTime();

        for (Article article : article_list) {
            if (article.getSize() <= boxes[0].spaceLeft()) {
                boxes[0].addContent(article);
                System.out.println(article.getName()+ " put in box one");

                System.out.println(boxes[0].spaceLeft()+ " space left");
            } else if (article.getSize() <= boxes[1].spaceLeft()) {
                boxes[1].addContent(article);
                System.out.println(article.getName()+ " put in box two");
                System.out.println(boxes[1].spaceLeft()+ " space left");
            } else if (article.getSize() <= boxes[2].spaceLeft()) {
                boxes[2].addContent(article);
                System.out.println(article.getName()+ " put in box three");
                System.out.println(boxes[2].spaceLeft()+ " space left");
            } else {
                System.out.println("all boxes are full");
            }
        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration);
        System.out.println(boxes[0].toString());
        System.out.println(boxes[1].toString());
        System.out.println(boxes[2].toString());


    }

    public Box[] getBoxes() {
        return boxes;
    }
}

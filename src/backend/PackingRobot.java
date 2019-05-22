package backend;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class PackingRobot extends Robot {
    private int order_id;
    private Box one;
    private Box two;
    private Box three;
    public ArrayList<String> path;

    public PackingRobot(String port) {
        super(port);
    }

    public void setOrder(int order_id) {
        Order order = new Order(order_id);
//        ArrayList<ArrayList> order_items = order.getOrderInfo();
        packOrder(order);
    }

    public void setBoxes(int size) {
        one = new Box();
        two = new Box();
        three = new Box();
        one.setSize(size);
        two.setSize(size);
        three.setSize(size);

    }

    public void packOrder(Order order) {

        Article[] article_list = order.getArticle_list();

//        System.out.println(one);
        long startTime = System.nanoTime();

        for (Article article : article_list) {
            if (article.getSize() <= one.spaceLeft()) {
                one.addContent(article);
                System.out.println(article.getName()+ " put in box one");
                System.out.println(one.spaceLeft()+ " space left");
                path = new ArrayList<>();
                path.add("ON");
                path.add("S1");
                path.add("sleep(");
                path.add("S1");

            } else if (article.getSize() <= two.spaceLeft()) {
                two.addContent(article);
                System.out.println(article.getName()+ " put in box two");
                System.out.println(two.spaceLeft()+ " space left");
                path = new ArrayList<>();
                path.add("ON");
                path.add("S1");
                path.add("sleep(");
                path.add("S2");
                path.add("S1");
                path.add("S2");
            } else if (article.getSize() <= three.spaceLeft()) {
                three.addContent(article);
                System.out.println(article.getName()+ " put in box three");
                System.out.println(three.spaceLeft()+ " space left");
            } else {
                System.out.println("all boxes are full");
            }
        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration);
        System.out.println(one.toString());
        System.out.println(two.toString());
        System.out.println(three.toString());


    }

}

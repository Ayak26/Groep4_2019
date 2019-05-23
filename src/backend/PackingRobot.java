package backend;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class PackingRobot extends Robot {
    private int order_id;
    private Box one = new Box();
    private Box two = new Box();
    private Box three = new Box();
    private Box[] BoxArray = new Box[]{one, two, three};

    public PackingRobot(String port) {
        super(port);
    }

    public void setOrder(int order_id) {
        Order order = new Order(order_id);
//        ArrayList<ArrayList> order_items = order.getOrderInfo();
        packOrder(order);
    }

    public void setBoxes(int size) {
        one.setSize(size);
        one.setName("one");
        two.setSize(size);
        two.setName("two");
        three.setSize(size);
        three.setName("three");

    }

    public void packOrder(Order order) {

        Article[] article_list = order.getArticle_listDecr();

//        System.out.println(one);
        long startTime = System.nanoTime();

        for (Article article : article_list) {
            Box best_fit = bestFit(article);
            best_fit.addContent(article);
        }


        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

//        System.out.println(duration);
        System.out.println("one = " + one.toString());
        System.out.println("two = " + two.toString());
        System.out.println("three = " + three.toString());


    }

    public Box bestFit(Article article) {
        Box best_fit = null;
        if (one.empty() && two.empty() && three.empty()) {
            System.out.println(article.getName() + " set to one because everything is empty");
            return one;
        }
        for (Box box : BoxArray) {
            if (box.spaceLeft() != 0) {

                System.out.println("current box: " + box.name);

                if (best_fit == null || best_fit.spaceLeft() == 0) {
                    System.out.println(article.getName() + " set to " + box.name + " because there was no best fit yet");
                    best_fit = box;
                }

                if (box.spaceLeft() < best_fit.spaceLeft() && box.spaceLeft() >= article.getSize()) {

                    System.out.println(article.getName() + " set to " + box.name + " because this box is a better fit for the article");
                    best_fit = box;
                } else {
                    System.out.println(box.spaceLeft() + " < " + best_fit.spaceLeft() + " && " + box.spaceLeft() + " >= " + article.getSize());
                }

//            if (box.spaceLeft() == article.getSize()) {
//                System.out.println("mf returned box: " +box.name);
//                return box;
//            }
            }
        }

        System.out.println("returned box: " + best_fit);
        System.out.println("");

        return best_fit;
    }
}

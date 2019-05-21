package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PackingRobot extends Robot {
    private int order_id;
    private Box one;
    private Box two;
    private Box three;

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
        System.out.println(order);

//        for (ArrayList article : articles) {
//            System.out.println(item.get(1));

//            System.out.println(item.size() < two.spaceLeft());
//            if (Integer.parseInt((String) item.get(0)) < one.spaceLeft()){
//                one.addContent(item);
//            } else if (item.size() < two.spaceLeft()){
//                two.addContent(item);
//            } else if (item.size() < three.spaceLeft()){
//                three.addContent(item);
//            } else {
//                //errything is full
//            }
//        }
    }

}

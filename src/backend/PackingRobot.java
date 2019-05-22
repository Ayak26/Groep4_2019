package backend;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

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

        Article[] article_list = order.getArticle_list();

//        System.out.println(one);
        long startTime = System.nanoTime();

        for (Article article : article_list) {
            if (article.getSize() <= one.spaceLeft()) {
                one.addContent(article);
                System.out.println(article.getName() + " put in box one");
                System.out.println(one.spaceLeft() + " space left");
            } else if (article.getSize() <= two.spaceLeft()) {
                two.addContent(article);
                System.out.println(article.getName() + " put in box two");
                System.out.println(two.spaceLeft() + " space left");
            } else if (article.getSize() <= three.spaceLeft()) {
                three.addContent(article);
                System.out.println(article.getName() + " put in box three");
                System.out.println(three.spaceLeft() + " space left");
            } else {
                System.out.println("all boxes are full");
            }
        }


        for (Article article : article_list) {
            if (one.empty()){
                one.addContent(article);
            }

            

        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration);
        System.out.println(one.toString());
        System.out.println(two.toString());
        System.out.println(three.toString());


    }

    public static int findClosest(int arr[], int target)
    {
        int n = arr.length;

        // Corner cases
        if (target <= arr[0])
            return arr[0];
        if (target >= arr[n - 1])
            return arr[n - 1];

        // Doing binary search
        int i = 0, j = n, mid = 0;
        while (i < j) {
            mid = (i + j) / 2;

            if (arr[mid] == target)
                return arr[mid];

            /* If target is less than array element,
               then search in left */
            if (target < arr[mid]) {

                // If target is greater than previous
                // to mid, return closest of two
                if (mid > 0 && target > arr[mid - 1])
                    return getClosest(arr[mid - 1],
                            arr[mid], target);

                /* Repeat for left half */
                j = mid;
            }

            // If target is greater than mid
            else {
                if (mid < n-1 && target < arr[mid + 1])
                    return getClosest(arr[mid],
                            arr[mid + 1], target);
                i = mid + 1; // update i
            }
        }

        // Only single element left after search
        return arr[mid];
    }

    public static int getClosest(int val1, int val2,
                                 int target)
    {
        if (target - val1 >= val2 - target)
            return val2;
        else
            return val1;
    }

}

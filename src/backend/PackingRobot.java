package backend;


public class PackingRobot extends Robot {
    private int order_id;

    private Box[] boxes;

    public PackingRobot(String port) {
        super(port);
        boxes = new Box[3];
        boxes[0] = new Box(5, "one");
        boxes[1] = new Box(5, "two");
        boxes[2] = new Box(5, "three");
    }

    public void setOrder(int order_id) {
        Order order = new Order(order_id);
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
            System.out.println(article.getName() + " put in box one");
            System.out.println(boxes[0].spaceLeft() + " space left");
        } else if (article.getSize() <= boxes[1].spaceLeft()) {
                boxes[1].addContent(article);
            System.out.println(article.getName() + " put in box two");
            System.out.println(boxes[1].spaceLeft() + " space left");
        } else if (article.getSize() <= boxes[2].spaceLeft()) {
                boxes[2].addContent(article);
            System.out.println(article.getName() + " put in box three");
            System.out.println(boxes[2].spaceLeft() + " space left");
        } else {
            System.out.println("all boxes are full");
        }
            //Box best_fit = bestFit(article);
            //best_fit.addContent(article);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration);
        System.out.println(boxes[0].toString());
        System.out.println(boxes[1].toString());
        System.out.println(boxes[2].toString());

    }


    /**
     * First check of all boxes are empty, next loop through the boxes and check if the current box is full.
     *
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
            }
        }

        System.out.println("returned box: " + best_fit);
        System.out.println("");

        return best_fit;

    }

    public Box[] getBoxes() {
        return boxes;
    }
}

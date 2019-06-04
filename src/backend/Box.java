package backend;

import java.util.ArrayList;

public class Box {
    public ArrayList<Article> content = new ArrayList<>();
    public int size = 0;
    public String name;

    public Box(int size, String name) {
        this.size = size;
        this.name = name;
    }

    /**
     * Set the size of this box
     *
     * @param size the size you want this box to be
     */
    public void setSize(int size){
        this.size = size;
    }

    /**
     * set the name of this box
     *
     * @param name the name you want this box to have
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * add an article to the contents of this box
     *
     * @param article the article which you want to be in the box
     */
    public void addContent(Article article){
        content.add(article);
    }

    /**
     * calculate the amount of space is left in this box
     *
     * @return an integer with the amount of space that the box has left
     */
    public Integer spaceLeft() {
        int space = size;
        for (Article article: content) {
            space = space - article.getSize();
        }
        return space;
    }



    /**
     * @return a string composed of the name, content and size of the box
     */
    @Override
    public String toString() {
        return "Box "+ name +"{" +
                "content=" + content +
                ", size=" + size +
                '}';
    }

    /**
     * check if the space that is left is equal to the max size of the box
     *
     * @return a boolean containing whether the box is empty or not
     */
    boolean empty() {
        return size == this.spaceLeft();
    }
}

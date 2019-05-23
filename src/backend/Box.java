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

    public void setSize(int size){
        this.size = size;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addContent(Article article){
        content.add(article);
    }

    public Integer spaceLeft() {
        int space = size;
        for (Article article: content) {
            space = space - article.getSize();
        }
        return space;
    }


    @Override
    public String toString() {
        return "Box "+ name +"{" +
                "content=" + content +
                ", size=" + size +
                '}';
    }

    boolean empty() {
        return size == this.spaceLeft();
    }
}

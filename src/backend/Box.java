package backend;

import java.util.ArrayList;

public class Box {
    public ArrayList<Article> content = new ArrayList<>();
    public int size = 0;

    public Box(int size) {
        this.size = size;
    }

    public void setSize(int size){
        this.size = size;
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
        return "Box{" +
                "content=" + content +
                ", size=" + size +
                '}';
    }
}

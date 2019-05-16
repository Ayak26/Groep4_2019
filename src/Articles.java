import java.util.ArrayList;

public class Articles {
    ArrayList<String> articles;

    public Articles() {
        articles = new ArrayList<>();
        articles.add("2");
        articles.add("3");
    }

    public ArrayList<String> getArticles() {
        return articles;
    }
}

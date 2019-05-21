package backend;

import java.util.ArrayList;

public class Box {
    public ArrayList<ArrayList> content = new ArrayList<>();
    public int size;

    public void setSize(int size){
        this.size = size;
    }

    public void addContent(ArrayList item){
        content.add(item);
    }

    public Integer spaceLeft() {
        return 4;
    }
}

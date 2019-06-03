package backend;

import javafx.beans.property.SimpleStringProperty;

public class OrderInfo {
    public SimpleStringProperty id;

    public OrderInfo(String id){
        this.id = new SimpleStringProperty(id);

    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}

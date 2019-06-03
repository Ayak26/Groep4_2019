package gui.home;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ModelTable implements Initializable {
    String id;

    public ModelTable(){
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package gui.home;

import backend.DataModel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StandardGuiMethods {

    @FXML
    private ImageView start_stop, detectimage;

    @FXML
    static void start_stop(ImageView start_stop) {
        Image image;
        if(Main.on) {
            image = new Image("file:assets/start.png");
            start_stop.setImage(image);
            DataModel.getSorteerrobot1().sendCommand("OFF");
            DataModel.getInpakrobot1().sendCommand("OFF");
            Main.on =false;
        } else {
            image = new Image("file:assets/stop.png");
            start_stop.setImage(image);
            DataModel.getSorteerrobot1().sendCommand("ON");
            DataModel.getInpakrobot1().sendCommand("ON");
            Main.on = true;
        }
    }

}

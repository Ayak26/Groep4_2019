module KBS {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.fazecast.jSerialComm;
    requires mysql.connector.java;
requires java.sql;

    opens gui.home;
}
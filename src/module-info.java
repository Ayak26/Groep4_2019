module Kbsjavarob {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    requires com.fazecast.jSerialComm;
    requires mysql.connector.java;
requires java.sql;
requires opencv;
requires javafx.swing;
requires core;
    opens gui.home;
    opens backend;

}
module Unfuckery {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    requires opencv;
    requires core;
    requires com.fazecast.jSerialComm;

    opens gui;
    opens backend;
}
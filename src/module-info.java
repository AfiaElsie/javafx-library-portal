module library.management.system {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    opens application;
    opens application.view;
    opens application.model;
    opens application.controller;

}
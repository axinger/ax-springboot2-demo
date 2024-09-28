module com.github.axinger.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.beans;


    opens com.github.axinger.javafxdemo to javafx.fxml;
    exports com.github.axinger.javafxdemo;
    exports com.github.axinger.javafxdemo.server;
    opens com.github.axinger.javafxdemo.server to javafx.fxml;
}

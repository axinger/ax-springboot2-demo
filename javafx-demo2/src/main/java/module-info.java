module com.github.axinger.javafxdemo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.axinger.javafxdemo2 to javafx.fxml;
    exports com.github.axinger.javafxdemo2;
}
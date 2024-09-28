package com.github.axinger.javafxdemo;

import com.github.axinger.javafxdemo.server.HelloServer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    @FXML
    private Label welcomeText;

    @Autowired
    private HelloServer helloServer;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(helloServer.hello());
    }
}

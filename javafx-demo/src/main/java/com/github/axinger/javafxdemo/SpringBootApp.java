package com.github.axinger.javafxdemo;// SpringBootApp.java
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        SpringApplication.run(SpringBootApp.class);
        // 在JavaFX应用程序启动之后，显示JavaFX场景。
        Platform.runLater(() -> {
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override
    public void init() {
        // 初始化JavaFX应用程序。
        Main.launch();
    }

    @Override
    public void stop() throws Exception {
        // 在应用程序停止时执行清理工作。
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    TabPane root;
    Tab tabCore, tabConsole;
    GridPane paneCore, paneConsole;
    Label lblTags;
    TextField txtTags;
    Button btnConnect;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new TabPane();
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabCore = new Tab("Core");
        tabConsole = new Tab("Console");

        paneCore = new GridPane();
        paneCore.setVgap(5);
        paneCore.setHgap(5);
        paneCore.setPadding(new Insets(5));
        lblTags = new Label("Tags: ");
        txtTags = new TextField();
        btnConnect = new Button("Connect");
        paneCore.add(lblTags, 0, 0);
        paneCore.add(txtTags, 1, 0);
        paneCore.add(btnConnect, 1, 1);

        paneConsole = new GridPane();
        paneConsole.setVgap(5);
        paneConsole.setHgap(5);
        paneConsole .setPadding(new Insets(5));

        tabCore.setContent(paneCore);
        tabConsole.setContent(paneConsole);

        root.getTabs().addAll(tabCore, tabConsole);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    TabPane root;
    Tab tabCore, tabConsole;
    Label lblTags;
    GridPane paneCore, paneConsole;
    TextField txtTags;
    Button btnConnect;
    Client clientA, clientB;
    Console console;
    Timer timer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        clientA = new Client();
        clientB = new Client();
        console = new Console();
        timer = new Timer();

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

        btnConnect.setOnAction(
            e -> {
                clientA.connect("");
                clientB.connect("");

                timer.schedule(new CheckClients(), 0, 1000);
            }
        );


        root.getTabs().addAll(tabCore, tabConsole);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    class CheckClients extends TimerTask{
        private int clientACount = 0, clientBCount = 0;
        @Override
        public void run() {
            if(clientA.getMessages().size() > clientACount){
                for(int i = clientACount; i < clientA.getMessages().size(); i++){
                    console.getMessages().add(new Message(clientA.getMessages().get(i).getMessage(), clientA.getMessages().get(i).getType()));
                    System.out.println(clientA.getMessages().get(i).getType() + ": " + clientA.getMessages().get(i).getMessage());
                    clientACount++;
                }
            }

            if(clientB.getMessages().size() > clientBCount){
                for(int i = clientBCount; i < clientB.getMessages().size(); i++){
                    console.getMessages().add(new Message(clientB.getMessages().get(i).getMessage(), clientB.getMessages().get(i).getType()));
                    //System.out.println(clientB.getMessages().get(i).getType() + ": " + clientB.getMessages().get(i).getMessage());
                    clientBCount++;
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package staff;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import staff.controllers.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main extends Application {

    private Controller controller;
    private VBox vb;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initAPIEndpoint();
        initController();
        // VBox
        vb = new VBox();
        vb.setPadding(new Insets(10, 100, 50, 100));
        vb.setMinWidth(800);
        vb.setMinHeight(500);
        vb.setSpacing(20);

        Label lbl = new Label("Received orders\n\n\n");
        lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 32));
        lbl.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().add(lbl);

        // Adding VBox to the scene
        scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.setTitle("POMS Staff");
        primaryStage.setFullScreen(true);
        primaryStage.show();
        stage = primaryStage;
    }

    /**
     * This method is called when the application should stop,
     * and provides a convenient place to prepare for application exit and destroy resources.
     */
    @Override
    public void stop() throws Exception
    {
        super.stop();

        if(controller != null)
            controller.terminate();

        Platform.exit();
        System.exit(0);
    }

    private void appendOrder(String summary) {
        Label text = new Label(summary);
        text.setStyle("-fx-background-color: coral; -fx-padding: 30px;");
        text.setFont(Font.font("Amble CN", FontWeight.NORMAL, 28));
        text.setTextFill(Color.BLACK);
        vb.getChildren().add(text);
    }

    public int newOrder(String summary) {

        int waitingTime = 0;
        List<String> choices = new ArrayList<>();
        choices.add("30");
        choices.add("45");
        choices.add("60");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("45", choices);
        dialog.setTitle("New order");
        dialog.setHeaderText("Confirm new order:\n\"" + summary + "\" ?");
        dialog.setContentText("Set waiting time: ");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            waitingTime = Integer.parseInt(result.get());
            appendOrder(summary);
        }

        return waitingTime;
    }

    private void initAPIEndpoint() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set endpoint IP");
        dialog.setHeaderText("Please enter the API's IP address");
        dialog.setContentText("IP: ");

        Optional<String> ip = dialog.showAndWait();

        if (!ip.isPresent())
            return;

        try {
            Files.write(Paths.get("endpoint"), ip.get().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initController() {
        controller = new Controller(this);
        controller.startHeartBeat();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

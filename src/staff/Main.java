package staff;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import staff.controllers.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main extends Application {

    private Controller controller;
    private VBox vb;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        initController();
        // VBox
        vb = new VBox();
        vb.setPadding(new Insets(10, 100, 50, 100));
        vb.setSpacing(20);

        Label lbl = new Label("Received orders\n\n\n");
        lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 32));
        lbl.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().add(lbl);

        Rectangle r = new Rectangle();
        r.setFill(Color.WHEAT);
        r.setWidth(600);
        r.setHeight(60);

        Label text = new Label("Pizza Royale +cheese,oregano");
        text.setTextFill(Color.BLACK);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(r, text);
        vb.getChildren().add(stack);

        // Adding VBox to the scene
        scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.setTitle("POMS Staff");
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

    //fix that
    private void appendOrder(String order) {
        Rectangle r = new Rectangle();
        r.setFill(Color.WHEAT);
        r.setWidth(300);
        r.setHeight(30);

        Label text = new Label("Pizza Royale +cheese,oregano");
        text.setFont(Font.font("Amble CN", FontWeight.NORMAL, 18));
        text.setTextFill(Color.BLACK);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(r, text);
        vb.getChildren().add(stack);
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
            //appendOrder(order);
        }

        return waitingTime;
    }

    private void initController() {
        controller = new Controller(this);
        controller.startHeartBeat();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

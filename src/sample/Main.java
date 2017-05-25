package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Main extends Application {

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
        primaryStage.setTitle("Hello World");
        primaryStage.show();
        stage = primaryStage;
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

    public void newOrder(String order) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm new order: \"" + order + "\" ?", ButtonType.YES, ButtonType.NO);
        alert.initModality(Modality.APPLICATION_MODAL); /* *** */
        alert.initOwner(stage);                         /* *** */
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //appendOrder(order);
        }
    }

    private void initController() {
        Controller controller = new Controller(this);
        controller.startHeartBeat();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

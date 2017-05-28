package staff.controllers;

import javafx.application.Platform;
import staff.Main;
import staff.utils.JSONService;
import javafx.concurrent.Task;

import static java.lang.Thread.sleep;

public class Controller {

    private Main view;

    public Controller(Main app) {
        this.view = app;
    }

    public void startHeartBeat() {
        Task<Void> heartBeat = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(true) {
                    try {
                        sleep(1000);
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                poll();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(heartBeat).start();
    }

    public void poll() {
        JSONService.getNewOrders();
        view.newOrder("Pizza Margarita XL");
    }
}

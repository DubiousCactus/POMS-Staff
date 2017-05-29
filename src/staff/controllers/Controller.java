package staff.controllers;

import javafx.application.Platform;
import staff.Main;
import staff.models.Order;
import staff.utils.JSONService;
import javafx.concurrent.Task;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Controller {

    private Main view;
    private ArrayList<Order> orders;
    private Thread heartBeatThread;
    private boolean keepBeating;

    public Controller(Main app) {
        this.view = app;
        orders = new ArrayList<>();
    }

    public void startHeartBeat() {
        keepBeating = true;

        Task<Void> heartBeat = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(keepBeating) {
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

                return null;
            }
        };

        heartBeatThread = new Thread(heartBeat);
        heartBeatThread.start();
    }

    public void poll() {
        ArrayList<Order> newOrders = JSONService.getNewOrders();

        for (Order order : newOrders) {
            if (!orders.contains(order)) {
                orders.add(order);
                int waitingTime = view.newOrder(order.getSummary());

                if (waitingTime != 0) {
                    System.out.println("Waiting time: " + waitingTime);
                    order.setWaitingTime(waitingTime);
                    JSONService.confirmOrder(order);
                }
            }
        }
    }

    public void terminate() {
        keepBeating = false;
    }
}

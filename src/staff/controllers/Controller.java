package staff.controllers;

import staff.Main;
import staff.utils.JSONService;

import static java.lang.Thread.sleep;

public class Controller {

    private Main view;

    public Controller(Main app) {
        this.view = app;
    }

    public void startHeartBeat() {
        /*Task heartBeat = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                return null;
            }

            @Override
            public void run() {
                while(true) {
                    try {
                        sleep(3000);
                        poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(heartBeat).start();*/
        JSONService.getNewOrders();
    }

    public void poll() {
        //fetch from api

        view.newOrder("Pizza Margarita XL");
    }
}

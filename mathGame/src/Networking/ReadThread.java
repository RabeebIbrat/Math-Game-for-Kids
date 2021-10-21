package Networking;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import windows.*;

public class ReadThread implements Runnable {
    private Thread thr;
    private NetworkUtil nc;

    public ReadThread(NetworkUtil nc) {
        this.nc = nc;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {


        try {
            while (true) {
                Object o = nc.read();
                if (o != null) {
                    System.out.println((String)o);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }
    }
}




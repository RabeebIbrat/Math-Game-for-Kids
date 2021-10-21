package Networking;

import javafx.application.Platform;
import javafx.scene.control.Label;
import windows.hostWindowController;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server implements Runnable{

    private ServerSocket serverSocket;

    public HashMap<String, NetworkUtil>connections = new HashMap<>();

    public Server() {
        new Thread(this).start();
    }

    @Override
    public void run() {

        try {
            System.out.println("Started");
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) {
        NetworkUtil nc = new NetworkUtil(clientSocket);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                NetworkUtil netUt = nc;

                try {

                    Object o = netUt.read();
                    System.out.println((String) o);
                    if (o != null) {
                        Label lbl1 = new Label((String) o);
                        lbl1.setLayoutX(201);
                        lbl1.setLayoutY(100 + connections.size() * 40);
                        connections.put((String) o, netUt);
                        hostWindowController.paneDemo.getChildren().add(lbl1);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    //nc.closeConnection();
                }

            }
        });

    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}

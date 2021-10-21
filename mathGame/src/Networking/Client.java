package Networking;

import javafx.application.Platform;
import windows.Main;
import windows.QuestionsData;
import windows.questionPanelController;

public class Client implements Runnable{

    String serverAddress;
    int serverPort;
    String name;

    public Client(String serverAddress, int serverPort, String name) {
        new Thread(this).start();
        this.serverAddress = serverAddress;
        this.serverPort =serverPort;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            NetworkUtil nc = new NetworkUtil(serverAddress, serverPort);

            nc.write(name);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Object o = nc.read();
                    QuestionsData questionsData = (QuestionsData)o;

                    System.out.println((o instanceof QuestionsData));
                    if(questionsData != null) {
                        Main.setQuestionTypes(questionsData.getQuestionType());
                        Main.setQuestionDifficulty(questionsData.getQuestionDifficulty());
                        Main.setTotalQuestion(questionsData.getTotalQuestions());
                        Main.setQuestions(questionsData.getQuestion());

                        questionPanelController.setQuestionPanel();
                        Main.setScene(Main.squestionPanel);
                    }
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort, "Demo");
    }
}


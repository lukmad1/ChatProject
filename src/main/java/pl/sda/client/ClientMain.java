package pl.sda.client;

import pl.sda.client.gui.ClientWindow;
import pl.sda.client.gui.ConnectToServerWindow;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    private static String serverIP = "localhost";

    public static void main(String[] args) throws IOException {

        ConnectToServerWindow connectToServerWindow = new ConnectToServerWindow();
        connectToServerWindow.setVisible(true);

        ClientWindow clientWindow = new ClientWindow();

        connectToServerWindow.addConnectButtonListener(l -> {
            serverIP = connectToServerWindow.getIpBox().getText();
            connectToServerWindow.setVisible(false);
            clientWindow.setVisible(true);
        });

        Socket server = new Socket(Inet4Address.getByName(serverIP), 8888);

        clientWindow.addSendButtonActionListener(l -> {
            String userInput = clientWindow.getMessage();
            try {
                server.getOutputStream().write((userInput + "\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Scanner sc = new Scanner(server.getInputStream());
        while (sc.hasNextLine()) {
            String msg = sc.nextLine();
            clientWindow.writeToChat(msg);
        }
    }
}

package pl.sda.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        List<Socket> users = new ArrayList<>();

        while (true) {
            Socket accept = serverSocket.accept();
            users.add(accept);
            new Thread(new ChatObject(accept, users)).start();
        }
    }

}

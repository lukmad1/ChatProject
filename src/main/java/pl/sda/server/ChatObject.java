package pl.sda.server;

import pl.sda.server.messagetypes.GroupMessage;
import pl.sda.server.messagetypes.IMessage;
import pl.sda.server.messagetypes.PrivateMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChatObject implements Runnable {
    private List<Socket> userList;
    private Socket currentUser;
    private List<IMessage> messages;

    public ChatObject(Socket socket, List<Socket> users) {
        this.currentUser = socket;
        this.userList = users;
        messages = Arrays.asList(new GroupMessage(userList, currentUser), new PrivateMessage(userList, currentUser));
    }

    @Override
    public void run() {
        Scanner scanner;

        try {
            scanner = establishInputStream();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                for (IMessage message : messages) {
                    if (message.isApplicable(s)) {
                        message.sendMessage(s);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Scanner establishInputStream() throws IOException {
        return new Scanner(currentUser.getInputStream());
    }

}

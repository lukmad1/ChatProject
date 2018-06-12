package pl.sda.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class GroupMessage implements IMessage {
    private List<Socket> userList;
    private Socket currentUser;

    public GroupMessage(List<Socket> userList, Socket currentUser) {
        this.userList = userList;
        this.currentUser = currentUser;
    }

    @Override
    public boolean isApplicable(String msg) {
        return !msg.contains("pw");
    }

    @Override
    public void sendMessage(String msg) throws IOException {
        String message = (currentUser.getInetAddress().toString() + ": " + msg + "\n");
        for (Socket user : userList) {
            user.getOutputStream().write(message.getBytes());
        }
    }


}

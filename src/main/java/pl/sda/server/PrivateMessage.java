package pl.sda.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivateMessage implements IMessage {
    private final String IPREGEXP = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
    private List<Socket> userList;
    private Socket currentUser;

    public PrivateMessage(List<Socket> userList, Socket currentUser) {
        this.currentUser = currentUser;
        this.userList = userList;
    }


    @Override
    public boolean isApplicable(String msg) {
        return msg.contains("pw");
    }


    @Override
    public void sendMessage(String msg) throws IOException {
        String messageTo = ("PW to " + currentUser.getInetAddress().getHostAddress() + ": " + msg.replaceFirst(IPREGEXP, "").replaceFirst("pw", "") + "\n");
        String messageFrom = ("PW from " + currentUser.getInetAddress().getHostAddress() + ": " + msg.replaceFirst(IPREGEXP, "").replaceFirst("pw", "") + "\n");

        for (Socket user : userList) {
            if (user.getInetAddress().getHostAddress().equals(getIpAdress(msg))) {
                currentUser.getOutputStream().write(messageTo.getBytes());
                user.getOutputStream().write(messageFrom.getBytes());
            }
        }
    }


    private String getIpAdress(String msg) {
        String targetIpAdress = "";
        Pattern pattern = Pattern.compile(IPREGEXP);
        Matcher matcher = pattern.matcher(msg);

        if (matcher.find()) {
            targetIpAdress = matcher.group();
        }

        return targetIpAdress;
    }

}

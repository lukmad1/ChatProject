package pl.sda.server;

import java.io.IOException;

public interface IMessage {

    boolean isApplicable(String msg);

    void sendMessage(String msg) throws IOException;
}

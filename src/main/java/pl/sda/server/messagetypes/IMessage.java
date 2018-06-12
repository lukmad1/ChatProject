package pl.sda.server.messagetypes;

import java.io.IOException;

public interface IMessage {

    boolean isApplicable(String msg);

    void sendMessage(String msg) throws IOException;
}

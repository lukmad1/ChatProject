package pl.sda.client.gui;

import pl.sda.client.gui.renderers.MessageRenderer;
import sun.plugin.javascript.navig.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame {

    private JButton sendButton = new JButton("Send");
    private JTextField textBox = new JTextField();
    private DefaultListModel<String> messageDefaultListModel = new DefaultListModel<>();
    private JList<String> messageJList = new JList<>(messageDefaultListModel);


    public ClientWindow() throws HeadlessException {
        setLayout(new GridBagLayout());
        setSize(new Dimension(300, 500));
        setTitle("Message window");

        messageJList.setPreferredSize(new Dimension(300, 400));
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        sendButton.setPreferredSize(new Dimension(100, 100));
        add(sendButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.1;
        c.ipadx = 200;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textBox, c);

        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 400;
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_START;
        messageJList.setCellRenderer(new MessageRenderer(200));
        messageJList.setPreferredSize(new Dimension(200,400));
        messageJList.setAutoscrolls(true);
        add(new JScrollPane(messageJList), c);

        ImageIcon icon = new ImageIcon("D:\\Projekty_Java\\WzorceProjektoweChatProject\\src\\main\\resources\\dominik_chcial_logo.png");
        setIconImage(icon.getImage());
    }

    public void addSendButtonActionListener(ActionListener actionListener) {
        this.sendButton.addActionListener(actionListener);
    }

    public String getMessage() {
        String msg = textBox.getText();
        textBox.setText("");
        return msg;
    }

    public void writeToChat(String message) {
        messageDefaultListModel.addElement(message);
    }
}

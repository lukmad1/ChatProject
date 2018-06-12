package pl.sda.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConnectToServerWindow extends JFrame {
    private JTextField ipBox = new JTextField();
    private JButton connectButton = new JButton("Connect!");


    public ConnectToServerWindow() throws HeadlessException {
        setTitle("Connect");
        setLayout(new FlowLayout());
        setSize(new Dimension(350, 80));

        ipBox.setPreferredSize(new Dimension(150, 30));
        add(ipBox, FlowLayout.LEFT);
        connectButton.setPreferredSize(new Dimension(100, 30));
        add(connectButton, FlowLayout.CENTER);
    }

    public JTextField getIpBox() {
        return ipBox;
    }

    public void addConnectButtonListener(ActionListener actionListener) {
        connectButton.addActionListener(actionListener);
    }

}

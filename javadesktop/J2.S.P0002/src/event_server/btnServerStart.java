/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_server;

import controller.ServerAppController;
import entity.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class btnServerStart implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // connect socket to server
                    ServerAppController.serverSocket = new ServerSocket(9999);
                    ServerAppController.serverApp.getTxtServerMessage().setText("Server starting....");
                    // disable button start 
                    ServerAppController.serverApp.getBtnServerStart().setEnabled(false);
                    while (true) {
                        try {
                            // Accept request connection from user
                            Socket s = ServerAppController.serverSocket.accept();
                            // create input stream to get data
                            ServerAppController.in = new ObjectInputStream(s.getInputStream());
                            ServerAppController.serverApp.getBtnServerSave().setEnabled(true);
                            // convert receive data to object Data
                            Data data = (Data) ServerAppController.in.readObject();
                            ServerAppController.filename = data.getFilename();
                            ServerAppController.file_recieve = data.getFile();
                            ServerAppController.serverApp.getTxtServerMessage().setText(data.getStatus());
                        } catch (ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Something wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Something wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }).start();
    }
}

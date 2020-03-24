/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_client;

import controller.ClientAppController;
import entity.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class btnClientSend implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        // check ip address empty
        if (ClientAppController.clientApp.getTxtIPAddressOfServer().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please input text for ip address !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // check client path empty
        if (ClientAppController.clientApp.getTxtClientFilePath().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please choose file path !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        File file = ClientAppController.j.getSelectedFile();
        try {
            // open socket to server
            ClientAppController.serverSocket = new Socket(ClientAppController.clientApp.getTxtIPAddressOfServer().getText().trim(), 9999);
            // create output stream to send data
            ClientAppController.outputStream = new ObjectOutputStream(ClientAppController.serverSocket.getOutputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot connect to server", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // read byte from input file
            FileInputStream in = new FileInputStream(file);
            byte fileByte[] = new byte[in.available()];
            in.read(fileByte);
            // pass to data object
            Data data = new Data();
            data.setFilename(file.getName());
            data.setFile(fileByte);
            data.setStatus(file.getName() + " file is sended");
            // pass object data to ObjectOutputStream
            ClientAppController.outputStream.writeObject(data);
            // push data file to server
            ClientAppController.outputStream.flush();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error to send file", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error to send file", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

}

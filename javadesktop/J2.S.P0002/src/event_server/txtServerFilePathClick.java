/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_server;

import controller.ServerAppController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author ANH DUC
 */
public class txtServerFilePathClick implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {
        // Only choose folder file
        ServerAppController.j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ServerAppController.j.setDialogTitle("Choose folder to save file !!!");
        int file = ServerAppController.j.showOpenDialog(null);
        // get absolute path to text file
        if (file == JFileChooser.APPROVE_OPTION) {
            File directory = ServerAppController.j.getSelectedFile();
            ServerAppController.serverApp.getTxtSeverFilePath().setText(directory.getAbsolutePath());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}

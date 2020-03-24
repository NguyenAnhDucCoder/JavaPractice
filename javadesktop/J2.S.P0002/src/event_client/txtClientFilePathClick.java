/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_client;

import controller.ClientAppController;
import java.awt.event.MouseListener;

/**
 *
 * @author ANH DUC
 */
public class txtClientFilePathClick implements MouseListener {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {
        // open file dialog
        ClientAppController.j.setDialogTitle("Choose file txt !!!");
        ClientAppController.j.showOpenDialog(null);
        // get full path file
        ClientAppController.path = ClientAppController.j.getSelectedFile().getAbsolutePath();
        ClientAppController.clientApp.getTxtClientFilePath().setText(ClientAppController.path);
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent me) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent me) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent me) {
    }

}

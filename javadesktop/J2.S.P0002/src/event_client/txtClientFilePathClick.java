/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_client;

import controller.ClientAppController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

/**
 *
 * @author ANH DUC
 */
public class txtClientFilePathClick extends MouseAdapter {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {
        // open file dialog
        ClientAppController.j.setDialogTitle("Choose file txt !!!");
        ClientAppController.j.showOpenDialog(null);
        // get full path file
        ClientAppController.path = ClientAppController.j.getSelectedFile().getAbsolutePath();
        ClientAppController.clientApp.getTxtClientFilePath().setText(ClientAppController.path);
    }

}

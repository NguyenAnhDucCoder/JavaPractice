 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_server;

import controller.ServerAppController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class btnServerSave implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        ServerAppController.path = ServerAppController.serverApp.getTxtSeverFilePath().getText().trim();
        // check path empty
        if (ServerAppController.path.equals("")) {
            JOptionPane.showMessageDialog(null, "Should select folder to save", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            try {
                File file_receive = new File(ServerAppController.path + "/" + ServerAppController.filename);
                // check file is exist or not
                if (file_receive.exists()) {
                    // Show dialog if file exist
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to replace it?", "Save As", JOptionPane.YES_NO_OPTION);
                    // if user choose no return method
                    if (option == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                FileOutputStream out = new FileOutputStream(file_receive);
                // write data to file
                out.write(ServerAppController.file_recieve);
                ServerAppController.serverApp.getTxtServerMessage().setText("Save succeed");
                // close FileOutputStream
                out.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Something wrong", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

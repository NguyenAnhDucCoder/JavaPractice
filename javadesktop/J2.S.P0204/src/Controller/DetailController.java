/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Common.CommonSub;
import Entity.Worker;
import Model.WorkerImplement;
import View.DetailScreen;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class DetailController {

    DetailScreen detailScreen;

    public DetailController() {
        detailScreen = new DetailScreen(MainScreenController.mainScreen, true);
        initDataWorker();
        detailScreen.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                detailScreen.dispose();
            }
        });
        // set JDiolog Center
        detailScreen.setLocationRelativeTo(null);
        detailScreen.setVisible(true);
    }

    private void initDataWorker() {
        WorkerImplement workerImplement = new WorkerImplement();
        try {
            Worker worker = workerImplement.getDetailWorker(Integer.parseInt(MainScreenController.getSelectedRow()));
            detailScreen.getTxtID().setText(String.valueOf(worker.getId()));
            detailScreen.getTxtName().setText(worker.getName());
            detailScreen.getTxtRole().setText(worker.getRole().getRoleName());
            detailScreen.getTxtGender().setText(worker.isGender() ? "Male" : "Female");
            Image img = ImageIO.read(new ByteArrayInputStream(worker.getImage()));
            ImageIcon imageIcon = new ImageIcon(img);
            detailScreen.getjLabel_Image().setIcon(new ImageIcon(CommonSub.scaleImageIcon(detailScreen.getjLabel_Image(), imageIcon)));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot get worker detail !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

}

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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class DetailController {

    private DetailScreen detailScreen;
    private final MainScreenController mainScreenController;

    public DetailController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
        detailScreen = new DetailScreen(mainScreenController.getMainScreen(), true);
        initDataWorker();
        detailScreen.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // dispose
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
            // get worker by id
            Worker worker = workerImplement.getDetailWorker(Integer.parseInt(mainScreenController.getSelectedRow()));
            // set text TxtID
            detailScreen.getTxtID().setText(String.valueOf(worker.getId()));
            // set text TxtName
            detailScreen.getTxtName().setText(worker.getName());
            // set text TxtRole
            detailScreen.getTxtRole().setText(worker.getRole().getRoleName());
            // set text TxtGender
            detailScreen.getTxtGender().setText(worker.isGender() ? "Male" : "Female");
            // convert to image from byte array
            Image img = ImageIO.read(new ByteArrayInputStream(worker.getImage()));
            ImageIcon imageIcon = new ImageIcon(img);
            // add image to jLabel_Image
            detailScreen.getjLabel_Image().setIcon(new ImageIcon(CommonSub.scaleImageIcon(detailScreen.getjLabel_Image(), imageIcon)));
        } catch (Exception ex) {
            // show warning message
            JOptionPane.showMessageDialog(null, "Cannot get worker detail !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

}

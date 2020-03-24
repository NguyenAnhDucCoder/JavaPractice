/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Common.CommonSub;
import static Common.CommonSub.ConvertFileToByte;
import static Controller.MainScreenController.mainScreen;
import Entity.Role;
import Entity.Worker;
import Model.WorkerImplement;
import View.UpdateScreen;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ANH DUC
 */
public class UpdateController {

    private DefaultComboBoxModel<Role> modelRole;
    private File file;
    UpdateScreen updateScreen;
    private Worker worker;

    public UpdateController() {
        updateScreen = new UpdateScreen(mainScreen, true);
        updateScreen.getjComboBox_Role().setModel(modelRole = new DefaultComboBoxModel<>());
        listRole();
        getWorkerById();
        updateScreen.getjPanel_Image().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                chooseImage();
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
        });
        updateScreen.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateWorker();
            }
        });
        // set JDiolog Center
        updateScreen.setLocationRelativeTo(null);
        updateScreen.setVisible(true);
    }

    private void listRole() {
        try {
            List<Role> listRoles = new WorkerImplement().getAllRole();
            for (Role role : listRoles) {
                modelRole.addElement(role);
            }
        } catch (Exception ex) {
        }
    }

    private void getWorkerById() {
        int worker_id = Integer.parseInt(MainScreenController.getSelectedRow());
        WorkerImplement workerImplement = new WorkerImplement();
        try {
            worker = workerImplement.getDetailWorker(worker_id);
            updateScreen.getTxtName().setText(worker.getName());
            if (worker.isGender()) {
                updateScreen.getRadMale().setSelected(true);
            } else {
                updateScreen.getRadFemale().setSelected(true);
            }
            modelRole.setSelectedItem(worker.getRole());
            Image img = ImageIO.read(new ByteArrayInputStream(worker.getImage()));
            ImageIcon imageIcon = new ImageIcon(img);
            updateScreen.getjLabel_Image().setIcon(new ImageIcon(CommonSub.scaleImageIcon(updateScreen.getjLabel_Image(), imageIcon)));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot get worker detail !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose image file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            updateScreen.getjLabel_Image().setIcon(new ImageIcon(CommonSub.scaleImageIcon(updateScreen.getjLabel_Image(), new ImageIcon(file.getAbsolutePath()))));
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
    }

    private void updateWorker() {
        if (updateScreen.getTxtName().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(updateScreen, "Worker Name cannot be empty !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            updateScreen.getTxtName().requestFocus();
            return;
        }
        WorkerImplement workerImplement = new WorkerImplement();
        int id = Integer.parseInt(MainScreenController.getSelectedRow());
        String name = updateScreen.getTxtName().getText().trim();
        boolean gender = updateScreen.getRadMale().isSelected();
        byte[] image = null;
        if (file != null) {
            image = ConvertFileToByte(file.getAbsolutePath());
        } else {
            image = worker.getImage();
        }
        Role role = (Role) updateScreen.getjComboBox_Role().getSelectedItem();
        Worker worker = new Worker(id, name, gender, image, role);
        try {
            boolean checkedUpdate = workerImplement.updateWorker(worker);
            if (checkedUpdate == true) {
                JOptionPane.showMessageDialog(updateScreen, "Worker ID: " + id + " is updated");
                updateScreen.dispose();
                MainScreenController.showAllWorker();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(updateScreen, "Worker ID: " + id + " is NOT updated", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
}

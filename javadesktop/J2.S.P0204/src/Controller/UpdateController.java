/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Common.CommonSub;
import static Common.CommonSub.ConvertFileToByte;
import Entity.Role;
import Entity.Worker;
import Model.WorkerImplement;
import View.UpdateScreen;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
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

    private final DefaultComboBoxModel<Role> modelRole;
    private File file;
    private final UpdateScreen updateScreen;
    private Worker worker;
    private final MainScreenController mainController;

    public UpdateController(MainScreenController mainController) {
        this.mainController = mainController;
        updateScreen = new UpdateScreen(mainController.getMainScreen(), true);
        // set model for jComboBox_Role
        updateScreen.getjComboBox_Role().setModel(modelRole = new DefaultComboBoxModel<>());
        listRole();
        getWorkerById();
        // raise click panel
        updateScreen.getjPanel_Image().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                chooseImage();
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
            // get all role from database
            List<Role> listRoles = new WorkerImplement().getAllRole();
            // add list role to jLabel_Image
            for (Role role : listRoles) {
                modelRole.addElement(role);
            }
        } catch (Exception ex) {
        }
    }

    private void getWorkerById() {
        // get worker_id
        int worker_id = Integer.parseInt(mainController.getSelectedRow());
        WorkerImplement workerImplement = new WorkerImplement();
        try {
            // get worker by id
            worker = workerImplement.getDetailWorker(worker_id);
            // set text TxtName
            updateScreen.getTxtName().setText(worker.getName());
            if (worker.isGender()) {
                // selected RadMale if male
                updateScreen.getRadMale().setSelected(true);
            } else {
                // selected getRadFemale if Female
                updateScreen.getRadFemale().setSelected(true);
            }
            // set selected for role combobox
            modelRole.setSelectedItem(worker.getRole());
            // convert byte array to image
            Image img = ImageIO.read(new ByteArrayInputStream(worker.getImage()));
            ImageIcon imageIcon = new ImageIcon(img);
            // add image to jLabel_Image
            updateScreen.getjLabel_Image().setIcon(new ImageIcon(CommonSub.scaleImageIcon(updateScreen.getjLabel_Image(), imageIcon)));
        } catch (Exception ex) {
            // show error message
            JOptionPane.showMessageDialog(null, "Cannot get worker detail !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose image file");
        // Filter image file
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        // show fileChooser
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // get file chooser
            file = fileChooser.getSelectedFile();
            // add image to jLabel_Image
            updateScreen.getjLabel_Image().setIcon(new ImageIcon(CommonSub.scaleImageIcon(updateScreen.getjLabel_Image(), new ImageIcon(file.getAbsolutePath()))));
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
    }

    private void updateWorker() {
        // check empty name
        if (updateScreen.getTxtName().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(updateScreen, "Worker Name cannot be empty !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            updateScreen.getTxtName().requestFocus();
            return;
        }
        WorkerImplement workerImplement = new WorkerImplement();
        // get worker id
        int id = Integer.parseInt(mainController.getSelectedRow());
        // get name
        String name = updateScreen.getTxtName().getText().trim();
        // get gender
        boolean gender = updateScreen.getRadMale().isSelected();
        byte[] image = null;
        if (file != null) {
            // if user choose image convert image to byte array
            image = ConvertFileToByte(file.getAbsolutePath());
        } else {
            // if user does not choose image get old byte array image
            image = worker.getImage();
        }
        // get role from combobox
        Role role = (Role) updateScreen.getjComboBox_Role().getSelectedItem();
        Worker worker = new Worker(id, name, gender, image, role);
        try {
            // update worker
            boolean checkedUpdate = workerImplement.updateWorker(worker);
            if (checkedUpdate == true) {
                // show message success
                JOptionPane.showMessageDialog(updateScreen, "Worker ID: " + id + " is updated");
                // dispose
                updateScreen.dispose();
                // show data to jtable again
                mainController.showAllWorker();
            }
        } catch (Exception e) {
            // show error message
            JOptionPane.showMessageDialog(updateScreen, "Worker ID: " + id + " is NOT updated", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
}

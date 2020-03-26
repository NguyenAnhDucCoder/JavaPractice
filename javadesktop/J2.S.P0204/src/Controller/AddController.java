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
import View.AddScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class AddController {

    private File file;
    private final DefaultComboBoxModel<Role> modelRole;
    private final AddScreen addScreen;
    private final MainScreenController mainScreenController;

    public AddController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
        addScreen = new AddScreen(mainScreenController.getMainScreen(), true);
        modelRole = new DefaultComboBoxModel<>();
        // set model for jComboBox_Role
        addScreen.getjComboBox_Role().setModel(modelRole);
        listRole();
        addScreen.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addWorker();
            }
        });
        // raise click panel
        addScreen.getjPanel_Image().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                chooseImage();
            }

        });
        // set JDiolog Center
        addScreen.setLocationRelativeTo(null);
        addScreen.setVisible(true);
    }

    private void listRole() {

        try {
            // get all role
            List<Role> listRoles = new WorkerImplement().getAllRole();
            // add listRoles to modelRole
            for (Role role : listRoles) {
                modelRole.addElement(role);
            }
        } catch (Exception ex) {
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose image file");
        // setting file Filter is Image files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        // show dialog fileChooser
        int result = fileChooser.showSaveDialog(null);
        // save file
        if (result == JFileChooser.APPROVE_OPTION) {
            // get file chooser
            file = fileChooser.getSelectedFile();
            // get image into jLabel
            addScreen.getjLabel4().setIcon(new ImageIcon(CommonSub.scaleImageIcon(addScreen.getjLabel4(), new ImageIcon(file.getAbsolutePath()))));
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
    }

    private void addWorker() {
        // check input txtName
        if (addScreen.getTxtName().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(addScreen, "Worker Name cannot be empty !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            addScreen.getTxtName().requestFocus();
            return;
        }
        // check choose gender
        if (addScreen.getRadMale().isSelected() == false && addScreen.getRadFemale().isSelected() == false) {
            JOptionPane.showMessageDialog(addScreen, "Please choose your gender !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // check choose file image
        if (file == null) {
            JOptionPane.showMessageDialog(addScreen, "Please choose image !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        WorkerImplement workerImplement = new WorkerImplement();
        // get txtName
        String worker_name = addScreen.getTxtName().getText().trim();
        // get worker_gender
        boolean worker_gender = addScreen.getRadMale().isSelected();
        // convert File to byte
        byte[] image = ConvertFileToByte(file.getAbsolutePath());
        // get Role
        Role role = (Role) addScreen.getjComboBox_Role().getSelectedItem();
        Worker worker = new Worker(worker_name, worker_gender, image, role);
        try {
            // add worker to database
            boolean checkadd = workerImplement.addWorker(worker);
            if (checkadd == true) {
                // show success message
                JOptionPane.showMessageDialog(addScreen, "One Worker is added");
                // dispose 
                addScreen.dispose();
                // show data to jTable again
                mainScreenController.showAllWorker();
            } else {
                // show failed message
                JOptionPane.showMessageDialog(addScreen, "One Worker added failed !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(addScreen, "Something wrong !!!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
}

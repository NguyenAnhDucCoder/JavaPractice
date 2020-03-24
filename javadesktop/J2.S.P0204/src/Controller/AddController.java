/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Common.CommonSub;
import static Controller.MainScreenController.mainScreen;
import Entity.Role;
import Entity.Worker;
import Model.WorkerImplement;
import View.AddScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private DefaultComboBoxModel<Role> modelRole;
    protected AddScreen addScreen;

    public AddController() {
        addScreen = new AddScreen(mainScreen, true);
        modelRole = new DefaultComboBoxModel<>();
        addScreen.getjComboBox_Role().setModel(modelRole);
        listRole();
        addScreen.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addWorker();
            }
        });
        addScreen.getjPanel_Image().addMouseListener(new MouseListener() {
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
        // set JFrame Center
        addScreen.setLocationRelativeTo(null);
        addScreen.setVisible(true);
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

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose image file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            addScreen.getjLabel4().setIcon(new ImageIcon(CommonSub.scaleImageIcon(addScreen.getjLabel4(), new ImageIcon(file.getAbsolutePath()))));
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
    }

    private void addWorker() {
        if (addScreen.getTxtName().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(addScreen, "Worker Name cannot be empty !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            addScreen.getTxtName().requestFocus();
            return;
        }
        if (file == null) {
            JOptionPane.showMessageDialog(addScreen, "Please choose image !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (addScreen.getRadMale().isSelected() == false && addScreen.getRadFemale().isSelected() == false) {
            JOptionPane.showMessageDialog(addScreen, "Please choose your gender !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        WorkerImplement workerImplement = new WorkerImplement();
        String worker_name = addScreen.getTxtName().getText().trim();
        boolean worker_gender = addScreen.getRadMale().isSelected();
        byte[] image = CommonSub.ConvertFile(file.getAbsolutePath());
        Role role = (Role) addScreen.getjComboBox_Role().getSelectedItem();
        Worker worker = new Worker(worker_name, worker_gender, image, role);
        try {
            boolean checkadd = workerImplement.addWorker(worker);
            if (checkadd == true) {
                JOptionPane.showMessageDialog(addScreen, "One Worker is added");
                addScreen.dispose();
                MainScreenController.showAllWorker();
            } else {
                JOptionPane.showMessageDialog(addScreen, "One Worker added failed !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {

        }
    }
}

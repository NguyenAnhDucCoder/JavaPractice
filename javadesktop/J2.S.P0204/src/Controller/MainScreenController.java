/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Worker;
import Model.WorkerImplement;
import View.MainScreen;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ANH DUC
 */
public class MainScreenController {

    public static MainScreen mainScreen;

    public MainScreenController() {
        mainScreen = new MainScreen();
        //Show all worker
        showAllWorker();
        mainScreen.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // call addcontroller
                AddController ac = new AddController();
            }
        });
        mainScreen.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // if not select row
                if (mainScreen.getjTable_Worker().getSelectedRow() == -1) {
                    // show warning message
                    JOptionPane.showMessageDialog(null, "Please select row !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // call UpdateController
                UpdateController updateController = new UpdateController();
            }
        });
        mainScreen.getBtnViewDetail().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // if not select row
                if (mainScreen.getjTable_Worker().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select row !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // call DetailController
                DetailController detailController = new DetailController();
            }
        });
        // set JFrame Center
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setVisible(true);
    }

    public static void showAllWorker() {
        String[] columnName = {"ID", "Name", "Gender", "Image"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                // not edit cell in table
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnName);
        // set model for jTable_Worker
        mainScreen.getjTable_Worker().setModel(tableModel);
        // setting image column
        mainScreen.getjTable_Worker().getColumn("Image").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                // set row height
                mainScreen.getjTable_Worker().setRowHeight(62);
                return (Component) o;
            }
        });
        try {
            // list all worker from database
            List<Worker> workers = new WorkerImplement().getAllWorker();
            // add List<Worker> to tableModel
            for (Worker w : workers) {
                tableModel.addRow(w.dataRow());
            }
        } catch (Exception ex) {
            // show warning message
            JOptionPane.showMessageDialog(null, "Cannot show data !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String getSelectedRow() {
        // get row selected
        int row = mainScreen.getjTable_Worker().getSelectedRow();
        // return content cell of first column and row selected
        return mainScreen.getjTable_Worker().getValueAt(row, 0).toString();
    }
}

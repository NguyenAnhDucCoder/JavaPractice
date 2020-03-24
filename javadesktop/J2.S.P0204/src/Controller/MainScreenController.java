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
        //column names
        showAllWorker();
        mainScreen.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AddController ac = new AddController();
            }
        });
        mainScreen.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UpdateController updateController = new UpdateController();
            }
        });
        mainScreen.getBtnViewDetail().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UpdateController updateController = new UpdateController();
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
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnName);
        mainScreen.getjTable_Worker().setModel(tableModel);
        mainScreen.getjTable_Worker().getColumn("Image").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                mainScreen.getjTable_Worker().setRowHeight(62);
                return (Component) o;
            }
        });
        try {
            List<Worker> workers = new WorkerImplement().getAllWorker();
            for (Worker w : workers) {
                tableModel.addRow(w.dataRow());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot show data !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String getSelectedRow() {
        int row = mainScreen.getjTable_Worker().getSelectedRow();
        int column = 0;
        return mainScreen.getjTable_Worker().getValueAt(row, column).toString();
    }

}

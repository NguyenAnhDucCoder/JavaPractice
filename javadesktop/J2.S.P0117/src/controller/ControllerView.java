package controller;

import entity.Stock;
import gui.MainView;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.InsertModal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class ControllerView {

    MainView view = new MainView();
    DefaultTableModel tableModel;
    int tableRows;
    // create data column name in jtable
    String[] columnNames = {"StockID", "StockName", "Address", "DateAvailable", "Note"};
    //create data in every column in jtable
    String[][] data = {
        {"1", "Stock one", "N01-Washington street", "2010-11-05", ""},
        {"2", "Stock two", "372 Cave town-001 Banks", "2011-09-07", ""},
        {"3", "Stock three", "Nary angle - 890 Number one", "2010-05-13", "Very Dangerous"},
        {"4", "Stock four", "Twin tower - 01 Main street", "2015-04-07", ""},
        {"5", "Stock five", "N01-Washington street", "2010-11-05", ""}
    };

    public ControllerView() {
        //Load data to jtable
        tableModel = new DefaultTableModel(data, columnNames);
        tableRows = data.length;
        view.getjTable1().setModel(tableModel);
        view.getBtnInsert().addActionListener(new btnInsertDB());
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    class btnInsertDB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                InsertModal insertModal = new InsertModal();
                // check connection success or not
                if (insertModal.checkConnection() == false) {
                    JOptionPane.showMessageDialog(null, "Cannot connect to sql !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // If database not exist show message
                if (insertModal.checkExistDatabase() == false) {
                    JOptionPane.showMessageDialog(null, "Database is not exist !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // If table not exist show message
                if (insertModal.checkExistTable() == false) {
                    JOptionPane.showMessageDialog(null, "Table is not exist !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ArrayList<Stock> listStock = new ArrayList();
                // insert all data to the list
                for (int i = 0; i < tableRows; i++) {
                    Stock stock = new Stock();
                    //stock.setStockID(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
                    //stock.setDateAvailable(Date.valueOf(tableModel.getValueAt(i, 3).toString()));
                    stock.setStockID(tableModel.getValueAt(i, 0).toString());
                    stock.setStockName(tableModel.getValueAt(i, 1).toString());
                    stock.setAddress(tableModel.getValueAt(i, 2).toString());
                    stock.setDateAvailable(tableModel.getValueAt(i, 3).toString());
                    stock.setNote(tableModel.getValueAt(i, 4).toString());
                    listStock.add(stock);
                }
                // Insert database
                boolean check = insertModal.InsertToDB(listStock);
                // Display show message dialog whether update database success or not
                if (check == true) {
                    JOptionPane.showMessageDialog(null, "Update success !!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data Error !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database not exist !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Something is wrong !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}

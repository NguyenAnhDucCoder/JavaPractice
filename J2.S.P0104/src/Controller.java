
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class Controller {

    View view = new View();
    int tableRows;

    public Controller() {

        // create data column name in jtable
        String[] columnNames = {"StockID", "StockName", "Address", "DateAvailable", "Note"};
        //create data in every column in jtable
        String[][] data = { 
                    {"1", "Stock one", "N01-Washington street", "11/05/2010", ""},
                    {"2", "Stock two", "372 Cave town-001 Banks", "09/07/2011", ""},
                    {"3", "Stock three", "Nary angle - 890 Number one", "13/05/2010", ""},
                    {"4", "Stock four", "Twin tower - 01 Main street", "04/07/2015", ""},
                    {"5", "Stock five", "N01-Washington street", "11/05/2010", ""}
        };

        //Load data to jtable
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        // find table row
        tableRows = tableModel.getRowCount();
        view.getjTable().setModel(tableModel);
        // select one row if table row not empty
        if (tableRows != 0) {
            // Set row selection at 0 , 0
            view.getjTable().setRowSelectionInterval(0, 0);
        }
        //Create event of every button
        view.getBtnMNext().addActionListener(new btnMoveNextListener());
        view.getBtnMPrevious().addActionListener(new btnMovePreviousListener());
        view.getBtnMFirst().addActionListener(new btnMoveFirstListener());
        view.getBtnMLast().addActionListener(new btnMoveLastListener());
        // set location of form in the middle
        view.setLocationRelativeTo(null);
        // visible form
        view.setVisible(true);
    }

    public void moveRow(int valueMove) {
        if (tableRows == 0) {
            JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //get row selected
        int row = view.getjTable().getSelectedRow();
        //find new row after move
        int newRow = row + valueMove;
        // check whether selection is over top or bottom 
        if (newRow < 0 || newRow >= tableRows) {
            JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            // set select new row
            view.getjTable().setRowSelectionInterval(newRow, newRow);
        }
    }

    class btnMoveFirstListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tableRows == 0) {
                JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Show error message if selected row already in top
            if (view.getjTable().getSelectedRow() == 0) {
                JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                // Move to the top
                view.getjTable().setRowSelectionInterval(0, 0);
            }
        }
    }

    class btnMovePreviousListener implements ActionListener {

        // Move preious row 1
        @Override
        public void actionPerformed(ActionEvent ae) {
            moveRow(-1);
        }
    }

    class btnMoveNextListener implements ActionListener {

        // Move next 1
        @Override
        public void actionPerformed(ActionEvent ae) {
            moveRow(1);
        }
    }

    class btnMoveLastListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tableRows == 0) {
                JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Show error if selected row is already in bottom
            if (view.getjTable().getSelectedRow() == tableRows - 1) {
                JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                // Move to the bottom
                view.getjTable().setRowSelectionInterval(tableRows - 1, tableRows - 1);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANH DUC
 */
public class InsertModal {

    Connection connection = null;

    public InsertModal() {

    }

    public boolean checkConnection() {
        try {
            // Open connect to database
            connection = new DBContext().getConnection("");
            //Close connection
            connection.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean checkExistDatabase() throws SQLException, Exception {
        connection = new DBContext().getConnection("FU_DB");
        // check database exist or not
        String query = "SELECT COUNT (*) as count FROM master.sys.sysdatabases \n"
                + "  WHERE name='FU_DB'";
        // Create object PreparedStatement
        PreparedStatement ps = connection.prepareStatement(query);
        // Execute sql query using PreparedStatement
        ResultSet resultSet = ps.executeQuery();

        int count = 0;
        // set value count 
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        resultSet.close();
        ps.close();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkExistTable() throws Exception {
        String query = "SELECT COUNT (*) as count FROM INFORMATION_SCHEMA.TABLES\n"
                + "          WHERE TABLE_NAME='Stocks'";
        PreparedStatement ps = connection.prepareStatement(query);
        // Execute sql query using PreparedStatement
        ResultSet resultSet = ps.executeQuery();

        int count = 0;
        // set value count 
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        resultSet.close();
        // Close PreparedStatement
        ps.close();
        // check count
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean InsertToDB(List<Stock> stock) throws SQLException {
        try {
            // Create query for insert into data
            String insertdata = "insert into Stocks values(?,?,?,?,?) ";
            // check whether connect to database or not !!!
            PreparedStatement ps = connection.prepareStatement(insertdata);
            // Not execute SQL statements 
            connection.setAutoCommit(false);
            // Access every object in List<stock>
            for (int i = 0; i < stock.size(); i++) {
                Stock stock1 = stock.get(i);
                ps.setString(1, stock1.getStockID());
                ps.setString(2, stock1.getStockName());
                ps.setString(3, stock1.getAddress());
                ps.setString(4, stock1.getDateAvailable());
                ps.setString(5, stock1.getNote());
                // add SQL statement in Batch
                ps.addBatch();
            }
            // execute batch
            ps.executeBatch();
            // clear batch
            ps.clearBatch();
            ps.close(); 
            connection.commit();
        } catch (SQLException ex) {
            // no change when executeBatch if execute errors
            connection.rollback();
            return false;
        } catch (Exception ex) {
            return false;
        }finally{
            connection.close();
        }
        return true;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Address;
import Entity.Opening;
import Entity.Share;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public class FindUsDAO {

    public List<Address> getAddress(int page, int pageSize) throws Exception {
        List<Address> address = new ArrayList<>();
        DBConnection dBConnection = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY id)"
                    + " as RowNumber, * FROM Address) as MP WHERE RowNumber between ? and ? ";
            if (page == 0) {
                page = 1;
            }
            if (pageSize == 0) {
                pageSize = 1;
            }
            int from = (page - 1) * pageSize + 1;
            int to = page * pageSize;
            conn = dBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("NameFind");
                String ad = rs.getString("Address");
                String tel = rs.getString("Tel");
                String email = rs.getString("Email");
                String mapAddress = rs.getString("MapAdress");
                int id = rs.getInt("ID");
                List<Opening> openings = new ArrayList<>();
                String query1 = "Select * from Opening where AddressID = ?";
                ps = conn.prepareStatement(query1);
                ps.setInt(1, id);
                ResultSet rs1 = ps.executeQuery();
                while (rs1.next()) {
                    openings.add(new Opening(rs1.getString("Day"), rs1.getString("Opening")));
                }
                rs1.close();
                address.add(new Address(name, ad, tel, email, mapAddress, openings));
            }
            dBConnection.closeConnection(conn, ps, rs);
        } catch (Exception e) {
            dBConnection.closeConnection(conn, ps, rs);
            throw e;
        }
        return address;
    }

    public int getTotalPages(int pageSize) throws Exception {
        if (getTotalPages() % pageSize == 0) {
            return getTotalPages() / pageSize;
        } else {
            return 1 + getTotalPages() / pageSize;
        }
    }

    public int getTotalPages() throws Exception {
        DBConnection dBConnection = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            String query = "select count(*) from Address";
            conn = dBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            rows = 0;
            if (rs.next()) {
                rows = rs.getInt(1);
            }
            dBConnection.closeConnection(conn, ps, rs);
        } catch (Exception e) {
            dBConnection.closeConnection(conn, ps, rs);
            throw e;
        }
        return rows;
    }
}

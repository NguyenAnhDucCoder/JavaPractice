/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.MenuPrice;
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
public class MenuDAO {

    public List<MenuPrice> getAllMenu(int page, int pageSize) throws Exception {
        List<MenuPrice> menu = new ArrayList<>();
        DBConnection dBConnection = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY id) "
                    + "as RowNumber, * FROM MenuPrice) as MP WHERE RowNumber between ? and ?";
            if (page == 0) {
                page = 1;
            }
            if (pageSize == 0) {
                pageSize = 3;
            }
            int from = (page - 1) * pageSize + 1;
            int to = page * pageSize;
            conn = dBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NameMenu");
                String price = rs.getString("Price");
                String content = rs.getString("Content");
                menu.add(new MenuPrice(id, name, price, content));
            }
            dBConnection.closeConnection(conn, ps, rs);
        } catch (Exception e) {
            dBConnection.closeConnection(conn, ps, rs);
            throw e;
        }
        return menu;
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
            String query = "select count(*) from MenuPrice";
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

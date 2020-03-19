/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author ANH DUC
 */
public class DBConnection {

    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            InitialContext context = new InitialContext();
            Context environment = (Context) context.lookup("java:/comp/env");
            String user = (String) environment.lookup("UserName");
            String pass = (String) environment.lookup("Password");
            String url = (String) environment.lookup("UrlConnection");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw e;
        }
    }

    public String getImagePath() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            InitialContext context = new InitialContext();
            Context environment = (Context) context.lookup("java:/comp/env");
            String path = (String) environment.lookup("ImagePath");
            return path;
        } catch (Exception e) {
            throw e;
        }
    }

    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (Exception e) {
            throw e;
        }

    }
}

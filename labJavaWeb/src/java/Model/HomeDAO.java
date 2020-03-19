/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Home;
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
public class HomeDAO {

    public List<Home> getImageIntro() throws Exception {
        DBConnection db = new DBConnection();
        List<Home> intro = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Select Image from Home where Title = 'intro'";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String image = db.getImagePath() + rs.getString("Image");
                intro.add(new Home(image));
            }
            db.closeConnection(conn, ps, rs);
        } catch (Exception e) {
            db.closeConnection(conn, ps, rs);
            throw e;
        }
        return intro;
    }

    public List<Home> getIntro() throws Exception {
        DBConnection db = new DBConnection();
        List<Home> intro = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Select * from Home where Image = 'home.jpg'";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString("Title");
                String content = rs.getString("Content");
                String image = db.getImagePath() + rs.getString("Image");
                intro.add(new Home(title, image, content));
            }
            db.closeConnection(conn, ps, rs);
        } catch (Exception e) {
            db.closeConnection(conn, ps, rs);
            throw e;
        }
        return intro;
    }
}

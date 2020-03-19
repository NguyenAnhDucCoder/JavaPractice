/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class SocialDAO {

    public List<Share> getSocial() throws Exception {
        List<Share> SocialNetwork = new ArrayList<>();
        DBConnection dBConnection = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String icon = null, social = null;
        try {
            String query = "select * from Share";
            conn = dBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                icon = dBConnection.getImagePath() + rs.getString("Icon");
                social = rs.getString("Link");
                SocialNetwork.add(new Share(icon, social));
            }
            dBConnection.closeConnection(conn, ps, rs);
        } catch (Exception e) {
            dBConnection.closeConnection(conn, ps, rs);
            throw e;
        }
        return SocialNetwork;
    }
}

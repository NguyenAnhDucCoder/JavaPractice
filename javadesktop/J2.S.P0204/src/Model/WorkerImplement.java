/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Role;
import Entity.Worker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public class WorkerImplement implements WorkerInterface {

    @Override
    public List<Role> getAllRole() throws Exception {
        List<Role> listRoles = new ArrayList<>();
        DBContext dBContext = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Select * from [Role]";
            conn = dBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("ID"));
                r.setRoleName(rs.getString("RoleName"));
                listRoles.add(r);
            }
            return listRoles;
        } catch (Exception e) {
            throw e;
        } finally {
            dBContext.closeConnection(rs, ps, conn);
        }
    }

    @Override
    public List<Worker> getAllWorker() throws Exception {
        List<Worker> listWorkers = new ArrayList<>();
        DBContext dBContext = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Select * from [Worker]";
            conn = dBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getBoolean("Gender"),
                        rs.getBytes("Image")
                );
                listWorkers.add(worker);
            }
            return listWorkers;
        } catch (Exception e) {
            throw e;
        } finally {
            dBContext.closeConnection(rs, ps, conn);
        }
    }

    @Override
    public Worker getDetailWorker(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Select w.ID, w.[Name], w.Gender, w.RoleID, "
                    + "w.[Image], r.RoleName from [Worker] w INNER JOIN [Role] r"
                    + " ON w.RoleID = r.ID where w.ID = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Worker worker = new Worker(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getBoolean("Gender"),
                        rs.getBytes("Image"),
                        new Role(rs.getInt("RoleID"),
                                rs.getString("RoleName"))
                );
                return worker;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    @Override
    public boolean updateWorker(Worker worker) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Update [Worker] set [Name] = ?, [Gender] = ?, "
                    + "[Image] = ? , [RoleID] = ? where [ID] = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, worker.getName());
            ps.setBoolean(2, worker.isGender());
            ps.setBytes(3, worker.getImage());
            ps.setInt(4, worker.getRole().getId());
            ps.setInt(5, worker.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    @Override
    public boolean addWorker(Worker worker) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Insert into [Worker] (Name, Gender, Image, RoleID)"
                    + " VALUES (?,?,?,?)";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, worker.getName());
            ps.setBoolean(2, worker.isGender());
            ps.setBytes(3, worker.getImage());
            ps.setInt(4, worker.getRole().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}

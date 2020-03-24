/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Common.CommonSub;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ANH DUC
 */
public class Worker {

    private int id;
    private String name;
    private boolean gender;
    private byte[] image;
    private Role role;

    public Worker() {
    }

    public Worker(int id, String name, boolean gender, byte[] image) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.image = image;
    }

    public Worker(String name, boolean gender, byte[] image, Role role) {
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.role = role;
    }

    public Worker(int id, String name, boolean gender, byte[] image, Role role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Object[] dataRow() {
        try {
            Image img = ImageIO.read(new ByteArrayInputStream(image));
            ImageIcon imageIcon = new ImageIcon(img);
            JLabel jLabel = new JLabel();
            jLabel.setSize(60, 60);
            jLabel.setIcon(new ImageIcon(CommonSub.scaleImageIcon(jLabel, imageIcon)));
            String gender = "";
            if (this.gender == true) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            return new Object[]{id,name, gender, jLabel};
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return null;
    }
}

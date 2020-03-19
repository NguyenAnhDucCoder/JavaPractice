/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Home;
import Model.HomeDAO;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public class HomeBean {
    public List<Home> getImageIntro() throws Exception{
        return new HomeDAO().getImageIntro();
    }
    public List<Home> getIntro()throws Exception{
        return new HomeDAO().getIntro();
    }
}

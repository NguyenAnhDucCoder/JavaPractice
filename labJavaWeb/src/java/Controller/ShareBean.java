/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Share;
import Model.SocialDAO;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public class ShareBean {
    public List<Share> getSocial() throws Exception {
        return new SocialDAO().getSocial();
    }
}

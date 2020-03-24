
import controller.ClientAppController;
import controller.ServerAppController;
import gui.ClientApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANH DUC
 */
public class MainProgram {
    
    public static void main(String[] args) {
        ServerAppController appController = new ServerAppController();
       ClientAppController clientAppController = new ClientAppController();
    }
}

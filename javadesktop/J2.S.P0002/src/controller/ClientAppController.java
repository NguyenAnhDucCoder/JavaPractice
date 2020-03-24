/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import event_client.txtClientFilePathClick;
import event_client.btnClientSend;
import entity.Data;
import gui.ClientApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author ANH DUC
 */
public class ClientAppController {

    public static Socket serverSocket = null;
    public static ObjectOutputStream outputStream;
    public static ClientApp clientApp = new ClientApp();
    public static JFileChooser j = new JFileChooser();
    public static String path;

    public ClientAppController() {
        // set visible form
        clientApp.setVisible(true);
        // add event for buttons
        clientApp.getTxtClientFilePath().addMouseListener(new txtClientFilePathClick());
        clientApp.getBtnClientSend().addActionListener(new btnClientSend());

    }
}

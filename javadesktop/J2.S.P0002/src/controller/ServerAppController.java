/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import event_server.txtServerFilePathClick;
import event_server.btnServerStart;
import event_server.btnServerSave;
import entity.Data;
import gui.ServerApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class ServerAppController {

    public static ServerSocket serverSocket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static String path;
    public static String filename;
    public static byte[] file_recieve;
    public static JFileChooser j = new JFileChooser();

    public static ServerApp serverApp = new ServerApp();

    public ServerAppController() {
        // set visible form
        serverApp.setVisible(true);
        serverApp.getBtnServerSave().setEnabled(false);
        // create event of button
        serverApp.getBtnServerStart().addActionListener(new btnServerStart());
        serverApp.getBtnServerSave().addActionListener(new btnServerSave());
        serverApp.getTxtSeverFilePath().addMouseListener(new txtServerFilePathClick());
    }
}

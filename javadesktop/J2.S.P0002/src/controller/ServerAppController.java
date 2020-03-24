/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import event_server.txtServerFilePathClick;
import event_server.btnServerStart;
import event_server.btnServerSave;
import gui.ServerApp;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import javax.swing.JFileChooser;

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

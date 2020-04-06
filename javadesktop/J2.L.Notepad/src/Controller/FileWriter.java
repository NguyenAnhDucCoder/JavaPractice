package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class FileWriter {

    private String path;
    private String content;

    public FileWriter() {
    }

    public FileWriter(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public void WriteFile() {
        try (PrintWriter printWriter = new PrintWriter(new File(path))) {
            // print content to file
            printWriter.print(content);
            printWriter.close();
        } catch (Exception e) {
            // show warning message
            JOptionPane.showMessageDialog(null, "Something wrong", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

}

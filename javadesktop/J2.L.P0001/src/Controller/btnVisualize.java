/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Edge;
import Entity.Vertex;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ANH DUC
 */
public class btnVisualize implements ActionListener {

    public static List<Edge> listEdges;
    public static List<Vertex> listVertex;

    public btnVisualize() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        listEdges = new ArrayList<>();
        listVertex = new ArrayList<>();
        analizeInput();
    }

    public void analizeInput() {

        StringTokenizer st = new StringTokenizer(SimpleGraphController.simpleGraph.getjTextArea_Input().getText(), "\n");
        String getInput = null;
        while (st.hasMoreTokens()) {
            getInput = st.nextToken().trim();
            if (getInput.length() > 1 && getInput.contains("{")) {
                String nameGraph = getInput.substring(0, getInput.length() - 1);
                SimpleGraphController.simpleGraph.getjLabel_GraphName().setText("Graph - " + nameGraph);
            } else if (getInput.contains("label") && getInput.contains("color")) {
                getVertexInfor(getInput);
            }
        }
    }

    public void getVertexInfor(String vertexInfor) {
        Vertex vertex = new Vertex();
        StringTokenizer st = new StringTokenizer(vertexInfor, "\" []");
        boolean isName = true;
        while (st.hasMoreTokens()) {
            String input = st.nextToken().trim();
            if (isName) {
                vertex.setName(input);
                isName = false;
                continue;
            }
            if (input.contains("label")) {
                vertex.setLabel(st.nextToken().trim());
            } else if (input.contains("color")) {
                Field field;
                try {
                    field = Class.forName("java.awt.Color").getField(st.nextToken().trim());
                    vertex.setColor((Color) field.get(null));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SimpleGraphController.simpleGraph, "Color is incorrect", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        listVertex.add(vertex);
    }

    public void getEdgesInfor(String edgeInfor) {
        Edge edge = new Edge();
        StringTokenizer st = new StringTokenizer(edgeInfor, "[] \"->");
        boolean isFrom = true;
        while (st.hasMoreTokens()) {
            String input = st.nextToken().trim();
            if (isFrom) {
                edge.setFromVertex(input);
                edge.setToVertex(st.nextToken().trim());
                isFrom = false;
                continue;
            }
            if (input.contains("label")) {
                edge.setLabel(input);
            }
        }
        listEdges.add(edge);
    }

}

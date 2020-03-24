package Controller;


import View.SimpleGraph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class SimpleGraphController {

    public static SimpleGraph simpleGraph;


    public SimpleGraphController() {
        simpleGraph = new SimpleGraph();
        simpleGraph.getjButton_Visualize().addActionListener(new btnVisualize());
        simpleGraph.setVisible(true);
    }
    


}

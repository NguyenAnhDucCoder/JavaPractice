/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ANH DUC
 */
public class Edge {

    private String label;
    private String fromVertex;
    private String toVertex;

    public Edge() {
    }

    public Edge(String label, String fromVertex, String toVertex) {
        this.label = label;
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(String fromVertex) {
        this.fromVertex = fromVertex;
    }

    public String getToVertex() {
        return toVertex;
    }

    public void setToVertex(String toVertex) {
        this.toVertex = toVertex;
    }

}

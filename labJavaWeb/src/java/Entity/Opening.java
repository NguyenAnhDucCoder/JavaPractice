/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

public class Opening {
    private String day;
    private String opening;

    public Opening() {
    }

    public Opening(String day, String opening) {
        this.day = day;
        this.opening = opening;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

public class Home {
    private String title;
    private String image;
    private String content;

    public Home() {
    }

    public Home(String title, String image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public Home(String image) {
        this.image = image;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}

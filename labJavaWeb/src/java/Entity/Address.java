/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;

public class Address {

    private String name;
    private String address;
    private String tel;
    private String email;
    private String mapAddress;
    private List<Opening> openings;

    public Address() {
    }

    public Address(String name, String address, String tel, String email, String mapAddress, List<Opening> openings) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.mapAddress = mapAddress;
        this.openings = openings;
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Opening> getOpenings() {
        return openings;
    }

    public void setOpenings(List<Opening> openings) {
        this.openings = openings;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthew
 */
public class Person {

    String id;
    String username;
    int orderFromTop;

    public Person(String id, String username, int orderFromTop) {
        this.id = id;
        this.username = username;
        this.orderFromTop = orderFromTop;
    }

    // ===================GETERS AND SETTERS===================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderFromTop() {
        return orderFromTop;
    }

    public void setOrderFromTop(int orderFromTop) {
        this.orderFromTop = orderFromTop;
    }

}

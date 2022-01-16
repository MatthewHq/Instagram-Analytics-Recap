
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew
 */
public class Likes {

    int order;
    String id;
    int count;
    HashMap<Integer, Person> people = new HashMap();

    public Likes(String id, int count) {
        this.id = id;
        this.count = count;
        order = 0;
    }

    public void addPerson(String id, String username) {
        Person stranger = new Person(id, username, order);
        boolean flag = false;
        for (Person p : people.values()) {
            if (p.getId().equals(id)) {
                flag = true;
            }
        }

        if (!flag) {
            people.put(order, stranger);
            order++;
        }

    }

    // ===================GETERS AND SETTERS===================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HashMap<Integer, Person> getPeople() {
        return people;
    }
//

    public void setPeople(HashMap<Integer, Person> people) {
        this.people = people;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthew
 */
public class LinkedListo<E> {

    LinkedListo<E> next;    E val;

    public LinkedListo() {
        val = null;
        next = null;
    }

    public LinkedListo<E> getNext() {
        return next;
    }

    public void setNext(LinkedListo<E> next) {
        this.next = next;
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

}

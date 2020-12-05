/*
Name: William Alger
 */
package com.company;

import java.util.Iterator;
import java.util.Objects;

public class MyCircularLinkedList<E> implements MyList<E> {
    private int size = 0;
    Node<E> tail = null;
    public static void main(
            String[] args) {
        MyCircularLinkedList<String> list = new MyCircularLinkedList<>();
        list.add("America"); // Add it to the list
        System.out.println("(1) "  );
    }


    public MyCircularLinkedList()
    {

    }


    public class Node<E> {
        E element;
        Node<E> next;

        public Node(E e) {
            this.element = e;
        }
    }

    @Override
    public void add(int index, E e) {
        if (index == 0)
            addFirst(e);
        else if (index == size) {
            addLast(e);
        }
        else {
            Node<E> current = tail.next; //start at first node
            for (int i = 0; i < index; i++) current = current.next;
            Node<E> tmp = current.next;
            Node<E> node = new Node<>(e); // create our new node
            current.next = node;
            node.next = tmp;
            size++;
        }
    }

    public void addFirst(E e) {
        Node<E> node = new Node<>(e);
        if (tail == null) {
            node = tail;
            size++;
        }
    }

    public void addLast(E e) {
        Node<E> node = new Node<>(e);
        if (tail == null) { // tail is only going to be null if the linked list is empty
            tail = node;
            size++;
        }
        else {
            Node<E> tmp = tail.next; //store link to first node as temp;
            tail.next = node; //link old tail to new node
            node.next = tmp; //link new node to first node
            tail = node; //set the new tail as the new node
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0)
            return null;
        else {
            Node<E> tmp = tail.next; //store the first node as temp
            tail.next = (tail.next).next; //connect the tail.next link to the node after the first
            size--;
            return tmp.element; //return first element
        }
    }

    public E removeLast() {
        if (size == 0)
            return null;
        else if (size == 1) { //if there is only one node in our linked list
            Node<E> tmp = tail;
            tail = null;
            size--;
            return tmp.element;
        }
        else {
            Node<E> current = tail.next; //start at first node
            Node<E> tmp = tail;
            while (current.next != tail) current = current.next; //find the node right before tail
            current.next = tail.next; // link the previous node before tail to first node skipping tail
            tail = current; // set current node to the new tail
            size--;
            return tmp.element; //return removed element
        }
    }

    @Override
    public E get(int index) {
        Node<E> current = tail.next;
        for (int i=0; i<index; i++) current = current.next;
        return current.element;
    }

    @Override
    public int indexOf(Object e) {
        if (size > 0) { // if our list isn't empty
            int index = 0;
            Node<E> current = tail.next;
            while (current != e) { //while our current object isn't the object we're looking for
                current = current.next;
                index++;
            }
            if (current == e) return index; //if we found the object, return its index
            else return -1; //if not found
        }
        else return -1; //if list is empty
    }

    @Override
    public int lastIndexOf(E e) {
        if (size > 0) {
            return 1;
        }
        else return -1;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E set(int index, E e) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return null;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }


    public E getFirst() {
        return tail.next.element;
    }

    public E getLast() {
        if (size == 0)
            return null;
        else
            return tail.element;
    }
}



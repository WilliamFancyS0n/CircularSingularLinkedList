/*
Name: William Alger
 */
package com.company;
import java.util.Iterator;

public class MyCircularLinkedList<E> implements MyList<E> {
    private int size = 0;
    Node<E> tail = null;


    public MyCircularLinkedList(){}


    public class Node<E> {
        E element;
        Node<E> next;
        public Node(E e)
        {
            this.element = e;
        }
    }

    @Override
    public void add(int index, E e) {
        if (index == 0)
            addFirst(e);
        else if (index >= size)

    }
    public void addFirst(E e) {
        Node<E> node = new Node<>(e);
        node.next = head;
        head = node;
        size++;
        if (tail == null)
            tail = head;
    }

    public void addLast(E e) {
        Node<E> node = new Node<>(e);
        if (tail == null)
            head = tail = node;
        else {
            tail.next = node;
            tail = node;
        }
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int indexOf(Object e) {
        return 0;
    }

    @Override
    public int lastIndexOf(E e) {
        return 0;
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
        return 0;
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
        return null;
    }

    public E getLast() {
        if (size == 0)
            return null;
        else
            return tail.element;
    }

    public E removeFirst() {
        if (size == 0)
            return null;
        else {

        }
    }

    public E removeLast() {
        if (size == 0)
            return null;
        else if (size == 1) {

        }
    }



}



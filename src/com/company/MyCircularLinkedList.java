/*
Name: William Alger
 */
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.lang.StringBuilder;

public class MyCircularLinkedList<E> implements MyList<E> {
    private static int size = 0;
    Node<E> tail = null;
    public static void main(String[] args)
    {
//        MyCircularLinkedList<String> list = new MyCircularLinkedList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        System.out.println(list);
//        list.remove(1);
//        System.out.println(list);
    }


    public MyCircularLinkedList()
    {

    }

    public MyCircularLinkedList(E[] objects)
    {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }


    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean contains(Object o)
    {
        if (size == 0)
        {
            return false;
        }
        else {
            Node<E> current = tail.next;
            for (int i = 0; i < size; i++) {
                if (current.equals(o)) return true;
                current = current.next;
            }
            return false;
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        return new CircularLinkedListIterator();
    }

    @Override
    public void clear()
    {
        size = 0;
        tail = null;
    }


    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override
    public void add(int index, E e) {
        if (index == 0)
            addFirst(e);
        else if (index >= size) {
            addLast(e);
        }
        else {
            Node<E> current = tail; //start at first node
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
            tail = node;
            node.next = tail;
        }
        else {
            Node<E> tmp = tail.next; //store link to original first node
            tail.next = node; //link tail to new node
            node.next = tmp; //link new node to previously first node

        }
        size++;
    }

    public void addLast(E e) {
        Node<E> node = new Node<>(e);

        if (tail == null) {
            tail = node;
            node.next = tail;
        }

        else {
            Node<E> tmp = tail.next; //store link to first node as temp;
            tail.next = node; //link old tail to new ode
            node.next = tmp; //link new node to first node
            tail = node; //set the new tail as the new node
        }
        size++;
    }
    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size) {
            return null;
        }
        else if (index == 0)
        {
            return removeFirst();

        }
        else if (index >= size-1)
        {
            return removeLast();
        }
        else
        {
            Node<E> current = tail.next;
            for (int i=1; i < index; i++) current = current.next;
            Node<E> tmp = current.next;
            current.next = (current.next).next;
            size--;
            return tmp.element;
        }
    }


    public E removeFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        else {
            Node<E> tmp = tail.next; //store the first node as temp
            tail.next = (tail.next).next; //connect the tail.next link to the node after the first
            size--;
            return tmp.element; //return first element
        }
    }

    public E removeLast() {
        if (size == 0)
            throw new NoSuchElementException();
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
        if (size == 0)
            throw new IndexOutOfBoundsException();
        else if (index == size-1)
            return tail.element;
        else{
            Node<E> current = tail.next;
            for (int i=0; i<index; i++) current = current.next;
            return current.element;
        }
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
    public E set(int index, E e)
    {
        if (size == 0) { // if list is empty, add the new object as the first object to a new list;
            addFirst(e);
            return null;
        }
        else if (index > size) {
            addLast(e);
            return null;
        }
        else {
            Node<E> node = new Node<>(e); //create a new node
            Node<E> current = tail.next; //set the pointer to the tail
            for (int i = 0; i < index; i++) { //iterate to the node before the index we're replacing/setting
                current = current.next;
            }
            Node<E> tmp = current.next; // copy the node we're replacing
            current.next = node; // point the previous node to our new node
            node.next = tmp.next;
            return tmp.element;
        }



    }


    public String toString()
    {
        StringBuilder result = new StringBuilder("[");
        Node<E> current;
        if (size > 1) current = tail.next;
        else current = tail;

        for (int i = 0; i < size; i++) {
            result.append(current.element);
            if (current.next != tail.next)
            {
                result.append(", ");
            }
            current = current.next;


        }
        result.append("]");
        return result.toString();
    }


    public E getFirst() {
        if (size == 0) throw new NoSuchElementException();
        else return tail.next.element;
    }

    public E getLast() {
        if (size == 0) throw new NoSuchElementException();
        else return tail.element;
    }
    private class CircularLinkedListIterator implements java.util.Iterator<E> {

        private Node<E> current = tail.next;

        @Override
        public boolean hasNext()
        {
           return current.next != tail.next;
        }

        @Override
        public E next()
        {
            E e = current.element;
            current = current.next;
            return e;
        }
    }







}



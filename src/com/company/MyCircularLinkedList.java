/*
Name: William Alger
Date: Saturday, December 5th, 2020
Purpose of program:
    This is an implementation of a circular singularly linked list with tail pointer that inherits from the generic MyList class
 */
package com.company;
import java.util.*;
import java.lang.StringBuilder;
public class MyCircularLinkedList<E> implements MyList<E> {
    private static int size = 0;
    Node<E> tail = null; //Tail pointer

//    public static void main(String[] args) {
//
//    } //test things here individually

    public MyCircularLinkedList() { }

    //our node class
    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element) {
            this.element = element;
        }
        @Override
        public boolean equals(Object o){ //Override our equals method so that we can use .equals() between nodes comparing their elements
            return element.equals(o);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o)
    {
        if (size ==0) return false;
        Node<E> current = tail.next;
        for (int i = 0; i < size; i++) {
            if (current.equals(o)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        tail = null;
    }

    @Override
    public void add(int index, E e) {
        if (index == 0)
            addFirst(e);
        else if (index == size)
            addLast(e);
        else if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
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
    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        else if (index == 0) return removeFirst();
        else if (index == size-1) return removeLast();
        else {
            Node<E> current = tail.next;
            for (int i=1; i < index; i++) current = current.next;
            Node<E> tmp = current.next;
            current.next = (current.next).next;
            size--;
            return tmp.element;
        }
    }
    public E removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        else {
            Node<E> tmp = tail.next; //store the first node as temp
            tail.next = (tail.next).next; //connect the tail.next link to the node after the first
            size--;
            return tmp.element; //return first element
        }
    }
    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        else if (size == 1) { //if there is only one node in our linked list, its going to be the tail;
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
        if (index < 0  || index > size-1)
            throw new IndexOutOfBoundsException();
        else if (index == size-1)
            return getLast();
        else if (index == 0)
            return getFirst();
        else {
            Node<E> current = tail.next;
            for (int i=0; i<index; i++) current = current.next;
            return current.element;
        }
    }

    public E getFirst() {
        if (size == 0) throw new NoSuchElementException();
        else return tail.next.element; // Since it is a circular list, the first element is always tail.next
    }

    public E getLast() {
        if (size == 0) throw new NoSuchElementException();
        else return tail.element;
    }

    @Override
    public int indexOf(Object e) {
        if (size > 0) {
            int index = 0;
            Node<E> current = tail.next;
            while (index < size) {
                if (current.equals(e))
                    return index; //this will always return the first found index of our match starting from the beginning of our linked list
                index++;
                current = current.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        PriorityQueue<Integer> list = new PriorityQueue<>(Collections.reverseOrder());
        if (size > 0) {
            int index = 0;
            Node<E> current = tail.next;
            while (index < size) {
                if (current.equals(e))
                    list.add(index); //if we find a match, we're going to add the index to a priority queue
                index++;
                current = current.next;
            }
            if (!list.isEmpty())
                return list.poll(); //list.poll() is always going to return the highest index if there was a match
        }
       return -1; //if no matches
    }

    @Override
    public E set(int index, E e)
    {
        if (index < 0 || index > size-1) throw new IndexOutOfBoundsException();
        else {
            Node<E> current = tail.next; //start at the first node
            Node<E> node = new Node<>(e); //create a new node with inserted parameter
            for (int i = 1; i < index; i++) current = current.next; //traverse to the node before the one we're changing
            Node<E> tmp = current.next; //copy the node we're replacing
            current.next = node; //link the node we're on to our new node.
            node.next = tmp.next; //link our new node back to the other end of the chain
            return tmp.element; //return the node that is no longer linked to anything
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

    @Override
    public Iterator<E> iterator()
    {
        return new CircularLinkedListIterator();
    }

    private class CircularLinkedListIterator implements java.util.Iterator<E> {

        private Node<E> current = tail.next;
        boolean isCalled = false;
        E lastReturned;
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
            isCalled = true; // allows you to now call the remove method;
            lastReturned = e;
            return e;
        }
        @Override
        public void remove()
        {
           if (!isCalled) throw new IllegalStateException();
           else {
               MyCircularLinkedList.this.remove(lastReturned);
           }
           isCalled = false; // makes sure that you have to call the next() method again before calling remove() in order to not get an IllegalStateException
        }
    }
}



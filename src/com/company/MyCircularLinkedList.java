/*
Name: William Alger
Date: Saturday, December 5th, completed at 1:54 AM :)
Purpose of program:
    This is an implementation of a circular singularly linked list with tail pointer.




 */
package com.company;

import java.util.*;
import java.lang.StringBuilder;

public class MyCircularLinkedList<E> implements MyList<E> {
    private static int size = 0;
    Node<E> tail = null;


//    public static void main(String[] args) // main to test methods individually
//    {
//
//    }

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
        if (size == 0) return false;
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

        @Override
        public boolean equals(Object o){
            return element.equals(o);
        }
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
    public E remove(int index)
    {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        else if (index == 0) return removeFirst();
        else if (index == size-1) return removeLast();
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
        if (size == 0 || index < 0  || index > size-1)
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
    public int indexOf(Object e)
    {
        if (size > 0) {
            int index = 0;
            Node<E> current = tail.next;
            while (index < size) {
                if (current.equals(e))
                    return index;
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
                    list.add(index);
                index++;
                current = current.next;
            }
            if (!list.isEmpty())
                return list.poll();
        }
       return -1;
    }
    @Override
    public E set(int index, E e)
    {
        if (index < 0 || index > size-1) throw new IndexOutOfBoundsException();
        else {
            Node<E> current = tail.next;
            Node<E> node = new Node<>(e);
            for (int i = 1; i < index; i++) current = current.next;
            Node<E> tmp = current.next; //copy the node we're replacing
            current.next = node;
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
            isCalled = true;
            lastReturned = e;
            return e;
        }
        @Override
        public void remove()
        {
           if (isCalled == false) throw new IllegalStateException();
           else {
               MyCircularLinkedList.this.remove(lastReturned);
           }
           isCalled = false;
        }
    }
}



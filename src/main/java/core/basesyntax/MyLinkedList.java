package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void add(T value) {
    }

    @Override
    public void add(T value, int index) {
    }

    @Override
    public void addAll(List<T> list) {
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(T value, int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean remove(T object) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int indexOf(T object) {
        if (head == null) {
            return -1;
        }
        Node<T> node = head;
        int i = 0;
        while (node != null) {
            if (node.value == object || (node.value != null && node.value.equals(object))) {
                return i;
            }
            node = node.next;
            i++;
        }
        return -1;
    }

    private Node<T> nodeOf(int index) {
        if (index < 0) {
            return null;
        }
        Node<T> node = head;
        int i = 0;
        while (i < index) {
            node = node.next;
            i++;
        }
        return node;
    }

    private static class Node<T> {
        Node<T> prev;
        T value;
        Node<T> next;

        Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}

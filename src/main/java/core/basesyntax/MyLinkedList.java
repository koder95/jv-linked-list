package core.basesyntax;

import java.util.List;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void add(T value) {
        if (head == null && tail == null) {
            head = new Node<>(null, value, null);
            tail = head;
            size++;
        } else if (head == tail) {
            tail = new Node<>(head, value, null);
            head.next = tail;
            size++;
        } else {
            Node<T> node = new Node<>(tail, value, null);
            tail.next = node;
            tail = node;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index == 0) {
            if (head == null) {
                head = new Node<>(null, value, null);
                tail = head;
            } else {
                head.prev = new Node<>(null, value, head);
                head = head.prev;
            }
            size++;
        } else if (index < size) {
            Node<T> current = nodeOf(index);
            Node<T> prev = current.prev;
            Node<T> node = new Node<>(prev, value, current);
            prev.next = node;
            current.prev = node;
            size++;
        } else if (index == size) {
            add(value);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (T t : list) {
            add(t);
        }
    }

    @Override
    public T get(int index) {
        return nodeOf(index).value;
    }

    @Override
    public T set(T value, int index) {
        Node<T> node = nodeOf(index);
        T old = node.value;
        node.value = value;
        return old;
    }

    @Override
    public T remove(int index) {
        if (index == 0) {
            return unlink(head);
        } else if (!isEmpty() && index == size - 1) {
            return unlink(tail);
        }
        Node<T> node = nodeOf(index);
        return unlink(node);
    }

    @Override
    public boolean remove(T object) {
        int index = indexOf(object);
        if (index < 0) {
            return false;
        }
        try {
            remove(index);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private T unlink(Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is not exist",
                    new NoSuchElementException(new NullPointerException()));
        }
        Node<T> prev = node.prev;
        node.prev = null;
        Node<T> next = node.next;
        node.next = null;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
        size--;
        T value = node.value;
        node.value = null;
        return value;
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
        checkIndex(index);
        if (index == 0) {
            return head;
        } else if (index == size - 1) {
            return tail;
        }
        int half = size / 2;
        boolean fromBegin = index < half;
        Node<T> node = fromBegin ? head : tail;
        int i = fromBegin ? 0 : size - 1;
        while (i != index) {
            if (fromBegin) {
                node = node.next;
                i++;
            } else {
                node = node.prev;
                i--;
            }
        }
        return node;
    }

    private static class Node<T> {
        private Node<T> prev;
        private T value;
        private Node<T> next;

        private Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}

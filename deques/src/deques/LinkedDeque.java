package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        // TODO: replace this with your code

        front = new Node<T>(null, null, null);
        back = new Node<T>(null, front, null);
        front.next = back;
    }

    public void addFirst(T item) {
        size += 1;
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        Node<T> node = new Node<T>(item, front, front.next);
        front.next.prev = node;
        front.next = node;

    }

    public void addLast(T item) {
        size += 1;
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        Node<T> node = new Node<T>(item, back.prev, back);
        back.prev.next = node;
        back.prev = node;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        T item = front.next.value;
        front = front.next;
        front.prev = null;
        front.value = null;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        T item = back.prev.value;
        back = back.prev;
        back.next = null;
        back.value = null;
        return item;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        Node<T> node;
        if (size / 2 > index) {
            // Start from front
            node = front.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            // Start from back
            node = back.prev;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node.value;
    }

    public int size() {
        return size;
    }
}

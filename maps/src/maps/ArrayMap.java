package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ArrayMap<K, V> extends AbstractIterableMap<K, V> {
    // TODO: define a reasonable default value for the following field
    private static final int DEFAULT_INITIAL_CAPACITY = 4;
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    SimpleEntry<K, V>[] entries;

    // You may add extra fields or helper methods though!

    private int size;

    /**
     * Constructs a new ArrayMap with default initial capacity.
     */
    public ArrayMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructs a new ArrayMap with the given initial capacity (i.e., the initial
     * size of the internal array).
     *
     * @param initialCapacity the initial capacity of the ArrayMap. Must be > 0.
     */
    public ArrayMap(int initialCapacity) {
        this.entries = this.createArrayOfEntries(initialCapacity);
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code Entry<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     */
    @SuppressWarnings("unchecked")
    private SimpleEntry<K, V>[] createArrayOfEntries(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
        arrays and generics interact.
        */
        return (SimpleEntry<K, V>[]) (new SimpleEntry[arraySize]);
    }

    @Override
    public V get(Object key) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        for (Entry<K, V> entry: this) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        for (int i = 0; i < size; i++) {
            if (key.equals(entries[i].getKey())) {
                V oldValue = entries[i].getValue();
                entries[i].setValue(value);
                return oldValue;
            }
        }

        if (entries.length == size) {
            SimpleEntry<K, V>[] oldEntries = entries;
            entries = createArrayOfEntries(size * 2);
            for (int i = 0; i < size; i++) {
                entries[i] = oldEntries[i];
            }
        }

        entries[size++] = new SimpleEntry<>(key, value);

        return null;
    }

    @Override
    public V remove(Object key) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        for (int i = 0; i < size; i++) {
            if (key.equals(entries[i].getKey())) {
                V value = entries[i].getValue();
                entries[i] = entries[--size];
                entries[size] = null;
                return value;
            }
        }

        return null;
    }

    @Override
    public void clear() {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        while (0 > size) {
            entries[--size] = null;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        for (Entry<K, V> entry: this) {
            if (key.equals(entry.getKey())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: You may or may not need to change this method, depending on whether you
        // add any parameters to the ArrayMapIterator constructor.
        return new ArrayMapIterator<>(this.entries);
    }

    // TODO: after you implement the iterator, remove this toString implementation
    // Doing so will give you a better string representation for assertion errors the debugger.
    /*
    @Override
    public String toString() {
        return super.toString();
    }
    */

    private static class ArrayMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private final SimpleEntry<K, V>[] entries;
        // You may add more fields and constructor parameters

        private int current;

        public ArrayMapIterator(SimpleEntry<K, V>[] entries) {
            this.entries = entries;
            current = 0;
        }

        @Override
        public boolean hasNext() {
            // TODO: replace this with your code
            // throw new UnsupportedOperationException("Not implemented yet.");

            return entries.length > current && null != entries[current];
        }

        @Override
        public Map.Entry<K, V> next() {
            // TODO: replace this with your code
            // throw new UnsupportedOperationException("Not implemented yet.");

            if (hasNext()) {
                return entries[current++];
            }

            throw new NoSuchElementException();
        }
    }
}

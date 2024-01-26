package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {
    // TODO: define reasonable default values for each of the following three fields
    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 0.75;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 16;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 2;

    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    AbstractIterableMap<K, V>[] chains;

    // You're encouraged to add extra fields (and helper methods) though!

    private double resizingLoadFactorThreshold;
    private int initialChainCapacity;
    private int size;

    private int hashCode2Index(Object obj) {
        int index = obj.hashCode() % chains.length;
        if (0 > index) {
            index += chains.length;
        }

        return index;
    }

    private void resize() {
        AbstractIterableMap<K, V>[] oldChains = chains;
        chains = createArrayOfChains(oldChains.length * 2);
        size = 0;
        for (int i = 0; i < oldChains.length; i++) {
            if (null != oldChains[i]) {
                for(Entry<K, V> entry: oldChains[i]) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * Constructs a new ChainedHashMap with default resizing load factor threshold,
     * default initial chain count, and default initial chain capacity.
     */
    public ChainedHashMap() {
        this(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD, DEFAULT_INITIAL_CHAIN_COUNT, DEFAULT_INITIAL_CHAIN_CAPACITY);
    }

    /**
     * Constructs a new ChainedHashMap with the given parameters.
     *
     * @param resizingLoadFactorThreshold the load factor threshold for resizing. When the load factor
     *                                    exceeds this value, the hash table resizes. Must be > 0.
     * @param initialChainCount the initial number of chains for your hash table. Must be > 0.
     * @param chainInitialCapacity the initial capacity of each ArrayMap chain created by the map.
     *                             Must be > 0.
     */
    public ChainedHashMap(double resizingLoadFactorThreshold, int initialChainCount, int chainInitialCapacity) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        this.resizingLoadFactorThreshold = resizingLoadFactorThreshold;
        initialChainCapacity = chainInitialCapacity;
        chains = createArrayOfChains(initialChainCount);
        size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code AbstractIterableMap<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     * @see ArrayMap createArrayOfEntries method for more background on why we need this method
     */
    @SuppressWarnings("unchecked")
    private AbstractIterableMap<K, V>[] createArrayOfChains(int arraySize) {
        return (AbstractIterableMap<K, V>[]) new AbstractIterableMap[arraySize];
    }

    /**
     * Returns a new chain.
     *
     * This method will be overridden by the grader so that your ChainedHashMap implementation
     * is graded using our solution ArrayMaps.
     *
     * Note: You do not need to modify this method.
     */
    protected AbstractIterableMap<K, V> createChain(int initialSize) {
        return new ArrayMap<>(initialSize);
    }

    @Override
    public V get(Object key) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        int index = hashCode2Index(key);
        if (null == chains[index]) {
            return null;
        }

        return chains[index].get(key);
    }

    @Override
    public V put(K key, V value) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        int index = hashCode2Index(key);
        if (null == chains[index]) {
            chains[index] = new ArrayMap<K, V>(initialChainCapacity);
        }

        int oldChainSize = chains[index].size();
        V oldValue = chains[index].put(key, value);
        int chainSize = chains[index].size();
        if (oldChainSize + 1 == chainSize) {
            size++;

            if (resizingLoadFactorThreshold * chains.length < size) {
                resize();
            }
        }

        return oldValue;
    }

    @Override
    public V remove(Object key) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        int index = hashCode2Index(key);
        if (null == chains[index]) {
            return null;
        }

        int oldChainSize = chains[index].size();
        V value = chains[index].remove(key);
        int chainSize = chains[index].size();
        if (oldChainSize == chainSize + 1) {
            size--;
        }

        return value;
    }

    @Override
    public void clear() {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        for (int i = 0; i < chains.length; i++) {
            chains[i] = null;
        }

        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        int index = hashCode2Index(key);
        if (null == chains[index]) {
            return false;
        }

        return chains[index].containsKey(key);
    }

    @Override
    public int size() {
        // TODO: replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: you won't need to change this method (unless you add more constructor parameters)
        return new ChainedHashMapIterator<>(this.chains);
    }

    // TODO: after you implement the iterator, remove this toString implementation
    // Doing so will give you a better string representation for assertion errors the debugger.
    /*
    @Override
    public String toString() {
        return super.toString();
    }
     */

    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedHashMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private AbstractIterableMap<K, V>[] chains;
        // You may add more fields and constructor parameters
        private int current;
        private Iterator<Map.Entry<K, V>> currentChainIterator;

        public ChainedHashMapIterator(AbstractIterableMap<K, V>[] chains) {
            this.chains = chains;
            current = 0;
        }

        @Override
        public boolean hasNext() {
            // TODO: replace this with your code
            // throw new UnsupportedOperationException("Not implemented yet.");

            if (null != currentChainIterator) {
                if (currentChainIterator.hasNext()) {
                    return true;
                }

                current++;
            }

            while (chains.length > current && (null == chains[current] || 0 == chains[current].size())) {
                current++;
            }

            if (chains.length == current) {
                currentChainIterator = null;
                return false;
            }

            currentChainIterator = chains[current].entrySet().iterator();
            return currentChainIterator.hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
            // TODO: replace this with your code
            // throw new UnsupportedOperationException("Not implemented yet.");

            if (hasNext()) {
                return currentChainIterator.next();
            }

            throw new NoSuchElementException();
        }
    }
}

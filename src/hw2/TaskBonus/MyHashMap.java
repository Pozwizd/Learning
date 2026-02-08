package hw2.TaskBonus;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int hash = key.hashCode();
        int index = hash % table.length;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
    }

    @Override
    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        int hash = key.hashCode();
        int index = hash % table.length;
        Entry<K, V> prev = null;
        for (Entry<K, V> entry = table[index]; entry != null; prev = entry, entry = entry.next) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int hash = key.hashCode();
        int index = hash % table.length;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public K[] keyArray() {
        K[] keys = (K[]) new Object[size];
        int i = 0;
        for (Entry<K, V> entry : table) {
            for (; entry != null; entry = entry.next) {
                keys[i++] = entry.key;
            }
        }
        return keys;
    }

    @Override
    public V[] valueArray() {
        V[] values = (V[]) new Object[size];
        int i = 0;
        for (Entry<K, V> entry : table) {
            for (; entry != null; entry = entry.next) {
                values[i++] = entry.value;
            }
        }
        return values;
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int index = entry.key.hashCode() % table.length;
                entry.next = table[index];
                table[index] = entry;
                entry = next;
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
package edu.luc.cs271.myhashmap;

import java.util.*;
import java.util.Map;


/**
 * A generic HashMap custom implementation using chaining. Concretely, the table is an ArrayList of
 * chains represented as LinkedLists.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> implements Map<K, V> {

  private static final int DEFAULT_TABLE_SIZE = 11; // a prime

  private List<List<Entry<K, V>>> table;

  public MyHashMap() {

    this(DEFAULT_TABLE_SIZE);
  }

  public MyHashMap(final int tableSize) {
    // allocate a table of the given size
    table = new ArrayList<>(tableSize);
    // then create an empty chain at each position
    for (int i = 0; i < tableSize; i += 1) {
      table.add(new LinkedList<>());
    }
  }

  @Override
  public int size() {
    int result = 0;
    for (List<Entry<K,V>> list: table) {
      result += list.size();
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean containsKey(final Object key) {
    if (key != null) {
      final int index = calculateIndex(key);
      List<Entry<K,V>> list = table.get(index);
      for (Entry<K,V> entry: list) {
        if (key.equals(entry.getKey())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean containsValue(final Object value) {
    if (value != null) {
      for (List<Entry<K,V>> list: table) {
        for (Entry<K,V> entry: list) {
          if (value.equals(entry.getValue())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public V get(final Object key) {
    if (key != null) {
      for (List<Entry<K,V>> list: table) {
        for (Entry<K,V> entry: list) {
          if (key.equals(entry.getKey())) {
            return entry.getValue();
          }
        }
      }
    }
    return null;
  }

  @Override
  public V put(final K key, final V value) {
    final int index = calculateIndex(key);
    List<Entry<K,V>> list = table.get(index);
    V currentValue = null;
    for (Entry<K,V> entry: list) {
      if (key.equals(entry.getKey())) {
        currentValue = entry.getValue();
        entry.setValue(value);
        return currentValue;
      }
    }
    Map.Entry<K,V> newEntry = new AbstractMap.SimpleEntry<K, V>(key, value);
    list.add(newEntry);
    return null;
  }

  @Override
  public V remove(final Object key) {

    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();

    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        iter.remove();
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public void putAll(final Map<? extends K, ? extends V> m) {
    for (Iterator iterator = m.entrySet().iterator(); iterator.hasNext();) {
      Entry entry = (java.util.Map.Entry) iterator.next();
      put((K) entry.getKey(), (V) entry.getValue());
    }
  }

  @Override
  public void clear() {
    for (List<Entry<K,V>> list: table) {
      list.clear();
    }
  }

  /** The resulting keySet is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Set<K> keySet() {
    final Set<K> result = new HashSet<>();
    for (List<Entry<K,V>> list: table) {
      for (Entry<K,V> entry: list) {
        result.add(entry.getKey());
      }
    }
    return Collections.unmodifiableSet(result);
  }

  /** The resulting values collection is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Collection<V> values() {
    final List<V> result = new LinkedList<>();
    for (List<Entry<K,V>> list: table) {
      for (Entry<K,V> entry: list) {
        result.add(entry.getValue());
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /** The resulting entrySet is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Set<Entry<K, V>> entrySet() {
    final Set<Entry<K, V>> result = new HashSet<>();
    for (List<Entry<K,V>> list: table) {
      for (Entry<K,V> entry: list) {
        result.add(entry);
      }
    }
    return Collections.unmodifiableSet(result);
  }

  @Override
  public String toString() {
    return table.toString();
  }

  public boolean equals(final Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof Map)) {
      return false;
    } else {
      return this.entrySet().equals(((Map)that).entrySet());
    }
  }

  private int calculateIndex(final Object key) {
    return Math.floorMod(key.hashCode(), table.size());
  }
}

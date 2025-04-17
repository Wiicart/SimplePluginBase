package com.pedestriamc.common.util;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Non thread-safe BidiMap implementation that allows keys that share values.
 * Implemented with two Maps, which are not necessarily the same size, due to the Map being non-strict.
 * When using inverted methods, the most recent key added will be used.
 * @param <K> Key type
 * @param <V> Value type
 */
public class UnrestrictedBidiMap<K, V> implements BidiMap<K, V> {

    private final Map<K, V> regular;
    private final Map<V, K> inverted;

    public UnrestrictedBidiMap(){
        regular = new HashMap<>();
        inverted = new HashMap<>();
    }


    /**
     * As internal Maps can have different sizes, this returns the size of the normal map.
     * @return int of the size
     */
    @Override
    public int size() {
        return regular.size();
    }

    @Override
    public boolean isEmpty() {
        return regular.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return regular.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return regular.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return regular.get(key);
    }

    @Override
    public V put(K key, V value) {
        V val = regular.get(key);
        regular.put(key, value);
        inverted.put(value, key);
        return val;
    }

    @Override
    public V remove(Object key) {
        V val = regular.get(key);
        inverted.remove(val);
        return regular.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        regular.putAll(m);
        for(K k : m.keySet()){
            V val = m.get(k);
            inverted.put(val, k);
        }
    }

    @Override
    public void clear() {
        regular.clear();
        inverted.clear();
    }

    @Override
    public @NotNull Set<K> keySet() {
        return regular.keySet();
    }

    @Override
    public K getKey(Object value) {
        return inverted.get(value);
    }

    @Override
    public K removeValue(Object value) {
        K key = inverted.get(value);
        if(key != null && regular.containsKey(key)){
            regular.remove(key);
        }
        return inverted.remove(value);
    }

    /**
     * This method is lossy, due to the unrestricted nature of this map.
     * If any keys share the same value, the newest Key will be used on this map.
     * Older duplicate keys that cannot be transferred over are thrown out.
     * @return a new NonStrictBidiMap
     */
    @Override
    public BidiMap<V, K> inverseBidiMap() {
        BidiMap<V, K> map = new UnrestrictedBidiMap<>();
        map.putAll(inverted);
        return map;
    }

    @Override
    public @NotNull Set<V> values() {
        return new HashSet<>(regular.values());
    }

    @Override
    public @NotNull Set<Map.Entry<K, V>> entrySet() {
        return regular.entrySet();
    }

    /**
     * Not implemented
     * @return Will not return
     * @throws UnsupportedOperationException Unsupported
     */
    @Override
    @Deprecated
    public MapIterator<K, V> mapIterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("UnrestrictedBidiMap does not implement BidMap#mapIterator().");
    }

    @Override
    public String toString(){
        return regular.toString();
    }

    @Override
    public int hashCode(){
        return regular.hashCode();
    }



}

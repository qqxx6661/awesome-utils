package cn.monitor4all.commonutils.algorithm.lru;

import java.util.HashMap;

/**
 * 使用HashMap实现LRU缓存
 * @param <K>
 * @param <V>
 */
public class LRUWithHashMap<K, V> {

    private final int MAX_CACHE_SIZE;
    private Entry<K,V> first;
    private Entry<K,V> last;
    private HashMap<K, Entry<K, V>> hashMap;

    public LRUWithHashMap(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new HashMap<>();
    }

    public void put(K key, V value) {
        // 获取Entry
        Entry<K,V> entry = getEntry(key);
        // 若为空则判断是否超过容量，超过则删除链尾Entry
        if (entry == null) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                hashMap.remove(last.key);
                removeLast();
            }
            entry = new Entry<>();
            entry.key = key;
        }
        // 新Entry准备好后，将Entry原来两边拼接（若存在的话），然后将Entry头插在链头
        entry.value = value;
        moveToFirst(entry);
        hashMap.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        moveToFirst(entry);
        return entry.value;
    }

    private void moveToFirst(Entry<K,V> entry) {
        if (entry == first) {
            return;
        }
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        if (entry == last) {
            last = last.pre;
        }

        if (first == null || last == null) {
            first = last = entry;
            return;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }

    private Entry<K, V> getEntry(K key) {
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry<K,V> entry = first;
        while (entry != null) {
            sb.append(String.format("%s:%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return sb.toString();
    }

    static class Entry<K, V> {
        public Entry<K,V> pre;
        public Entry<K,V> next;
        public K key;
        public V value;
    }

    public static void main(String[] args) {
        LRUWithHashMap<String, Integer> lruWithHashMap = new LRUWithHashMap<>(3);
        lruWithHashMap.put("a", 1);
        lruWithHashMap.put("b", 2);
        lruWithHashMap.put("c", 3);
        lruWithHashMap.put("d", 4);
        System.out.println(lruWithHashMap.get("a"));
        System.out.println(lruWithHashMap.get("b"));
    }
}

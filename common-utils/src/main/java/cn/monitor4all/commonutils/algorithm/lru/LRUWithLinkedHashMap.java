package cn.monitor4all.commonutils.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUWithLinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    private int cacheSize;

    public LRUWithLinkedHashMap(int cacheSize) {
        super(16,0.75f,true);
        this.cacheSize = cacheSize;
    }

    /**
     * 判断元素个数是否超过缓存容量
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    public static void main(String[] args) {
        Map<String, Integer> lru = new LRUWithLinkedHashMap<>(3);
        lru.put("a", 1);
        lru.put("b", 2);
        lru.put("c", 3);
        lru.put("d", 4);
        System.out.println(lru.get("a"));
    }
}

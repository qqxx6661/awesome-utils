package com.rude3knife.commonutils.utils;

import java.util.*;

/**
 * Map相关工具类
 * @author yangzhendong
 */
public class MapUtil {

    /**
     * 将Map根据Key进行排序
     * 内部使用LinkedHashMap
     * 注意：若Map中有null会抛出空指针异常
     * @param map 需要排序的Map
     * @return 排序后的Map
     */
    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);
        Map<K,V> sortedMap = new LinkedHashMap<>();
        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }
        return sortedMap;
    }

    /**
     * 将Map根据Value进行排序
     * 内部使用LinkedHashMap
     * 注意：若Map中有null会抛出空指针异常
     * @param map 需要排序的Map
     * @return 排序后的Map
     */
    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<>(map.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getValue));
        Map<K,V> sortedMap = new LinkedHashMap<>();
        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

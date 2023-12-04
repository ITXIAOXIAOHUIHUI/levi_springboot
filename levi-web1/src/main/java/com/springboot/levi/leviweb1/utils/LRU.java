package com.springboot.levi.leviweb1.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的页面置换算法，选择最近最久未使用的数据予以淘汰。
 * @program: levi_springboot
 * @description:
 * 必须要有顺序之分，区分最近使用的和很久没有使用的数据排序。
 * 写和读操作一次搞定。 如果容量满了要删除最不常用的数据，每次新访问还要把新的数据插入到队头(按照业务你自己设定左右那一边是队头)
 * 由于LinkedHashMap可以记录下Map中元素的访问顺序，所以可以轻易的实现LRU算法。只需要将构造方法的accessOrder传入true，并且重写removeEldestEntry方法即可。accessOrder传入true可以实现LRU缓存算法（访问顺序）
 * 作者：GoodLiang
 * 链接：https://juejin.cn/post/7003143615950094344
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @author: jhh
 * @create: 2022-08-25 17:31
 */
public class LRU<K,V> extends LinkedHashMap<K,V> {
    private int capacity;

    public LRU(int capacity){
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected  boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
        return super.size()>capacity;
    }
    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put(1,"a");
        lru.put(2,"b");
        lru.put(3,"c");
        System.out.println(lru.keySet());
        lru.put(4,"d");
        System.out.println(lru.keySet());
        lru.put(3,"c");
        System.out.println(lru.keySet());
        lru.put(3,"c");
        System.out.println(lru.keySet());
        lru.put(3,"c");
        System.out.println(lru.keySet());
        lru.put(5,"e");
        System.out.println(lru.keySet());

    }

}

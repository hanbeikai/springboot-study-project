package com.beikai.hashmap_demo.test;

/**
 * @ClassName MyHashMap
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/23 14:40
 * @Version 1.0
 *  自定义 hashmap
 **/
public class MyHashMap<K,V>{

    /**
     * 链表中的对象
      */
    private Entry[] table;
    // 初始化的数组长度
    private static final Integer CAPACITRY = 8;
    // 设置长度
    private int size = 0;

    public MyHashMap() {
        this.table = new Entry[CAPACITRY];
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {
        // 获取key的hash值
        int hash = key.hashCode();
        // 获取key对应的下标
        int i = hash % CAPACITRY;
        // 找到对应key的值 如果找到,返回找到的value值 如果没有找到 返回为空
        for (Entry<K,V> entry = table[i];entry != null; entry = entry.next) {
            if (entry.k.equals(key)){
                return entry.v;
            }
        }

        return null;
    }

    public Object put(K key, V value) {
        // 获取key的hash值
        int hash = key.hashCode();
        // 获取对应的key的数组下标
        int i = hash % CAPACITRY;

        // 遍历数组 存储值
        for (Entry<K,V> entry = table[i]; entry != null; entry = entry.next) {
            if (entry.k.equals(key)){
                // 如果key相同 value不同 进行覆盖操作 返回被覆盖的值
                V oldvalue = entry.v;
                entry.v = value;
                return oldvalue;
            }
        }

        addEntry(key, value, i);

        return null;
    }

    /**
     * 把链表对象 添加到对应的数组中
     * @param key
     * @param value
     * @param i
     */
    private void addEntry(K key, V value, int i) {
        Entry entry = new Entry(key,value,table[i]);
        table[i] = entry;
        size++;
    }

    /**
     * 创建链表对象
     * @param <K>
     * @param <V>
     */
    class Entry<K,V>{
        // key
        private K k;
        // value
        private V v;
        // 下一个
        private Entry next;

        public Entry(K k, V v, Entry next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String,String> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {

            myHashMap.put("张"+i,"三"+i);
        }

        System.out.println(String.format("%s的值是 : %s","张",myHashMap.get("张")));


    }

}


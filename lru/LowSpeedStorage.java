package top.fomeiherz.potato.lru;

import java.util.HashMap;
import java.util.Map;

public class LowSpeedStorage<K, V> implements Storage<K, V> {

    private Map<K, V> storage = new HashMap<>();

    public void set(K key, V value) {
        storage.put(key, value);
    }

    @Override
    public V get(K key) {
        return storage.get(key);
    }
}

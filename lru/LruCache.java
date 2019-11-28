package top.fomeiherz.potato.lru;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LruCache extends AbstractLruCache<String, String> {

    // 高速缓存
    private Map<String, String> fastSpeedStorage;

    private Deque<String> queue;

    public LruCache(int capacity, Storage<String, String> lowSpeedStorage) {
        super(capacity, lowSpeedStorage);
        // 定义定长缓存
        fastSpeedStorage = new HashMap<>(capacity);
        queue = new LinkedList<>();
    }

    @Override
    public String get(String key) {
        String val;
        if (fastSpeedStorage.containsKey(key)) {
            // 高速缓存存在
            val = fastSpeedStorage.get(key);
            // 命中
            // 移动元素到尾部
            if (!queue.peekLast().equals(key)) {
                queue.remove(key);
                queue.offer(key);
            }
        } else {
            // 高速缓存不存在
            // 从低速缓存中取
            val = lowSpeedStorage.get(key);
            // 保存到高速缓存中
            // 缓存已满，需要置换
            if (fastSpeedStorage.size() == capacity) {
                // 头部出队
                fastSpeedStorage.remove(queue.pop());
            }
            // 缓存未满，直接放进去
            fastSpeedStorage.put(key, val);
            // 尾部入队
            queue.offer(key);
        }
        return val;
    }

    public static void main(String[] args) {
        LowSpeedStorage<String, String> lowSpeedStorage = new LowSpeedStorage();
        lowSpeedStorage.set("华农", "广东");
        lowSpeedStorage.set("华师", "广东");
        lowSpeedStorage.set("中大", "广东");
        lowSpeedStorage.set("广工", "广东");
        lowSpeedStorage.set("深大", "广东");
        lowSpeedStorage.set("武大", "湖北");
        lowSpeedStorage.set("北大", "北京");
        lowSpeedStorage.set("清华", "北京");
        lowSpeedStorage.set("人大", "北京");
        lowSpeedStorage.set("邮电大", "北京");
        lowSpeedStorage.set("外语学院", "北京");
        lowSpeedStorage.set("浙江大学", "浙江");
        lowSpeedStorage.set("复旦大学", "上海");
        lowSpeedStorage.set("杭州师范大学", "浙江");
        lowSpeedStorage.set("地质大", "江西");

        LruCache lruCache = new LruCache(10, lowSpeedStorage);
        System.out.println(lruCache.get("邮电大"));
        System.out.println(lruCache.get("邮电大"));
        System.out.println(lruCache.get("复旦大学"));
        System.out.println(lruCache.get("邮电大"));
        System.out.println(lruCache.get("地质大"));
        System.out.println(lruCache.get("中大"));
        System.out.println(lruCache.get("清华"));
        System.out.println(lruCache.get("武大"));
        System.out.println(lruCache.get("地质大"));
        System.out.println(lruCache.get("深大"));
        System.out.println(lruCache.get("华师"));
        System.out.println(lruCache.get("人大"));
        System.out.println(lruCache.get("武大"));
        System.out.println(lruCache.get("杭州师范大学"));
        System.out.println(lruCache.get("广工"));

        System.out.println(lruCache.fastSpeedStorage);
        System.out.println(lruCache.queue);
    }
}

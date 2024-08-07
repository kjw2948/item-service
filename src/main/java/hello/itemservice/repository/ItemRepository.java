package hello.itemservice.repository;

import hello.itemservice.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {
    private static Map<Long, Item> items = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        if (items.containsKey(item.getId())){
            throw new IllegalStateException("이미 있는 제품 입니다");
        }
        item.setId(++sequence);
        items.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        Optional<Item> findItem = Optional.ofNullable(items.get(id));
        if (findItem.isEmpty()) {
            throw new IllegalStateException("해당 제품은 존재하지 않습니다");
        }
        return findItem.get();
    }


    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }

    public void update(Long id, Item item) {
        items.put(id, item);
    }

    public void clearStore() {
        items.clear();
    }
}

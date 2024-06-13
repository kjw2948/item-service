package hello.review.repository;

import hello.review.domain.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@org.springframework.stereotype.Repository
public class ItemRepository implements Repository{
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    @Override
    public Item findById(Long id) {
        return store.get(id);
    }
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long editId, Item item) {
        Item findItem = findById(editId);
        findItem.setName(item.getName());
        findItem.setQuantity(item.getQuantity());
        findItem.setPrice(item.getPrice());
    }

    @Override
    public Item delete(Long editId) {
        if (store.containsKey(editId)) {
            return store.remove(editId);
        }
        return null;
    }
}

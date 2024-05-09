package hello.itemservice2.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // 컴포넌트 스캔의 대상이 됨
public class ItemRepository {


    //객체 생성하면 따로 값이 생성되는 것을 방지하려고 static으로 생성
    //동시성 이슈가 생길 수 있어서 concurrentMap , Long은 아토믹 머시기 쓰는게 맞음
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item upgradeParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(upgradeParam.getItemName());
        findItem.setPrice(upgradeParam.getPrice());
        findItem.setQuantity(upgradeParam.getQuantity());
    }
}

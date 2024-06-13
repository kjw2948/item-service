package hello.review.repository;

import hello.review.domain.Item;

import java.util.List;

public interface Repository {
    public Item save(Item item);
    public Item findById(Long id);
    public List<Item> findAll();
    public void update(Long id, Item item);

    public Item delete(Long id);
}

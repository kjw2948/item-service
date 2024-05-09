package hello.itemservice2.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private String itemName;
    private Long id;
    private Integer price;
    private int quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

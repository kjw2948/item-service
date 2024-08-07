package hello.itemservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String itemName;
    private int price;
    private int quantity;

    public ItemDto() {
    }

    public ItemDto(String itemName, int price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public static ItemDto createItemDto(Item item) {
        return new ItemDto(item.getItemName(), item.getPrice(), item.getQuantity());
    }
}

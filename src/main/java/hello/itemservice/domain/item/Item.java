package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
//DTO : 데이터가 왔다갔다 하는것
//@Data는 주의해서 사용할 필요가 있음 -> 실무에선 비추
public class Item {
    private Long id;
    private String itemName;
    private Integer price; // price가 안들어갈 때도 있다고 가정 (Null도 가능하게 하려구)
    private Integer quantity; //

    public Item() {

    }
    public Item(Long id, String itemName, Integer price , Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String itemName, Integer price , Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}

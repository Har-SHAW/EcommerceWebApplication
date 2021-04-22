package com.project.ecommerce.dto.item;

import com.project.ecommerce.entity.item.ItemEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Item implements Serializable {
    private Long itemId;
    @NotNull(message = "is required")
    private String itemName;
    @NotNull(message = "is required")
    private Double itemPrice;
    private Boolean isOutOfStock;

    public Item(ItemEntity itemEntity){
        this.setItemId(itemEntity.getItemId());
        this.setItemName(itemEntity.getItemName());
        this.setItemPrice(itemEntity.getItemPrice());
        this.setIsOutOfStock(itemEntity.getIsOutOfStock());
    }
}

package com.project.ecommerce.dto.item;

import com.project.ecommerce.entity.item.ItemEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Item {
    @NonNull private Long itemId;
    @NonNull private String itemName;
    @NonNull private Double itemPrice;

    public Item(ItemEntity itemEntity){
        this.setItemId(itemEntity.getItemId());
        this.setItemName(itemEntity.getItemName());
        this.setItemPrice(itemEntity.getItemPrice());
    }
}

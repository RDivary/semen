package com.divary.semen.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "order_detail")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {

    @Id
    private Integer id;
    private Integer quantity;
    private Integer price;
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

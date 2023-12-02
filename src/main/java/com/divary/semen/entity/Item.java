package com.divary.semen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Table(name = "item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    private Integer id;
    private String name;
    private Integer price;

    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetails;
}

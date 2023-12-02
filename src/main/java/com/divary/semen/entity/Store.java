package com.divary.semen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Table(name = "store")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {

    @Id
    private Integer id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;
}

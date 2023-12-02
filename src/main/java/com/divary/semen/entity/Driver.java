package com.divary.semen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Table(name = "driver")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

    @Id
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "driver")
    private List<Order> orders;

}

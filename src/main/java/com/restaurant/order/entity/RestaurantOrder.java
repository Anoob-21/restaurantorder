package com.restaurant.order.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_restaurant_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//customer_id - this is how it looks by default
public class RestaurantOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "order_item")
    private String orderitem;

}

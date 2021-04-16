package com.restaurant.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantOrderDto {

    private String customerId;
    private String orderitem;
    private String message;
    private Boolean flag = Boolean.TRUE;
}
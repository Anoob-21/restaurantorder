package com.restaurant.order;

import com.restaurant.order.dto.RestaurantOrderDto;
import com.restaurant.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantorder")
@Slf4j
@RefreshScope
public class OrderController {

    @Autowired
    OrderService orderService;
    //Move this to service.
    //COntroller
    @Value("${message:Hello default}")
    private String message;

    //Change from get order to order.

    @GetMapping("/getOrder")
    public ResponseEntity<RestaurantOrderDto> getOrder
            (@RequestParam(value="consumerId") String consumerId) {

        log.info("Received message is : {}",message);
        log.info("Calling order service for consumer id : {}",consumerId);


        RestaurantOrderDto restaurantOrderDto = orderService.getOrder(consumerId);
        log.info("received response :");

        ResponseEntity<RestaurantOrderDto> responseEntity = new ResponseEntity<>
                (restaurantOrderDto, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getMessage")
    public String getDisplayMessage(){
        return message;
    }


    @PostMapping("/createOrder")
    public ResponseEntity<RestaurantOrderDto> createRestaurantOrder
            (@RequestBody RestaurantOrderDto restaurantOrderDto){

        log.info("New order request received");
        RestaurantOrderDto restaurantSavedOrderDto =
                orderService.createOrder(restaurantOrderDto);
        log.info("New order created");
        //Move this to service.controller should just delegate.
        ResponseEntity<RestaurantOrderDto> responseEntity;
        if(restaurantSavedOrderDto.getFlag()){
            responseEntity = new ResponseEntity<>
                    (restaurantOrderDto, HttpStatus.OK);
        }else{

            responseEntity = new ResponseEntity<>
                    (restaurantOrderDto, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }


}


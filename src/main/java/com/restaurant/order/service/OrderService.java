package com.restaurant.order.service;

import com.restaurant.order.dto.RestaurantOrderDto;
import com.restaurant.order.entity.RestaurantOrder;
import com.restaurant.order.repository.RestaurantOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    RestaurantOrderRepository restaurantOrderRepository;

    public RestaurantOrderDto getOrder(String consumerId){

        RestaurantOrderDto restaurantOrderDto = new RestaurantOrderDto();
        Optional<RestaurantOrder> restaurantOrderOptional = restaurantOrderRepository
                .findByCustomerId(consumerId);
        if(restaurantOrderOptional.isPresent()){
            BeanUtils.copyProperties(restaurantOrderOptional.get(),restaurantOrderDto);
            restaurantOrderDto.setMessage("Success find the order");
        }else{
            restaurantOrderDto.setMessage("No order find for the consumerID ");
        }
        return restaurantOrderDto;
    }
        //Loop through,for each order ,create DTO,add DTO to list.
    public List<RestaurantOrderDto> getAllOrders(){
        List<RestaurantOrderDto> orders = new ArrayList<>();

        List<RestaurantOrder> orderretrieve = restaurantOrderRepository.findAll();
        return  orders;

    }
    public RestaurantOrderDto updateOrder(RestaurantOrderDto restaurantOrderReceived) {

        RestaurantOrder restaurantOrder = new RestaurantOrder();
        BeanUtils.copyProperties(restaurantOrderReceived, restaurantOrder);

        RestaurantOrderDto restaurantOrderDtoPostSave = new RestaurantOrderDto();
        try {
            restaurantOrderRepository.save(restaurantOrder);
            BeanUtils.copyProperties(restaurantOrder, restaurantOrderDtoPostSave);
            restaurantOrderDtoPostSave.setMessage("Order updated successfully");
        } catch (Exception ex) {
            log.error("****  Unable to save the order : {}", ex.getMessage());
            restaurantOrderDtoPostSave.setMessage(ex.getMessage());
            restaurantOrderDtoPostSave.setFlag(Boolean.FALSE);
        }

        return restaurantOrderDtoPostSave;
    }
    public RestaurantOrderDto deleteOrder(long consumerId) {

        RestaurantOrder restaurantOrder = new RestaurantOrder();
      //  BeanUtils.copyProperties(restaurantOrderReceived, restaurantOrder);

        RestaurantOrderDto restaurantOrderDtoPostSave = new RestaurantOrderDto();
        try {
            //restaurantOrderRepository.save(restaurantOrder);

            //restaurantOrderRepository.deleteById(consumerId);
            //restaurantOrderRepository.findByCustomerId(consumerId)
            //restaurantOrderRepository.delete();
          //  BeanUtils.copyProperties(restaurantOrder, restaurantOrderDtoPostSave);
            restaurantOrderDtoPostSave.setMessage("Order deleted successfully");
        } catch (Exception ex) {
            log.error("****  Unable to save the order : {}", ex.getMessage());
            restaurantOrderDtoPostSave.setMessage(ex.getMessage());
            restaurantOrderDtoPostSave.setFlag(Boolean.FALSE);
        }

        return restaurantOrderDtoPostSave;
    }


    public RestaurantOrderDto createOrder(RestaurantOrderDto restaurantOrderReceived) {

        RestaurantOrder restaurantOrder = new RestaurantOrder();
        BeanUtils.copyProperties(restaurantOrderReceived, restaurantOrder);

        RestaurantOrderDto restaurantOrderDtoPostSave = new RestaurantOrderDto();
        try {
            restaurantOrderRepository.save(restaurantOrder);
            BeanUtils.copyProperties(restaurantOrder, restaurantOrderDtoPostSave);
            restaurantOrderDtoPostSave.setMessage("Order saved successfully");
        } catch (Exception ex) {
            log.error("****  Unable to save the order : {}", ex.getMessage());
            restaurantOrderDtoPostSave.setMessage(ex.getMessage());
            restaurantOrderDtoPostSave.setFlag(Boolean.FALSE);
        }

        return restaurantOrderDtoPostSave;
    }
}


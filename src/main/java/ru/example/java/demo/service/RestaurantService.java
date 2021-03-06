package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.repository.*;
import ru.example.java.demo.service.exception.MyException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuService menuService;





    @Transactional
    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant update(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void delete(Long restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }

    public Collection<Restaurant> findAll(){
       return restaurantRepository.findAll();
    }

    public Restaurant findById(Long restaurantId){
       return restaurantRepository.findById(restaurantId)
               .orElseThrow(()->new MyException("Not found restaurant "+restaurantId, HttpStatus.BAD_REQUEST));
    }
    public Menu findMenuByRestaurantIdAndDate (Long restaurantId, LocalDate date){
       return menuService.findByRestaurantIdAndDate(restaurantId,date);
    }











}

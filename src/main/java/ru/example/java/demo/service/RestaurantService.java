package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.dto.VoteRequest;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.model.Vote;
import ru.example.java.demo.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;



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
               .orElseThrow();
    }









}

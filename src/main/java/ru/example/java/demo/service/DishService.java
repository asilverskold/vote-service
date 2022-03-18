package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.repository.DishRepository;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    @Transactional
    public Dish createDish(Dish dish){
        return dishRepository.save(dish);
    }

    @Transactional
    public Dish updateDish(Dish dish){
        return dishRepository.save(dish);
    }

    @Transactional
    public void deleteDish(Long dishId){
        dishRepository.deleteById(dishId);
    }






}

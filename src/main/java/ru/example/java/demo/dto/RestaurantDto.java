package ru.example.java.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class RestaurantDto implements Serializable {
    private final Long id;
    private final String name;
    private final MenuDto memu;
}

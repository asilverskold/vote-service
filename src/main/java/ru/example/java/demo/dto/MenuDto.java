package ru.example.java.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MenuDto implements Serializable {
    private final Long id;
    private final LocalDate date;
    private final List<DishDto> dishs;


}
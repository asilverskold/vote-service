package ru.example.java.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class DishDto implements Serializable {
    private final Long id;

    private final String name;
    private final Long price;
}

package ru.example.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {
    @NotNull
    private Long restaurantId;

    private LocalDate date;

    @NotNull
    private Long userId;
}

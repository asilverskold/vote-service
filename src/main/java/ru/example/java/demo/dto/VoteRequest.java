package ru.example.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {
    @NotNull
    private Long pollid;
    @NotNull
    private Long optionId;
    @NotNull
    private Long userId;
}

package ru.example.java.demo.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.controller.convertor.RestaurantModelAssembler;
import ru.example.java.demo.AuthenticatedUser;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.service.VoteService;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class VoteController {
    private final VoteService voteService;
    private final RestaurantModelAssembler assembler;

    @Secured({"ROLE_USER"})
    @GetMapping("result")
    public EntityModel<Restaurant> getResult() {
        return assembler.toModel(voteService.getResult(LocalDate.now()));
    }

    @Secured({"ROLE_USER"})
    @PostMapping("vote/")
    public void vote(@RequestParam Long restaurantId,
                     @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        System.out.println(authenticatedUser);
        voteService.vote(restaurantId,authenticatedUser.getUser().getId());
    }


}

package ru.example.java.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", unique = true)
    private LocalDate date;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "dish")
    private Set<Dish> dishs;





}
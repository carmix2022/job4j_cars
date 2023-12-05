package ru.job4j.cars.model;

import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.*;
import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "owners")
public class Owner {
    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
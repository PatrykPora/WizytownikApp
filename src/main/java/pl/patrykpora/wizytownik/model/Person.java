package pl.patrykpora.wizytownik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Person {

    Long id;
    UUID personUuid;
    String name;
    String surName;
    String email;
    String telephone;
    Address address;
}

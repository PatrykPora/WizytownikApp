package pl.patrykpora.wizytownik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public final class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    UUID personUuid;
    String name;
    String surName;
    String email;
    String telephone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;
}

package pl.patrykpora.wizytownik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public final class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    UUID addressUuid;
    String city;
    String street;
    Integer streetNumber;
    Integer homeNumber;

}

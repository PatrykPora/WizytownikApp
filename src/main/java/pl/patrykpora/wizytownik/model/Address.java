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
public final class Address {

    Long id;
    UUID addressUuid;
    String city;
    String street;
    Integer streetNumber;
    Integer homeNumber;

}

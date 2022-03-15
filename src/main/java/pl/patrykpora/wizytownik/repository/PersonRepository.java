package pl.patrykpora.wizytownik.repository;

import org.springframework.stereotype.Repository;
import pl.patrykpora.wizytownik.model.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    private Map<UUID, Person> persons = new ConcurrentHashMap<>();


    public Person getPerson(final UUID personUuid) {
        return persons.get(personUuid);

    }


    public void save(Person person) {
        final UUID randomUuid = UUID.randomUUID();
        person.setPersonUuid(randomUuid);
        persons.put(randomUuid, person);
    }


    public List<Person> findAll() {
        return persons.values().stream().collect(Collectors.toList());
    }


    public Optional<Person> findByEmail(final String email) {
        return persons.values().stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(email))
                .findAny();
    }
}

package pl.patrykpora.wizytownik.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.patrykpora.wizytownik.exception.PerosnNotFoundException;
import pl.patrykpora.wizytownik.exception.PersonAlreadyExistException;
import pl.patrykpora.wizytownik.model.Person;
import pl.patrykpora.wizytownik.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person getPerson(final UUID personUuid) {
        return personRepository.getPerson(personUuid);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        if (personRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new PersonAlreadyExistException();
        }
        personRepository.save(person);
    }

    public void editPerson(final UUID personUuid, final Person person) {
        if (!personRepository.findByUuid(personUuid).isPresent()){
            throw new PerosnNotFoundException();
        };
        person.setPersonUuid(personUuid);
        personRepository.save(person);
    }
}


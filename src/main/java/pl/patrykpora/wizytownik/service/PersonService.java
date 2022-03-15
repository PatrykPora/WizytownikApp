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
        return personRepository.findByPersonUuid(personUuid).orElseThrow(PerosnNotFoundException::new);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        if (personRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new PersonAlreadyExistException();
        }
        person.setPersonUuid(UUID.randomUUID());
        person.getAddress().setAddressUuid(UUID.randomUUID());
        personRepository.save(person);
    }

    public void editPerson(final UUID personUuid, final Person person) {
        final Person personDB = getPerson(personUuid);

        personDB.setName(person.getName());
        personDB.setSurName(person.getSurName());
        personDB.setEmail(person.getEmail());
        personDB.setTelephone(person.getTelephone());
        personDB.getAddress().setCity(person.getAddress().getCity());
        personDB.getAddress().setStreet(person.getAddress().getStreet());
        personDB.getAddress().setStreetNumber(person.getAddress().getStreetNumber());
        personDB.getAddress().setHomeNumber(person.getAddress().getHomeNumber());
        personRepository.save(personDB);
    }

    private void checkIfPersonExists(UUID personUuid) {
        if (!personRepository.findByPersonUuid(personUuid).isPresent()){
            throw new PerosnNotFoundException();
        }
    }

    public void delete(final UUID personUuid) {
        checkIfPersonExists(personUuid);
        final Person person = getPerson(personUuid);
        personRepository.delete(person);
    }
}


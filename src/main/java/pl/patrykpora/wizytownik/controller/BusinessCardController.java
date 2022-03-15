package pl.patrykpora.wizytownik.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.patrykpora.wizytownik.model.Address;
import pl.patrykpora.wizytownik.model.Person;
import pl.patrykpora.wizytownik.service.PersonService;

import java.util.UUID;

@Controller
@RequestMapping("person")
@AllArgsConstructor
public class BusinessCardController {

    private final PersonService personService;



    @GetMapping("/list")
    public String getPersons(Model model) {
        model.addAttribute("persons", personService.getPersons());
        return "list";
    }

    @GetMapping("details/{personUuid}")
    public String getPerson(@PathVariable UUID personUuid, Model model){
        model.addAttribute( "person", personService.getPerson(personUuid));
        return "details";
    }

    @GetMapping("/add")
    public String addPerson(Model model){
        final Person person = Person.builder().address(Address.builder().build()).build();
        model.addAttribute("person", person);
        model.addAttribute("action", "/person/add");
        return "edit";
    }

    @PostMapping("/add")
    public String addPerson(Person person) {
        personService.save(person);
        return "redirect:/person/list";
    }

    @GetMapping("/edit/{personUuid}")
    public String editPerson(@PathVariable UUID personUuid, Model model) {
        final Person person = personService.getPerson(personUuid);
        model.addAttribute("person", person);
        model.addAttribute("action", "/person/edit/" + personUuid);
        return "edit";
    }

    @PostMapping("/edit/{personUuid}")
    public String editPerson(@PathVariable UUID personUuid, Person person){
    personService.editPerson(personUuid, person);
    return "redirect:/person/list";
    }

    @PostMapping("/delete/{personUuid}")
    public String deletePerson(@PathVariable UUID personUuid){
        personService.delete(personUuid);
        return "redirect:/person/list";
    }
}

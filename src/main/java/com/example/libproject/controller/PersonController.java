package com.example.libproject.controller;

import com.example.libproject.domain.Book;
import com.example.libproject.domain.Person;
import com.example.libproject.service.BookService;
import com.example.libproject.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/people")
@Controller
public class PersonController {
    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public PersonController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.getAllPerson());
        return "people/index";
    }

    @GetMapping("/show/{id}")
    public String showPersonForm(@PathVariable long id, Model model) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);

        List<Book> books = bookService.findByPersonId(id);

        model.addAttribute("books", books);

        return "people/show";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute("person") Person person) {
        return "people/add";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "people/update";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

    @PostMapping("/addperson")
    public String addPerson(@Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "people/add";
        }
        personService.createPerson(person);
        return "redirect:/people";
    }

    @PostMapping("/update/{id}")
    public String addPerson(@PathVariable long id, @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "people/update";
        }
        person.setId(id);
        personService.updatePerson(person);
        return "redirect:/people";
    }
}

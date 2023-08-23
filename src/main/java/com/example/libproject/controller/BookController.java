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

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/index";
    }

    @GetMapping("/show/{id}")
    public String showBookForm(@PathVariable long id, Model model) {
        Book book = bookService.getBook(id);

        if (book.getPerson() == null) {
            List<Person> people = personService.getAllPerson();
            model.addAttribute("people", people);
        } else {
            Person person = book.getPerson();
            model.addAttribute("person", person);
        }

        model.addAttribute("book", book);

        return "books/show";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute("book") Book book) {
        return "books/add";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "books/update";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result) {

        if (result.hasErrors()) {
            return "books/add";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable long id, @Valid Book book, BindingResult result) {

        if (result.hasErrors()) {
            return "books/update";
        }
        book.setId(id);
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @PostMapping("/assign/{id}")
    public String assignBook(@PathVariable long id, @RequestParam(value = "person") long PersonId) {
        System.out.println(id);
        System.out.println(PersonId);
        Book book = bookService.getBook(id);
        Person person = personService.getPerson(PersonId);

        book.setPerson(person);
        bookService.updateBook(book);
        return "redirect:/books/show/" + id;
    }

    @PostMapping("/release/{id}")
    public String releaseBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        book.setPerson(null);
        bookService.updateBook(book);
        return "redirect:/books/show/" + id;
    }
}

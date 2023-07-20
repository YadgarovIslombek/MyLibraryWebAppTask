package uz.islombek.libraryApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.islombek.libraryApp.dao.BookDao;
import uz.islombek.libraryApp.dao.PersonDaO;
import uz.islombek.libraryApp.model.Book;
import uz.islombek.libraryApp.model.Person;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/library")
public class LibraryController {
    private final PersonDaO personDaO;
    private final BookDao bookDao;

    @Autowired
    public LibraryController(PersonDaO personDaO, BookDao bookDao) {
        this.personDaO = personDaO;
        this.bookDao = bookDao;
    }

    @Autowired

    @GetMapping
    public String getIndexPage() {
        return "library/index";
    }

    @GetMapping("/person/new")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "library/newPerson";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "library/newPerson";
        personDaO.addPerson(person);
        return "redirect:/library";
    }


    @GetMapping("/book")
    public String getListBook(Model model) {
        List<Book> listBook = bookDao.getListBook();
        model.addAttribute("book", listBook);
        return "library/allBook";
    }

    @GetMapping("/person")
    public String getListPerson(Model model) {
        List<Person> listPerson = personDaO.getListPerson();
        model.addAttribute("person", listPerson);
        return "library/allPerson";
    }

    @GetMapping("person/{id}/edit")
    public String show(@PathVariable("id") int id, Model model) {
        Person personById = personDaO.getPersonById(id);
        model.addAttribute("person", personById);
        return "library/editByIdPerson";
    }

    @PatchMapping("/person/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "library/editByIdPerson";
        personDaO.updatePerson(id, person);
        return "redirect:/library/person";

    }

    @GetMapping("book/new")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "library/addBook";
    }

    @PostMapping("/addBook")
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "/library/addBook";
        bookDao.addBook(book);
        return "redirect:/library";
    }


    @GetMapping("book/{id}/edit")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book bookId = bookDao.getBookById(id);
        model.addAttribute("book", bookId);
        return "library/editByIdBook";
    }

    @PatchMapping("/book/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "library/editByIdBook";
        bookDao.updateBook(id, book);
        return "redirect:/library/book";


    }

    @GetMapping("/person/{id}")
    public String showPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("personBooks", bookDao.takeBooks(id));
        model.addAttribute("person", personDaO.getPersonById(id));
        return "library/personShow";
    }

    @GetMapping("/book/{id}")
    public String showBook(Model model,@ModelAttribute(




            "person") Person person, @PathVariable("id") int id) {



        model.addAttribute("book", bookDao.getBookById(id));
        Optional<Person> ownerBook = bookDao.getOwnerBook(id);
        if (ownerBook.isPresent()) {
            model.addAttribute("owner", ownerBook.get());
        } else {
            model.addAttribute("people", personDaO.getListPerson());
        }
        return "library/bookShow";

    }

    @PatchMapping("/book/{id}/returnBook")
    public String returnBook(@PathVariable("id") int id) {

        bookDao.returnLibraryBook(id);
        return "redirect:/library/book";


    }

    @PatchMapping("/book/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDao.assign(id, selectedPerson);
        return "redirect:/library/book/" + id;
    }
}

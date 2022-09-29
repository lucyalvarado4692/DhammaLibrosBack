package net.codejava;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;



@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class BooksController {

    @Autowired
    private BooksService service;

    @GetMapping("/books")
    public List<Books> list() {
        return service.listAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Books> get(@PathVariable Integer id) {
        try {
            Books books = service.get(id);
            return new ResponseEntity<Books>(books, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Books>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public void add(@RequestBody Books books){
        service.save(books);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Books> update(@RequestBody Books books, @PathVariable Integer id){
        try{
            Books existBooks = service.get(id);
                existBooks.setTitle(books.getTitle());
                existBooks.setAuthor(books.getAuthor());
                existBooks.setDescription(books.getDescription());
                existBooks.setImg(books.getImg());
                existBooks.setYear(books.getYear());
                existBooks.setPrice(books.getPrice());
            service.save(existBooks);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }


}

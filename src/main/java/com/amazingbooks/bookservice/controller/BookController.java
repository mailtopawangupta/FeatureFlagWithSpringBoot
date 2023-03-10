package com.amazingbooks.bookservice.controller;


import com.amazingbooks.bookservice.repository.Book;
import com.amazingbooks.bookservice.service.BookService;
import com.amazingbooks.bookservice.utility.FF4JConstants;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.amazingbooks.bookservice.utility.FF4JConstants.*;

@RestController
@RequestMapping("/amazingbooks")
public class BookController {

    @Autowired
    public BookService bookService;

    @Autowired
    public FF4j ff4j;


    @PostConstruct
    public void populateFeature(){
        //By default feature is disabled
        if(!ff4j.exist(FEATURE_GET_ALL_BOOKS)){
            ff4j.createFeature(new Feature(FEATURE_GET_ALL_BOOKS, false, "Feature to fetch all the books"));
        }
        if(!ff4j.exist(FEATURE_GET_BOOKS_BY_TITLE)){
            ff4j.createFeature(new Feature(FEATURE_GET_BOOKS_BY_TITLE, false,"Feature to fetch the books by Title"));
        }
        if(!ff4j.exist(FEATURE_GET_BOOKS_BY_AUTHOR)){
            ff4j.createFeature(new Feature(FEATURE_GET_BOOKS_BY_AUTHOR, false,"Feature to fetch the books by Author Name"));
        }
        if(!ff4j.exist(FEATURE_GET_BOOKS_BY_ISBN)){
            ff4j.createFeature(new Feature(FF4JConstants.FEATURE_GET_BOOKS_BY_ISBN, false,"Feature to fetch the books by Author ISBN"));
        }
        if(!ff4j.exist(FEATURE_DELETE_BOOKS)){
            ff4j.createFeature(new Feature(FF4JConstants.FEATURE_DELETE_BOOKS, false,"Feature to delete the books"));
        }
    }

    /*
    @return List of all books
     */
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        if(!ff4j.exist(FEATURE_GET_ALL_BOOKS) || ff4j.check(FF4JConstants.FEATURE_GET_ALL_BOOKS)){
            books = bookService.findAllBooks();
        }
        return books;
    }

    /*
    @param title
    @return List of books with matching title
     */
    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        List<Book> books = new ArrayList<>();
        if(!ff4j.exist(FEATURE_GET_BOOKS_BY_TITLE) || ff4j.check(FEATURE_GET_BOOKS_BY_TITLE)) {
            books = bookService.findByTitle(title);
        }
        return books;
    }

    /*
    @param author
    @return List of books with matching author
     */
    @GetMapping("/books/author/{author}")
        public List<Book> getBooksByAuthor(@PathVariable String author){
        List<Book> books = new ArrayList<>();
        if(!ff4j.exist(FEATURE_GET_BOOKS_BY_AUTHOR) || ff4j.check(FEATURE_GET_BOOKS_BY_AUTHOR)) {
            return bookService.findByAuthor(author);
        }
        return books;
    }

    /*
    @param isbn
    @return list of book with matching isbn
     */
    @GetMapping("/books/isbn/{isbn}")
    public List<Book> getBooksByIsbn(@PathVariable Long isbn){
        List<Book> books = new ArrayList<>();
        if(!ff4j.exist(FEATURE_GET_BOOKS_BY_ISBN) || ff4j.check(FEATURE_GET_BOOKS_BY_ISBN)) {
            return bookService.findByIsbn(isbn);
        }
        return books;
    }

    /*
    @param book
    @return saved/updated book
     */
    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    /*
   @param book
   @return saved book
    */
    @DeleteMapping("/book")
    public ResponseEntity<Long> deleteBook(@RequestBody Book book){
        if(null!=bookService.findByIsbn(book.getIsbn())){
            bookService.deleteBook(book);
            return new ResponseEntity<>(book.getIsbn(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

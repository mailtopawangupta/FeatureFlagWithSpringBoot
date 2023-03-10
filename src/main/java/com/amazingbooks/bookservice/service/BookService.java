package com.amazingbooks.bookservice.service;

import com.amazingbooks.bookservice.dao.BookDAO;
import com.amazingbooks.bookservice.repository.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    public BookDAO bookDAO;

    public List<Book> findByIsbn(Long isbn){
        List<Book> books = bookDAO.findByIsbn(isbn);
        return books;
    }

    public List<Book> findByTitle(String title){
        List<Book> books = bookDAO.findByTitle(title);
        return books;
    }

    public List<Book> findByAuthor(String author){
        List<Book> books = bookDAO.findByAuthor(author);
        return books;
    }

    public List<Book> findAllBooks(){
        List<Book> books = bookDAO.findAll();
        return books;
    }

    @Transactional
    public Book saveBook(Book book){
        book = bookDAO.save(book);
        return book;
    }

    @Transactional
    public void deleteBook(Book book){
        bookDAO.delete(book);

    }
}

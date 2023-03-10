package com.amazingbooks.bookservice.dao;

import com.amazingbooks.bookservice.repository.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface BookDAO extends JpaRepository<Book,Long>{
    public List<Book> findByIsbn(Long isbn);
    public List<Book> findByTitle(String title);
    public List<Book> findByAuthor(String author);

}

package com.amazingbooks.bookservice.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="isbn")
    private Long isbn;

    @Column(name="title")
    private String title;

    @Column(name="publish_date")
    private String publishDate;

    @Column(name="total_copies")
    private Integer totalCopies;

    @Column(name="issued_copies")
    private Integer issuedCopies;

    @Column(name="author")
    private String author;

}

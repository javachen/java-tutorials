package com.javachen.service;

import com.javachen.entity.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
    public List<Book> findAllBooks();

    public Book findBookById(Long bookId) ;

    public Book createBook(Book book);

    public void deleteBook(Long bookId) ;

    public Book updateBook(Map<String, String> updates, Long bookId);

    public Book updateBook(Book book, Long bookId) ;
}

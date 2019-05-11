package com.javachen.service;

import com.javachen.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{
    private String REST_URL_PREFIX = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Book> findAllBooks() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/books", List.class);
    }

    @Override
    public Book findBookById(Long bookId) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/books/{bookId}", Book.class, bookId);
    }

    @Override
    public Book createBook(Book book) {
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {

    }

    @Override
    public Book updateBook(Map<String, String> updates, Long bookId) {
        return null;
    }

    @Override
    public Book updateBook(Book book, Long bookId) {
        return null;
    }
}

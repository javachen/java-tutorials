package com.javachen.dataloader;

import com.javachen.entity.Book;
import com.javachen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.stream.Stream;

//@Component
public class DataLoader implements ApplicationRunner {

    private BookService bookService;

    @Autowired
    public DataLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Book book1 = new Book("Aldous Huxley", "Brave new world");
        Book book2 = new Book("George Orwell", "Animal Farm");
        Book book3 = new Book("George", "Thinking in Java");
        Stream.of(book1, book2, book3).forEach(bookService::createBook);
    }
}

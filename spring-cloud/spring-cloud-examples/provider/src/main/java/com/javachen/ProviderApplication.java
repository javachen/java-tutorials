package com.javachen;

import com.javachen.entity.Book;
import com.javachen.service.BookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    /**
     * 初始化用户信息
     * 注：Spring Boot2不能像1.x一样，用spring.datasource.schema/data指定初始化SQL脚本，否则与actuator不能共存
     * 原因详见：
     * https://github.com/spring-projects/spring-boot/issues/13042
     * https://github.com/spring-projects/spring-boot/issues/13539
     *
     * @param BookService bookService
     * @return runner
     */
    @Bean
    ApplicationRunner init(BookService bookService) {
        return args -> {
            Book book1 = new Book("Aldous Huxley", "Brave new world");
            Book book2 = new Book("George Orwell", "Animal Farm");
            Book book3 = new Book("George", "Thinking in Java");
            Stream.of(book1, book2, book3).forEach(bookService::createBook);
        };
    }
}
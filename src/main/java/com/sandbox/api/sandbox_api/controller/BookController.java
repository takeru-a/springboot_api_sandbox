package com.sandbox.api.sandbox_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sandbox.api.sandbox_api.domain.entity.Book;
import com.sandbox.api.sandbox_api.domain.service.BookService;

import java.util.List;

/**
 * Bookのコントローラ
 */
@RestController
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/book/register")
    public ResponseEntity<Void> registerBook(@RequestBody Book book) {
        bookService.registerBook(book);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/book/update")
    public ResponseEntity<Void> updateBook(@RequestBody Book updateInfo) {
        int result = bookService.updateBook(updateInfo);
        return result == 1 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/book/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

package com.sandbox.api.sandbox_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.api.sandbox_api.domain.entity.Book;
import com.sandbox.api.sandbox_api.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * Bookの情報を操作するサービス
 */
@Service
public class BookService {

    // BookRepositoryのDI
    @Autowired
    private BookRepository bookRepository;

    /**
     * Bookの全件取得
     * @return Bookの全件
     */
    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Bookの1件取得
     * @param id BookID
     * @return 特定したBook情報
     */
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Book情報の登録
     * @param book 登録するBook情報
     */
    @Transactional
    public void registerBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Book情報の更新
     * @param updateInfo 更新するBook情報
     * @return 更新した件数
     */
    @Transactional
    public int updateBook(Book updateInfo) {

        int success = 1;
        int fail = 0;

        // 更新するBook情報を取得
        Book book = bookRepository.findById(updateInfo.getId()).orElse(null);
        if (book == null) {
            return fail;
        }

        // 更新情報をセット
        book.setTitle(updateInfo.getTitle());
        book.setAuthor(updateInfo.getAuthor());
        book.setPublished(updateInfo.getPublished());
        book.setDescription(updateInfo.getDescription());
        book.setPrice(updateInfo.getPrice());
        
        bookRepository.save(book);
        return success;
    }

    /**
     * Bookの削除
     * @param id 削除するBookID
     */
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
}

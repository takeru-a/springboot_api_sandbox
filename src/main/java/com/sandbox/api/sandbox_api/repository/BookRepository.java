package com.sandbox.api.sandbox_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.api.sandbox_api.domain.entity.Book;

/** 
 * Bookの永続層
 * JpaRepositoryを継承することで、CRUD操作のメソッドを利用できる
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}

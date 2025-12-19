package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    
    public Book saveBook(Book book){
       
        Author author = book.getAuthor();
    if (author != null) {
        // If author has an ID, fetch from DB
        if (author.getId() != null) {
            author = authorRepository.findById(author.getId()).orElseThrow(() -> new RuntimeException("Author not found"));
        } else {
            // Save new author
            author = authorRepository.save(author);
        }
        book.setAuthor(author);
    }
    return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}

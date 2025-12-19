package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;

@Service
public class AuthorService {
    

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author){
        // if(author.getBooks()!=null){
        //     author.getBooks().forEach(book->book.setAuthor(author));
        // }
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    public List<Author> getAllAuthorsSortedbynameAndSortByBooks(){
        // List<Author> authors = getAllAuthor().stream().sorted(Comparator.comparing(Author::getName)).collect(Collectors.toList());
        // for(Author author: authors){
        //     if(author.getBooks()!=null)
        //     author.getBooks().sort(Comparator.comparing(Book::getName).reversed());
        // }
        // return authors;


        //Or by using streams pipeline
        List<Author> authors = getAllAuthor().stream().sorted(Comparator.comparing(Author::getName)).peek(author->{
            if(author.getBooks()!=null){
                author.getBooks().sort(Comparator.comparing(Book::getName).reversed());
            }
        }).collect(Collectors.toList());
        return authors;
    }

    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }


}

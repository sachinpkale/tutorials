package com.graphqljava.tutorial.bookdetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;

public class BookBatchLoader implements BatchLoader<String, Book> {

    private static List<Author> authors = Arrays.asList(
            Author.builder().id("author-1").firstName("Joanne").lastName("Rowling").build(),
            Author.builder().id("author-2").firstName("Herman").lastName("Melville").build(),
            Author.builder().id("author-3").firstName("Anne").lastName("Rice").build()
    );

    private static List<Book> books = Arrays.asList(
            Book.builder().id("book-1").name("Harry Potter and the Philosopher's Stone").pageCount(223).author(authors.get(0)).build(),
            Book.builder().id("book-2").name("Moby Dick").pageCount(635).author(authors.get(1)).build(),
            Book.builder().id("book-3").name("Interview with the vampire").pageCount(371).author(authors.get(2)).build()
    );

    @Override
    public CompletionStage<List<Book>> load(final List<String> keys) {
        System.out.println(keys);
        List<Book> l = new ArrayList<>();
        for(String key: keys) {
            l.add(books.stream()
                       .filter(book -> book.getId().equals(key))
                       .findFirst()
                       .orElse(null));
        }
        return CompletableFuture.completedFuture(l);
    }
}

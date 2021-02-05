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
            Author.builder().id("author-3").firstName("Anne").lastName("Rice").build(),
            Author.builder().id("author-4").firstName("Joanne").lastName("Rowling").build(),
            Author.builder().id("author-5").firstName("Herman").lastName("Melville").build(),
            Author.builder().id("author-6").firstName("Anne").lastName("Rice").build(),
            Author.builder().id("author-7").firstName("Joanne").lastName("Rowling").build(),
            Author.builder().id("author-8").firstName("Herman").lastName("Melville").build(),
            Author.builder().id("author-9").firstName("Anne").lastName("Rice").build(),
            Author.builder().id("author-10").firstName("Joanne").lastName("Rowling").build()
    );

    private static List<Book> books = Arrays.asList(
            Book.builder().id("book-1").name("Harry Potter and the Philosopher's Stone").pageCount(223).author(authors.get(0)).build(),
            Book.builder().id("book-2").name("Moby Dick").pageCount(635).author(authors.get(1)).build(),
            Book.builder().id("book-3").name("Interview with the vampire").pageCount(371).author(authors.get(2)).build(),
            Book.builder().id("book-4").name("Book A").pageCount(478).author(authors.get(3)).build(),
            Book.builder().id("book-5").name("Book B").pageCount(381).author(authors.get(4)).build(),
            Book.builder().id("book-6").name("Book C").pageCount(666).author(authors.get(5)).build(),
            Book.builder().id("book-7").name("Book D").pageCount(970).author(authors.get(6)).build(),
            Book.builder().id("book-8").name("Book E").pageCount(1455).author(authors.get(7)).build(),
            Book.builder().id("book-9").name("Book F").pageCount(822).author(authors.get(8)).build(),
            Book.builder().id("book-10").name("Book G").pageCount(552).author(authors.get(9)).build()
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

package com.graphqljava.tutorial.bookdetails;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

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

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.getId().equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }
}

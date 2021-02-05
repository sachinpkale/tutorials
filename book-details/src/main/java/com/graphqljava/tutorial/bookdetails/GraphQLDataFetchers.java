package com.graphqljava.tutorial.bookdetails;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

    private static List<Book> books = Arrays.asList(
            Book.builder().id("book-1").name("Harry Potter and the Philosopher's Stone").pageCount(223).author("author-1").build(),
            Book.builder().id("book-2").name("Moby Dick").pageCount(635).author("author-2").build(),
            Book.builder().id("book-3").name("Interview with the vampire").pageCount(371).author("author-3").build()
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

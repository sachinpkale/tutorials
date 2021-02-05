package com.graphqljava.tutorial.bookdetails;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

    public DataFetcher<CompletableFuture<Book>> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            DataLoader<String, Book> dataLoader = dataFetchingEnvironment.getDataLoader("book");
            return dataLoader.load(bookId);
        };
    }
}

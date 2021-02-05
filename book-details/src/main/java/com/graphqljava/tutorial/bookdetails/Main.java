package com.graphqljava.tutorial.bookdetails;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

public class Main {
    public static void main(String[] args) throws Exception {
        GraphQLSchema schema = GraphQLProvider.buildSchema();

        DataLoaderOptions dataLoaderOptions = DataLoaderOptions.newOptions().setCachingEnabled(true);
        DataLoader<String, Book> bookDataLoader = DataLoader.newDataLoader(new BookBatchLoader(), dataLoaderOptions);

        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register("book", bookDataLoader);

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 1; i < 11; i++) {
            String query = "query { bookById(id: \"book-" + ((i % 2) + 1) + "\") { id name author {firstName} } }";

            ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                                                          .query(query)
                                                          .dataLoaderRegistry(registry)
                                                          .build();

            executorService.submit(() -> {
                CompletableFuture<ExecutionResult> executeResult = graphQL.executeAsync(executionInput);
                try {
                    System.out.println((Object)executeResult.get().getData());
                } catch (Exception e) {
                    //Ignore for now
                }
            });
        }

        Thread.sleep(5000);

        executorService.shutdown();
    }
}

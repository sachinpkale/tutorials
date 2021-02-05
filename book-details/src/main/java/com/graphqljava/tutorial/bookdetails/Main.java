package com.graphqljava.tutorial.bookdetails;

import java.io.IOException;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;

import com.google.common.collect.ImmutableMap;

import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

public class Main {
    public static void main(String[] args) throws IOException {
        GraphQLSchema schema = GraphQLProvider.buildSchema();

        DataLoaderOptions dataLoaderOptions = DataLoaderOptions.newOptions().setCachingEnabled(false);
        DataLoader<String, Book> bookDataLoader = DataLoader.newDataLoader(new BookBatchLoader(), dataLoaderOptions);

        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register("book", bookDataLoader);

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        String query = "query { bookById(id: \"book-1\") { name author {firstName} } }";

        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                                                      .query(query)
                                                      .dataLoaderRegistry(registry)
                                                      .build();

        System.out.println(graphQL.execute(executionInput));
    }
}

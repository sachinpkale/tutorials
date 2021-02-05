package com.graphqljava.tutorial.bookdetails;

import java.io.IOException;

import graphql.ExecutionInput;
import graphql.schema.GraphQLSchema;

public class Main {
    public static void main(String[] args) throws IOException {
        GraphQLSchema schema = GraphQLProvider.buildSchema();

        String query = "query { bookById(id: \"book-1\") { name } }";

        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                                                      .query(query)
                                                      .build();

        // Parse

        // Parse and Validate

        // Execute
    }
}

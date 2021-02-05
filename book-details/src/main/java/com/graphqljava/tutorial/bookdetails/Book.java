package com.graphqljava.tutorial.bookdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Book {
    String id;
    String name;
    Integer pageCount;
    String author;
}

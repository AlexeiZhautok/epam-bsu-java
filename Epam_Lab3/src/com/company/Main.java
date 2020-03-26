package com.company;

public class Main {
    public static void main(String[] args) {
        BookCollection test = new BookCollection();
        BookStream testStream = new BookStream(test);
        for(var i : testStream.SortBooksByName()) {
            System.out.println(i);
        }
        testStream.PrintAuthorsForEach();
        for(var i : testStream.GetAuthors()) {
            System.out.println(i);
        }
    }
}

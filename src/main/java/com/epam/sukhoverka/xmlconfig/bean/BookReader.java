package com.epam.sukhoverka.xmlconfig.bean;

import com.epam.sukhoverka.xmlconfig.annotation.BookReaderTracker;
import com.epam.sukhoverka.xmlconfig.annotation.Speaker;
public class BookReader implements IBookReader{

    private Book book;

    public BookReader(){
        System.out.println("BookReader object created!");
    }


    @Speaker
    @BookReaderTracker
    public void sayBookTitle(){
        System.out.println("Book title is: " + book.getTitle());
    }


    @Speaker
    @BookReaderTracker
    public void sayBookAuthor() {
        System.out.println("Book author is: " + book.getAuthor());
    }


    public void setBook(Book book){
        this.book = book;
    }
}

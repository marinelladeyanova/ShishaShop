package com.example.model.pojos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Comment {

    private int id;
    private String text;
    private LocalDate date;

    public Comment(String text) {
        this.text = text;
        this.date = LocalDate.now();
    }

    public Comment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

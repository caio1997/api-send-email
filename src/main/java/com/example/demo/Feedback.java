package com.example.demo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Feedback{

    @NotNull
    @Email
    private String email;

    @NotNull
    private String title;

    @NotNull
    @Min(10)
    private String text;

    public Feedback(@NotNull @Email String email, @NotNull String title, @NotNull @Min(10) String text) {
        this.email = email;
        this.title = title;
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

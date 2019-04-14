package com.company.model;

import javax.security.auth.Subject;

/**
 * Class containing all the necessary informatrions to send the fake mail
 * @author Luca Reis de Carvalho -Guillaume Vetter
 */
public class Mail {
    private String from;
    private String to;
    private String data;
    private String subject;

    public Mail(String from, String to, String subject, String data) {
        this.from = from;
        this.to = to;
        this.data = data;
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        data = data;
    }
}

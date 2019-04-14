package com.company.model;

/**
 * Class containing all the necessary informatrions to send the fake mail
 * @author Luca Reis de Carvalho -Guillaume Vetter
 */
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String message;

    public Mail(String from, String to, String subject, String message) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message=message;
    }

    public String getFrom() {
        return from;
    }


    public String getTo() {
        return to;
    }


    public String getSubject() {
        return subject;
    }


    public String getMessage() {
        return message;
    }


}

package com.company.model;

/**
 * Class containing all the necessary informatrions to send the fake mail
 * @author Luca Reis de Carvalho -Guillaume Vetter
 */
public class Mail {
    private String From;
    private String To;
    private String Data;

    public Mail(String from, String to, String data) {
        From = from;
        To = to;
        Data = data;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}

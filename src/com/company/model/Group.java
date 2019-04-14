package com.company.model;

import java.util.List;
/**
 * Class representing a group of victims with a list of recipients and a sender
 * @author Luca Reis de Carvalho -Guillaume Vetter
 */
public class Group {
    private List<Person> recipients;
    private Person sender;

    public Group(List<Person> recipients, Person sender) {
        this.recipients = recipients;
        this.sender = sender;
    }

    public Group() {
    }

    public void addRecipient(Person p){
        recipients.add(p);
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public List<Person> getRecipients() {
        return recipients;
    }

    public Person getSender() {
        return sender;
    }
}

package com.company.model;

import java.util.List;

public class Group {
    private List<Person> recipients;
    private Person sender;

    public Group(List<Person> recipients, Person sender) {
        this.recipients = recipients;
        this.sender = sender;
    }

    public List<Person> getRecipients() {
        return recipients;
    }

    public Person getSender() {
        return sender;
    }
}

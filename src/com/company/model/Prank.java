package com.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all the necessary informatrions for the prank(a group and a message)
 * @author Luca Reis de Carvalho -Guillaume Vetter
 */
public class Prank {
    private Group victims;
    private String subject;
    private String message;

    public Prank(Group victims, String subject,String message) {
        this.victims = victims;
        this.subject= subject;
        this.message = message;
    }

    /**
     * Generates all the mails for the current prank
     * @return the list of fake mails
     */
    public List<Mail> generateMails(){
        List<Mail> mails= new ArrayList<Mail>();
        for (Person v: victims.getRecipients()) {
            mails.add(new Mail(victims.getSender().getEmail(),v.getEmail(),subject,message));
        }
        return mails;
    }
}

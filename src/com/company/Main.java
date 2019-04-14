package com.company;


import com.company.model.Mail;
import com.company.model.Person;
import com.company.smtp.SMTPClient;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Person p1= new Person("dummy@gmail.com");
        Person p2= new Person("dummy2@gmail.com");
        Mail m= new Mail("dummy@gmail.com","dummy2@gmail.com", "nothing","pouet pouet");
        try {
            SMTPClient smtpClient= new SMTPClient("127.0.0.1",2525);

            smtpClient.sendMail(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

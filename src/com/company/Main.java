package com.company;


import com.company.config.ConfigManager;
import com.company.model.Mail;
import com.company.model.Person;
import com.company.model.Prank;
import com.company.model.PrankGenerator;
import com.company.smtp.SMTPClient;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrankGenerator pg=new PrankGenerator();

        try {
            ConfigManager cm= ConfigManager.getInstance();
            SMTPClient smtpClient= new SMTPClient(cm.getSmtpServerAddress(),cm.getSmtpServerPort());
            for(int i= 0; i < cm.getNumberOfGroups();i++){
              Prank p= pg.generatePrank();

              List<Mail> mails = p.generateMails();

              for (Mail m:mails) {
                smtpClient.sendMail(m);
              }
            System.out.println(mails.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

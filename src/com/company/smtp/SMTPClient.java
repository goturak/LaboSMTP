package com.company.smtp;

import com.company.model.Mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SMTPClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public SMTPClient(String ip, int port)  {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMessage("EHLO test",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendMessage(String msg,boolean waitForAnswer) throws IOException {

        out.println(msg);
        if(waitForAnswer) {
            String resp = in.readLine();
            return resp;
        }else{
            return null;
        }
    }
    public void sendMail(Mail mail) throws IOException, InterruptedException {
        System.out.println("new mail");
        System.out.println(sendMessage("MAIL FROM: "+mail.getFrom(),true));
        Thread.sleep(20);
        System.out.println(sendMessage("RCPT TO: "+mail.getTo(),true));
        Thread.sleep(20);
        System.out.println(sendMessage("DATA",true));
        Thread.sleep(20);
        sendMessage("From: "+mail.getFrom(),false);
        Thread.sleep(20);
        sendMessage("To: "+mail.getTo(),false);
        Thread.sleep(20);
        sendMessage("subject: "+ mail.getSubject()+"\n\n"+mail.getMessage(),false);
        Thread.sleep(20);
        System.out.println(sendMessage("\r\n.\r\n",true));
        Thread.sleep(20);
        out.flush();
        System.out.println("end of mail");
    }


    public void disconnect() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

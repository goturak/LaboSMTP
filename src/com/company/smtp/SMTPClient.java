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
            sendMessage("EHLO test");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
    public void sendMail(Mail mail) throws IOException {
        sendMessage("MAIL FROM: "+mail.getFrom());
        sendMessage("RCPT TO: "+mail.getTo());
        sendMessage("DATA");
        sendMessage("From: "+mail.getFrom());
        sendMessage("To: "+mail.getTo());
        sendMessage("subject: "+ mail.getSubject()+"\n\n"+mail.getMessage()+"\r\n.\r\n");

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

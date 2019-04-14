package com.company.config;

import com.company.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a parser for the config files, which create Adresses, Groups
 * and messages for the PrankGenerator.
 */
public class ConfigManager {
    private final List<Person> persons;
    private final List<String> messages;

    public ConfigManager() throws IOException {
        persons = parseAdress("adresses.txt");
        messages = parseMessages("messages.txt");

    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<String> getMessages() {
        return messages;
    }

    private List<Person> parseAdress(String fileName) throws IOException {
        List<Person> persons;
        try(FileInputStream stream = new FileInputStream(fileName)){
            InputStreamReader r = new InputStreamReader(stream);
            try(BufferedReader br = new BufferedReader(r)){
                persons = new ArrayList<>();
                String address;
                while ((address = br.readLine()) != null){
                    persons.add(new Person(address));
                }
            }
        }
        return persons;
    }

    private List<String> parseMessages(String fileName) throws IOException {
        List<String> messages;
        try(FileInputStream stream = new FileInputStream(fileName)){
            InputStreamReader r = new InputStreamReader(stream);
            try(BufferedReader br = new BufferedReader(r)){
                messages = new ArrayList<>();
                String line = br.readLine();
                while (line != null){
                    String message = "";
                    while((line != null && !line.equals("---"))){
                        message += line;
                        message += "\r\n";
                        line = br.readLine();
                    }
                    messages.add(message);
                    line = br.readLine();
                }
            }
        }
        return messages;
    }
}
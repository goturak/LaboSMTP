package com.company.config;

import com.company.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class represent a parser for the config files, which create Adresses, Groups
 * and messages for the PrankGenerator.
 * This class is a Singleton
 */
public final class ConfigManager {
    private static ConfigManager instance;

    private final List<Person> persons;
    private final List<String> messages;
    private String smtpServerAddress;
    private int smtpServerPort;
    private ArrayList<Person> witnessesToCC;
    private int numberOfGroups;

    private ConfigManager() throws IOException {
        persons = parseAdress("adresses.txt");
        messages = parseMessages("messages.txt");
        parseConfig("config.properties");
    }

    public ConfigManager getInstance() throws IOException {
        if(instance == null){
            this.instance = new ConfigManager();
        }
        return instance;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public ArrayList<Person> getWitnessesToCC() {
        return witnessesToCC;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    private void parseConfig(String fileName) throws IOException{
        FileInputStream stream = new FileInputStream(fileName);
        Properties p = new Properties();
        p.load(stream);
        this.smtpServerAddress = p.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(p.getProperty("stmpServerPort"));
        this.numberOfGroups = Integer.parseInt(p.getProperty("numberOfGroups"));

        this.witnessesToCC = new ArrayList<>();
        String field = p.getProperty("witnessesToCC");
        String[] witnesses = field.split(",");
        for(String w : witnesses){
            this.witnessesToCC.add(new Person(w));
        }

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


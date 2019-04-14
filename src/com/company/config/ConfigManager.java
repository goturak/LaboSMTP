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
 * @author Guillaume Vetter & Luca Reis de Carvalho
 */
public final class ConfigManager {
    private static ConfigManager instance;

    private final List<Person> persons;
    private final List<String> messages;
    private String smtpServerAddress;
    private int smtpServerPort;
    private ArrayList<Person> witnessesToCC;
    private int numberOfGroups;

    /**
     * private constructor of the config manager. Calls 3 private methods to parse
     * the files.
     * @throws IOException
     */
    private ConfigManager() throws IOException {
        persons = parseAddress("addresses.txt");
        messages = parseMessages("messages.txt");
        parseConfig("config.properties");
    }

    /**
     * getInstance method of the singleton.
     * @return the current instance of ConfigManager, creating one if it doesnt exists.
     * @throws IOException
     */
    public ConfigManager getInstance() throws IOException {
        if(instance == null){
            this.instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Getter of the person listed in addresses.txt
     * @return a list of person created with the addresses listed in addresses.txt
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Getter of the messages listed in messages.txt
     * @return Every messages stored in messages.txt
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Getter of the smtp server address
     * @return the smtp server address stored in the config file.
     */
    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    /**
     * Getter of the smtp server port
     * @return the smtp server port stored in the config file.
     */
    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    /**
     * Getter of the witness to CC
     * @return a List of person that we want to put in CC, stored in the config file.
     */
    public ArrayList<Person> getWitnessesToCC() {
        return witnessesToCC;
    }

    /**
     * Getter of the number of group
     * @return the number of group stored in the config file.
     */
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Private method used to parse the config file.
     * @param fileName the path of the config file.
     * @throws IOException
     */
    private void parseConfig(String fileName) throws IOException{
        FileInputStream stream = new FileInputStream(fileName);
        //We use the properties package to help us parse the file.
        Properties p = new Properties();
        p.load(stream);
        this.smtpServerAddress = p.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(p.getProperty("stmpServerPort"));
        this.numberOfGroups = Integer.parseInt(p.getProperty("numberOfGroups"));

        //For every person (separated by a comma), we store a new person in the list.
        this.witnessesToCC = new ArrayList<>();
        String field = p.getProperty("witnessesToCC");
        String[] witnesses = field.split(",");
        for(String w : witnesses){
            this.witnessesToCC.add(new Person(w));
        }

    }

    /**
     * Parser of the address File
     * @param fileName the path of the address file
     * @return a list of person, corresponding to the list of address in the file.
     * @throws IOException
     */
    private List<Person> parseAddress(String fileName) throws IOException {
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

    /**
     * Parser of the messages file, every message being separated by "---".
     * @param fileName the path of the messages file.
     * @return A list of string, with each entry being a message.
     * @throws IOException
     */
    private List<String> parseMessages(String fileName) throws IOException {
        List<String> messages;
        try(FileInputStream stream = new FileInputStream(fileName)){
            InputStreamReader r = new InputStreamReader(stream);
            try(BufferedReader br = new BufferedReader(r)){
                messages = new ArrayList<>();
                String line = br.readLine();
                while (line != null){
                    String message = "";

                    //Messages are considered separated by "---"
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
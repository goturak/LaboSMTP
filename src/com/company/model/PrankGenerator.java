package com.company.model;

import com.company.config.ConfigManager;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrankGenerator {
    private List<Prank> pranks;

    public PrankGenerator() {

    }

    public Prank generatePrank(){
        
    }

    public Group generateGroup(){
        Random rnd = new Random();
        List<Person> availablePersons= ConfigManager.getPersons();
        int size= rnd.nextInt(ConfigManager.getPersons().size()-2);
        size +=3;
        Group result= new Group();
        Collections.shuffle(availablePersons);
        result.setSender(availablePersons.get(0));
        for (int i = 1; i < size;i++){
            result.addRecipient(availablePersons.get(i));
        }

        return result;
    }
}

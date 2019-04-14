package com.company.model;

import com.company.config.ConfigManager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrankGenerator {
    private ConfigManager cm;


    public PrankGenerator() {
        try {
            cm=ConfigManager.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Prank generatePrank(){
        Random rnd = new Random();
        int index=rnd.nextInt(cm.getMessages().size());
        return new Prank(generateGroup(), cm.getSubjects().get(index),cm.getMessages().get(index));
    }

    /**
     * generates a random groups of victims chosen among the victim list parsed by the confimanager
     * @return the generated group
     */
    private Group generateGroup(){
        Random rnd = new Random();
        List<Person> availablePersons= cm.getPersons();
        int size= rnd.nextInt(availablePersons.size()-2);
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

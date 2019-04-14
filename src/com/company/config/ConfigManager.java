package com.company.config;

import com.company.model.Group;
import com.company.model.Person;

import java.util.List;

public class ConfigManager {
    static List<Person> persons;

    public static List<Person> getPersons() {
        return persons;
    }
}

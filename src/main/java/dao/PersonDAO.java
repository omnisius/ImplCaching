package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {

    void create(Person person);

    List<Person> getPersons();
}

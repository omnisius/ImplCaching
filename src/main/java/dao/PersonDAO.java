package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {

    public void create(Person person);

    public List<Person> getPersons();
}

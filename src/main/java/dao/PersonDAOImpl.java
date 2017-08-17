package dao;

import manager.ConnectionManager;
import model.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class PersonDAOImpl implements PersonDAO {
    private static final Logger LOG = Logger.getLogger(PersonDAOImpl.class);

    public void create(Person person) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement("INSERT INTO caching.persons (id ,name) VALUES (NULL , ?)");
            statement.setString(1,  person.getName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            disconnect(getConnection(), null, statement);
        }
    }

    public List<Person> getPersons() {
        List<Person> persons = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM caching.persons");
            Person person;
            while (resultSet.next()) {
                person = new Person();
                person.setId(Integer.parseInt(resultSet.getString("id")));
                person.setName(resultSet.getString("name"));

                persons.add(person);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            disconnect(getConnection(), resultSet, statement);
        }
        LOG.info(persons);
        return persons;
    }

    private void disconnect(Connection connection, ResultSet resultSet, Statement statement) {
        ConnectionManager.getInstance().disconnect(connection, resultSet, statement);
    }

    private Connection getConnection() {
        return ConnectionManager.getInstance().getConnection();
    }
}

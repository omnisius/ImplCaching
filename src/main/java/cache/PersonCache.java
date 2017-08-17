package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import dao.PersonDAO;
import dao.PersonDAOImpl;
import model.Person;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PersonCache {
    private PersonDAO dao;
    private LoadingCache cache;

    public void init(){
        dao = new PersonDAOImpl();
        cache = CacheBuilder.newBuilder().
                maximumSize(100).
                expireAfterAccess(10, TimeUnit.SECONDS).
                build(new CacheLoader<String, List<Person>>() {
                    @Override
                    public List<Person> load(String s) throws IOException {
                        return dao.getPersons();
                    }
                });
    }
    public Person get(String key) throws ExecutionException {
        return (Person) cache.get(key);
    }
}

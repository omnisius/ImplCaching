import cache.PersonCache;
import dao.PersonDAO;
import dao.PersonDAOImpl;

import java.util.concurrent.ExecutionException;

public class Application {

    public static void main(String[] args) throws ExecutionException {
        PersonDAO dao = new PersonDAOImpl();
        System.out.println(dao.getPersons());

        PersonCache cache = new PersonCache();
        cache.init();
        System.out.println(cache.size());

    }
}

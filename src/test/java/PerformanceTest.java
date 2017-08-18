import dao.PersonDAO;
import dao.PersonDAOImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PerformanceTest {

    @Test
    public void testGetPersonsPerf( ) {
        PersonDAO dao = new PersonDAOImpl();

        long startTime = System.currentTimeMillis( );
        dao.getPersons();
        long endTime = System.currentTimeMillis( );
        long time = endTime - startTime;
        System.out.println("Time of retrieving data from database is " + time + " ms");

        long startTimeAfterCache = System.currentTimeMillis( );
        dao.getPersons();
        long endTimeAfterCache = System.currentTimeMillis( );
        long timeAfterCache = endTimeAfterCache - startTimeAfterCache;
        System.out.println("Time of getting data from cache is " + timeAfterCache + " ms");
        assertTrue( "Is time after data has been loaded to cache is ten times greater that before",time / 10 > timeAfterCache);
    }
}

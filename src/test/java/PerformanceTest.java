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
        System.out.println(time);

        long startTimeAfterCache = System.currentTimeMillis( );
        dao.getPersons();
        long endTimeAfterCache = System.currentTimeMillis( );
        long timeAfterCache = endTimeAfterCache - startTimeAfterCache;
        System.out.println(timeAfterCache);
        assertTrue( "Is time after data has been loaded to cache is ten times greater that before",time / 10 > timeAfterCache);
    }
}

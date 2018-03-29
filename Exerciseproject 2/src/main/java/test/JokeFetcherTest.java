package test;

import org.hamcrest.collection.IsArrayWithSize;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.runners.MockitoJUnitRunner;
import testex.DateFormatter;
import testex.JokeException;
import testex.JokeFetcher;
import testex.Jokes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.hamcrest.BaseMatcher;
import testex.jokefetching.FetcherFactory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {
    private DateFormatter dateFormatter;
    private Calendar calendar;
    private FetcherFactory factory;

    @BeforeEach
    void init() {
        dateFormatter = new DateFormatter();
    }

    @Test
    public void testGetAvailableTypes() throws Exception {
        JokeFetcher jokeFetcher = new JokeFetcher(dateFormatter, factory);
        List<String> availableTypes = jokeFetcher.getAvailableTypes();

        //Test if the size of the array is 4, and then test if it contains the given items.
        assertThat(availableTypes, hasSize(4));
        assertThat(availableTypes, contains("eduprog", "chucknorris", "moma", "tambal"));
    }

    @Test
    public void testIsStringValid() throws Exception {
        JokeFetcher jokeFetcher = new JokeFetcher(dateFormatter, factory);

        //Test valid joke tokens
        boolean validResult = jokeFetcher.isStringValid("eduprog,chucknorris,moma,tambal");
        assertTrue(validResult);

        //Test unvalid joke tokens
        boolean unvalidResult = jokeFetcher.isStringValid("this should be unvalid");
        assertFalse(unvalidResult);
    }

    @Test
    public void testGetJokes() throws Exception {
        factory = new FetcherFactory();
        JokeFetcher jokeFetcher = new JokeFetcher(dateFormatter, factory);

        calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 29, 12, 41);
        Date testDate = calendar.getTime();

        //Test that the right time gets stored
        Jokes jokes = jokeFetcher.getJokes("chucknorris,chucknorris", testDate, "Europe/Copenhagen");
        assertThat(jokes.getTimeZoneString(), is("29 mar. 2018 12:41 PM"));
    }

    @Test
    public void testWrongJoke() throws Exception {
        factory = new FetcherFactory();
        JokeFetcher jokeFetcher = new JokeFetcher(dateFormatter, factory);

        calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 29, 12, 41);
        Date testDate = calendar.getTime();

        //Testing with wrong joke
        String wrongJoke = "CPH JOKE";
        assertThrows(JokeException.class, () -> jokeFetcher.getJokes(wrongJoke, testDate, "Europe/Copenhagen"));
    }
}
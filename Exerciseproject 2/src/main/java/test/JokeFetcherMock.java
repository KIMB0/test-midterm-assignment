package test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testex.*;
import testex.jokefetching.FetcherFactory;
import testex.jokefetching.IJokeFetcher;

import java.lang.reflect.Array;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyObject;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class JokeFetcherMock {
    private Date testDate;
    private JokeFetcher jokeFetcher;

    @Mock
    EducationalProgrammingJoke educationalProgrammingJoke;

    @Mock
    ChuckNorrisJoke chuckNorrisJoke;

    @Mock
    YoMommaJoke yoMommaJoke;

    @Mock
    TambalJoke tambalJoke;

    @Mock
    IDateFormatter iDateFormatter;

    @Mock
    DateFormatter dateFormatter;

    @Mock
    FetcherFactory factory;

    @Before
    public void init() throws JokeException {
        MockitoAnnotations.initMocks(this);

        jokeFetcher = new JokeFetcher(dateFormatter, factory);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 29, 12, 41);
        testDate = calendar.getTime();

        List<IJokeFetcher> jokes = new ArrayList<>();
        jokes.add(chuckNorrisJoke);
        jokes.add(yoMommaJoke);
        jokes.add(tambalJoke);
        jokes.add(educationalProgrammingJoke);

        when(factory.getAvailableTypes()).thenReturn(Arrays.asList("eduprog", "chucknorris", "moma", "tambal"));
//        when(factory.getJokeFetchers("eduprog,chucknorris,moma,tambal")).thenReturn(jokes);
//        when(dateFormatter.getFormattedDateString(anyObject(), testDate)).thenReturn("29 Mar 2018 12:41 PM");
    }

    @Test
    public void testDateFormat() throws JokeException {
        Jokes jokes = jokeFetcher.getJokes("chucknorris,chucknorris", testDate, "Europe/Copenhagen");

        System.out.println(testDate);
        verify(dateFormatter).getFormattedDateString("Europe/Copenhagen", testDate);

        assertThat(jokes.getTimeZoneString(), is("29 mar. 2018 12:41 PM"));
    }

    @Test
    public void testFactoryCall() throws JokeException {
        Jokes jokes = jokeFetcher.getJokes("chucknorris,chucknorris,moma", testDate, "Europe/Copenhagen");

        verify(factory).getJokeFetchers("chucknorris,chucknorris,moma");

        assertThat(jokes.getTimeZoneString(), is("29 mar. 2018 12:41 PM"));
    }

    @Test
    public void testGetJokes() throws JokeException {
        List<Joke> jokes = jokeFetcher.getJokes("chucknorris,moma,eduprog,tambal", testDate, "Europe/Copenhagen").getJokes();
        assertThat(jokes, contains(chuckNorrisJoke, yoMommaJoke, educationalProgrammingJoke, tambalJoke));
    }
}

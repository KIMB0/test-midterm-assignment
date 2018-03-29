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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    void init() throws JokeException {
        MockitoAnnotations.initMocks(this);

//        List<IJokeFetcher> fetchers = Arrays.asList(educationalProgrammingJoke, chuckNorrisJoke, yoMommaJoke, tambalJoke);
//        when(factory.getJokeFetchers("eduprog,chucknorris,moma,tambal")).thenReturn(fetchers);
//        List<String> types = Arrays.asList("EduJoke","ChuckNorris","Moma","Tambal");
//        when(factory.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(dateFormatter, factory);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 29, 12, 41);
        testDate = calendar.getTime();
        when(dateFormatter.getFormattedDateString(anyObject(), testDate)).thenReturn("29 Mar 2018 12:41 PM");
    }

    @Test
    public void testDateFormat() throws JokeException {
        Jokes jokes = jokeFetcher.getJokes("chucknorris,chucknorris", testDate, "Europe/Copenhagen");

        verify(dateFormatter).getFormattedDateString("Europe/Copenhagen", testDate);

        assertThat(jokes.getTimeZoneString(), is("dsf"));
    }
}

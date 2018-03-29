package test;

import org.junit.Test;
import testex.Joke;
import testex.jokefetching.FetcherFactory;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;



public class FetcherFactoryTest {
    FetcherFactory factory;
    @Test
    public void testGetAvailableTypes() throws Exception {
        factory = new FetcherFactory();
        List<Joke> result = factory.getJokeFetchers("eduprog,chucknorris,moma,tambal");
        assertThat(result, hasSize(4));
    }

    @Test
    public void getJokeFetchers() throws Exception {
        factory = new FetcherFactory();
        List<String> result = factory.getAvailableTypes();

        assertThat(result, hasSize(4));
        assertThat(result, contains("eduprog","chucknorris","moma","tambal"));
    }

}
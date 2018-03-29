package testex.jokefetching;

import testex.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetcherFactory implements IFetcherFactory {
    private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<Joke> getJokeFetchers(String jokesToFetch) {



        String[] tokens = jokesToFetch.split(",");
        List<Joke> jokes = new ArrayList<>();
        for(String token : tokens){
            switch(token){
                case "eduprog" : jokes.add(new EducationalProgrammingJoke().getJoke());break;
                case "chucknorris" : jokes.add(new ChuckNorrisJoke().getJoke());break;
                case "moma" : jokes.add(new YoMommaJoke().getJoke());break;
                case "tambal" : jokes.add(new TambalJoke().getJoke());break;
            }
        }
        return jokes;
    }
}

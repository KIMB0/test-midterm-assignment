package testex.jokefetching;

import testex.Joke;

import java.util.List;

public interface IFetcherFactory {
    List<String> getAvailableTypes();

    List<Joke> getJokeFetchers(String jokesToFetch);
}

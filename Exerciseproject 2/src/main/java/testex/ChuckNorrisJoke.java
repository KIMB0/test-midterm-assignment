package testex;

import testex.jokefetching.IJokeFetcher;

import static com.jayway.restassured.RestAssured.given;

public class ChuckNorrisJoke implements IJokeFetcher {

    @Override
    public Joke getJoke(){
        try{
            String joke = given().get("http://api.icndb.com/jokes/random").path("value.joke");
            String reference = "http://api.icndb.com/";
            return new Joke(joke, reference);
        }catch(Exception e){
            return null;
        }
    }
}
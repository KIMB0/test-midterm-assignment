package testex;

import testex.jokefetching.IJokeFetcher;

import static com.jayway.restassured.RestAssured.given;

public class TambalJoke implements IJokeFetcher {

    public Joke getJoke(){
        try{
            String joke  = given().get("http://tambal.azurewebsites.net/joke/random").path("joke");
            String reference = "http://tambal.azurewebsites.net/joke/random";
            return new Joke(joke,reference);
        }catch(Exception e){
            return null;
        }
    }
}

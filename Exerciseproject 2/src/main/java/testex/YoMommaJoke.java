package testex;

import testex.jokefetching.IJokeFetcher;

import static com.jayway.restassured.RestAssured.given;

public class YoMommaJoke implements IJokeFetcher {

    public Joke getJoke(){
        try{
            //API does not set response type to JSON, so we have to force the response to read as so
            String joke = given().get("http://api.yomomma.info/").andReturn().jsonPath().getString("joke");
            String reference = "http://api.yomomma.info/";
            return new Joke(joke,reference);
        }catch(Exception e){
            return null;
        }
    }
}

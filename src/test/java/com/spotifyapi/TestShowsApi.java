/*
 Purpose : Program for Test Cases is Written to Test Spotify Shows Api
 @author Dinesh Kumar Peddakotla
 @since 23-05-2021
*/
package com.spotifyapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotifyapi.TestUserProfileApi.token;
import static io.restassured.RestAssured.*;

public class TestShowsApi {

    /** declared string array and initialized */
    public String[] showIds = { "6ZcvVBPQ2ToLXEWVbaw59P", "2X40qLyoj1wQ2qE5FVpA7x"};

    /**
     *test case is written to test several shows api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_01_SeveralShows_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("ids", (Object[]) showIds)
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/shows");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test show episodes api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_02_ShowEpisodes_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .queryParam("limit", "3")
                                   .queryParam("offset", "3")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/shows/"+showIds[0]+"/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test show api and to check status code 200
     * for GET operation
     */
    @Test
    public void test_03_GetAShow_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/shows/"+showIds[1]);

        response.prettyPrint();
        response.then().statusCode(200);
    }
}

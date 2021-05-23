/*
 Purpose : Program for Test Cases is Written to Test Spotify Search Api
 @author Dinesh Kumar Peddakotla
 @since 23-05-2021
*/
package com.spotifyapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotifyapi.TestUserProfileApi.token;
import static io.restassured.RestAssured.*;

public class TestSearchApi {

    /**
     *test case is written to test search api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_01_WhenSearch_ForAnItem_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("q", "Uppena")
                                   .queryParam("type", "album")
                                   .queryParam("market", "IN")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/search");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}

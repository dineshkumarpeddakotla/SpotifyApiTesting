/*
 Purpose : Program for Test Cases is Written to Test Spotify Track Api
 @author Dinesh Kumar Peddakotla
 @since 23-05-2021
*/
package com.spotifyapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.spotifyapi.TestUserProfileApi.token;
import static io.restassured.RestAssured.given;

public class TestTrackApi {

    /** declared string array and initialized */
    String[] trackIds = {"0VnEZ9XMrhMu8OCDnRBoEL", "1pvuGKdtbTuSuEXdryvOeS", "0dnDTvdUco2UbaBjUtPxNS"};

    /**
     *test case is written to test audio analysis for a track api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_01_GetAudioAnalysis_ForATrack_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/audio-analysis/"+ trackIds[0]);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test audio features for several tracks api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_02_GetAudioFeatures_ForSeveral_Tracks_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/audio-features?ids="+ Arrays.toString(trackIds));
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test audio features for a track api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_03_GetAudioFeatures_ForA_Tracks_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/audio-features/"+trackIds[0]);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test several tracks api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_04_GetSeveralTracks_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/tracks?ids="
                                           + trackIds[0]+"&market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test track api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_05_GetATrack_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/tracks/"+trackIds[0]+"?market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}

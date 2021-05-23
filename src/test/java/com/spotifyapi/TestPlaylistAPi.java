/*
 Purpose : Program for Test Cases is Written to Test Spotify Playlist Api
 @author Dinesh Kumar Peddakotla
 @since 23-05-2021
*/
package com.spotifyapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.spotifyapi.TestUserProfileApi.token;
import static io.restassured.RestAssured.*;

public class TestPlaylistAPi {

    /** declared string array and initialized */
    String[] playlistId = {"1z9iy3sQdnEHkPbDZn9ARj", "4OzZWovLLaob2dHyyWzBzw", "4tXVHLlsnDrkSEjRxYsRg4"};
    /** declared variable */
    String userId;

    /**
     *test case is written to test remove item in playlist api and to check status code 200
     * for DELETE operation
     */
    @Test
    public void testDELETE_01_RemoveItemsFromAPlaylist_Should_ReturnStatusCode200() {
        //declared map object and initialized with hashmap
        Map<String,Object> mapUri = new HashMap<>();
        mapUri.put("uri", "spotify:track:5nVFeACm96rucybpDAjfK5");

        //declared and initialized array object
        ArrayList<Object> tracks = new ArrayList<>();
        tracks.add(mapUri);

        Map<String,Object> map = new HashMap<>();
        map.put("tracks", tracks);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body.toJSONString())
                                   .delete("https://api.spotify.com/v1/playlists/"
                                           + playlistId[0] +"/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test create a new playlist api and to check status code 201
     * for POST operation
     */
    @Test
    public void testPOST_02_When_CreateAPlaylist_Should_ReturnStatusCode201() {
        Map<String,Object> map = new HashMap<>();

        map.put("name", "Latest Track");
        map.put("description", "Latest Songs");
        map.put("public", false);

        //created json object
        JSONObject body = new JSONObject(map);

        Response userResponse = given().header("Authorization", token )
                                       .get("https://api.spotify.com/v1/me");

        //user id extracted from user response
        userId = userResponse.then().extract().path("id");

        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .post("https://api.spotify.com/v1/users/"+userId+"/playlists");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     *test case is written to test added item to a playlist api and to check status code 201
     * for POST operation
     */
    @Test
    public void testPOST_03_WhenAdded_Items_toAPlaylist_ShouldReturn_StatusCode201() {
        String[] uris = {"spotify:track:6rcTPn1CwOyuskilY12IqL","spotify:track:74IQCxI4nws964fic1Q4pv"};

        Map<String,Object> map = new HashMap<>();
        map.put("uris", uris);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .contentType(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .post("https://api.spotify.com/v1/playlists/"+playlistId[1]+"/tracks");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     *test case is written to test upload custom playlist cover image api and to check status code 202
     * for PUT operation
     */
    @Test
    public void testPUT_04_WhenUploadA_CustomPlaylist_CoverImage_ShouldReturn_StatusCode202() {
        //declared variable and initialized file path
        String imagePath = "C:/Users/dinnu/Downloads/download.txt";
        //new file created
        File file = new File(imagePath);

        Response response = given().contentType(ContentType.TEXT)
                                       .accept(ContentType.TEXT)
                                       .header("Authorization", token)
                                       .body(file)
                                       .put("https://api.spotify.com/v1/playlists/"+playlistId[1]+"/images");

        response.prettyPrint();
        response.then().statusCode(202);
    }

    /**
     *test case is written to test reorder or replace api and to check status code 201
     * for PUT operation
     */
    @Test
    public void testPUT_05_WhenReorder_Or_ReplaceA_PlaylistItems_ShouldReturn_StatusCode200_Or_201() {
        Map<String,Object> map = new HashMap<>();
        map.put("range_start",1);
        map.put( "insert_before",3);
        map.put( "range_length",2);

        JSONObject body = new JSONObject(map);

        Response response = given()
                            .queryParam("uris", "spotify:track:74IQCxI4nws964fic1Q4pv")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .header("Authorization", token)
                            .body(body)
                            .put("https://api.spotify.com/v1/playlists/"+playlistId[2]+"/tracks");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     *test case is written to test change playlist details api and to check status code 200
     * for PUT operation
     */
    @Test
    public void testPUT_06_WhenChange_APlaylistDetails_ShouldReturn_StatusCode200() {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Latest Songs");
        map.put("description", "Latest Songs");
        map.put("public", false);

        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .put("https://api.spotify.com/v1/playlists/"+playlistId[2]);

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test list of current users playlist api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_07_WhenGetAListOf_CurrentUsers_Playlist_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("limit", 5)
                                   .queryParam("offset", 6)
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/me/playlists");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test get playlist cover image api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_08_WhenGetA_Playlist_CoverImage_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/playlists/"+playlistId[2]+"/images");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test get playlist items api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_09_WhenGetA_PlaylistItems_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .queryParam("fields",
                                           "items(added_by.id,track(name,href,album(name,href)))")
                                   .queryParam("limit", 10)
                                   .queryParam("offset", 5)
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/playlists/"+playlistId[2]+"/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test get playlist api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_10_WhenGet_APlaylist_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get(" https://api.spotify.com/v1/playlists/"+playlistId[2]);

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test list of users playlist api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGet_11_WhenGetA_ListOfA_UsersPlaylist_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/users/"+userId+"/playlists");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
